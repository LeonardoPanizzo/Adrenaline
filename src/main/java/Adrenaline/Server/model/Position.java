package Adrenaline.Server.model;

import java.util.Vector;

public class Position {

    private char room;               //room in which the position is part
    private int[] matr;             //coordinates of the position
    private boolean door;           //if true the position has a door into another room
    private boolean respawnPoint;     //tells if the position is respawn point and if there will be power up or weapons [cambiato da reset a respawn per leggibilità. Andrea]
    private Vector<Position> linked;       //positions that are reachable through the door
    private int ndoor;              //number of rooms that are reachable through the door
    private AmmoCard ammo;          //munitions found in the position
    private WeaponCard[] weapons;      //weapons found in the position
    private AmmoDeck ammoDeck;
    private WeaponDeck weaponDeck;
    private Vector<Player> players;

    //because positions are fixed they will be red from a file each time, the file will call the constructor
    public Position(int i, int j, char room, boolean door, boolean respawnPoint, AmmoDeck deckAmmo, WeaponDeck deckWeapon){
        matr=new int[2];
        matr[0]=i;
        matr[1]=j;
        this.room=room;
        this.door=door;
        this.ndoor=0;
        this.respawnPoint=respawnPoint;
        this.ammoDeck=deckAmmo;
        this.weaponDeck=deckWeapon;
        players=new Vector<Player>(0,1);
        if(door){
            linked=new Vector<Position>();
        }
        if(respawnPoint) {
             weapons=new WeaponCard[3];
            for(int k=0;k<3;k++)        //drawn 3 weaponcard
                weapons[k]=weaponDeck.pickUpWeapon();
        }else{
            ammo=ammoDeck.pickUpAmmo();
        }
    }

    public Position(SupportPosition p1,AmmoDeck deckAmmo, WeaponDeck deckWeapon){
        matr=new int[2];
        matr[0]=p1.geti();
        matr[1]=p1.getj();
        this.room=p1.getroom();
        this.door=p1.getdoor();
        this.ndoor=0;
        this.respawnPoint=p1.getrespawn();
        this.ammoDeck=deckAmmo;
        this.weaponDeck=deckWeapon;
        players=new Vector<Player>(0,1);
        if(door){
            linked=new Vector<Position>();
        }
        if(respawnPoint) {
            weapons=new WeaponCard[3];
            for(int k=0;k<3;k++)        //drawn 3 weaponcard
                weapons[k]=weaponDeck.pickUpWeapon();
        }else{
            ammo=ammoDeck.pickUpAmmo();
        }
    }

    //todo this metod should be removed (only test use)
    public void setAmmo(AmmoCard ammo) {
        this.ammo = ammo;
    }

    public AmmoCard getAmmo() {
        return ammo;
    }

    public char getRoom(){
        return room;
    }

    public boolean isDoor(){
        return door;
    }

    public int[] getCoordinate() {
        return matr;
    }

    public void deletePlayer(Player x){
        players.remove(x);
    }

    public void setPlayer(Player x){
        players.add(x);
    }

    public boolean isRespawnPoint() {
        return respawnPoint;
    }

    public String toString(){             //prints only the munitions and the weapons found on this room, the colour isn't needed because the CLI user has his board
        String info;
        if(this.respawnPoint){
            info="the weapons are: ";
            for(int i=0; i<weapons.length; i++){
                info=info+weapons[i].getName()+" ";
            }
        }else{
            info="the munitions are: "+ammo.toString();
        }
        return info;
    }

    /*@requires x!=null;
             @receives the position that will be linked to this.position,
             @the link is possible only if this and x are doors
             @*/
    public void setLinks(Position x){
        if(this.door==true && x.isDoor()){      //a positions to have link to another position must be door
            linked.add(x);
            ndoor++;
        }
    }

    /**
     * return an array containing the players in the position this
     * @return
     */
    public Player[] getPlayers(){
        int tot=players.size();
        Player[] playertoretu=new Player[tot];
        for(int i=0; i<tot; i++){
            playertoretu[i]=players.get(i);
        }
        return playertoretu;
    }

    /*@requires x!=null;
     @receives the positions that will be linked to this.position, the link is possible only if this and all the
     @positions in x are doors
     @*/
    public void setLinks(Position[] x){
        if (this.door == true){
            for (int i = 0; i < x.length; i++) {
                if(x[i].isDoor()) {
                    linked.add(x[i]);
                    ndoor++;
                }
            }
        }
    }

    /*@
    @shows the weapons to allow the player the choice between them
    @*/
    public WeaponCard[] showWeapons(){
        return weapons;
    }

    public boolean equals(Position x){
        return(this.matr[0]==x.matr[0] &&this.matr[1]==x.matr[1]);
    }


    //todo should the two methods down here be synchronized?
    /*@
    @called after showWeapons, after having seen the weapons the player comunicate his choice sending the position of
    @weapon in the array
    @*/
    public WeaponCard chooseArm(int i){
        WeaponCard x=weapons[i];
        weapons[i]=null;
        return x;
    }

    public boolean pickUpWeapon(WeaponCard weapon){
        boolean done=false;
        String name=weapon.getName();
        int x=0;
        for(;x<weapons.length && !done; x++){
            done=weapons[x].equals(name);
        }
        if(done){
            weapons[x]=null;
        }
        return done;
    }

    /**
     * return true if there was an empty space for the given weapon
     * @param x
     * @return
     */
    public boolean giveWeapon(WeaponCard x){
        boolean done=false;
        for(int i=0; i<3 && !done; i++) {
            if(weapons[i]==null){
                weapons[i]=x;
                done=true;
            }
        }
        return done;
    }

    //return AmmoTile to the player and replace the returned one with a new one
    // todo AmmoTile must be analized to see if the player get a power up or just munitions, will this be done in the action class?
    //yes, ammo and powerup are seen in the Player's methot Action() [Andrea]

    public AmmoCard pickUpAmmo(){
        AmmoCard a=ammo;
        ammo=ammoDeck.pickUpAmmo();
        return a;
    }


    /*@requires x!=null;
     @is the position x visible from position this?
     @*/
    public boolean visible(Position x){
        boolean vis=false;
        if(this.room==x.room)
            vis=true;       //if x is in the same room ot his then is visible from this
        for(int i=0; (!vis)&&(i<ndoor); i++)         //checks the positions linked to this through the door
            if(linked.elementAt(i).getRoom()==x.room)       //che if the linked.position is in the same room of x
                vis=true;
        return vis;
    }

    /*@requires x!=null;
    @is x reachable from this in one step?
    @*/
    public boolean reachable(Position x){
        boolean reac=false;
        if((this.matr[0]==x.matr[0]&&((this.matr[1]==x.matr[1]+1)||(this.matr[1]==x.matr[1]-1)))||
                (this.matr[1]==x.matr[1]&&((this.matr[0]==x.matr[0]+1)||(this.matr[0]==x.matr[0]-1)))){     //checks if the distance between x and this is 1
            if(visible(x))      //the distance between x and this is 1, in this case if x si visible then is reachable
                reac=true;
        }
        return reac;
    }

    public boolean reachable(Position[] x){
        boolean reac=this.reachable(x[0]);
        for(int i=1; i<x.length && reac; i++){
            reac=x[i-1].reachable(x[i]);
        }
        return reac;
    }
}