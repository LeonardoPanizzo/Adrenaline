package Adrenaline.Server.model;

import java.io.Serializable;
import java.util.Vector;

public class Position implements Serializable {

    private char room;               //room in which the position is part
    private int[] matr;             //coordinates of the position
    private boolean door;           //if true the position has a door into another room
    private boolean respawnPoint;     //tells if the position is respawn point and if there will be power up or weapons
    private Vector<Position> linked;       //positions that are reachable through the door
    private int ndoor;              //number of rooms that are reachable through the door
    private AmmoCard ammo;          //munitions found in the position
    private WeaponCard[] weapons;      //weapons found in the position
    private AmmoDeck ammoDeck;
    private WeaponDeck weaponDeck;
    private Vector<Player> players;

    //because positions are fixed they will be red from a file each time, the file will call the constructor

    /**
     * Costructor for position. This object is created by reading a JSon file. A position is composed by coordinates,
     * the room's color, doors. It also could be a respawn point (and so it can access to the weapon deck) or no; in
     * this second case, a position has an Ammo card on itself.
     *
     * @param i the x coordinate
     * @param j the y coordinate
     * @param room color to know in which room we are
     * @param door boolean value to know if there are doors in the position
     * @param respawnPoint to know if we can grab ammunition or weapons
     * @param deckAmmo deck with all weapon cards
     * @param deckWeapon deck with all ammo cards
     */
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

    /**
     * This is used as a second constructor of the class Position, in this constructor there is the object
     * SupportPosition that is the position red from gson file and the decks that are created in the board
     *
     * @param p1
     * @param deckAmmo
     * @param deckWeapon
     */
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

    /**
     * Method to have access to weapon deck
     *
     * @return the weapon deck
     */
    public WeaponDeck getWeaponDeck() {
        return weaponDeck;
    }

    /**
     * Method to have access to ammo deck
     *
     * @return the ammo deck
     */
    public AmmoDeck getAmmoDeck() {
        return ammoDeck;
    }

    /**
     * Method used in test to test the possibility to take an ammo card.
     *
     * @param ammo ammo to give to position
     */
    public void setAmmo(AmmoCard ammo) {
        this.ammo = ammo;
    }

    /**
     * Method to see the ammo card present in position
     *
     * @return the ammo card
     */
    public AmmoCard getAmmo() {
        return ammo;
    }

    /**
     * Method to see in which room the position is.
     *
     * @return color of the room
     */
    public char getRoom(){
        return room;
    }

    /**
     * Method to see if one or more doors are present in position.
     *
     * @return true if one door at least is present
     */
    public boolean isDoor(){
        return door;
    }

    /**
     * Method to see the position's coordinates.
     *
     * @return an array with position's coordinate
     */
    public int[] getCoordinate() {
        return matr;
    }

    /**
     * Method to remove a player that was in this position, but now he moves away.
     *
     * @param x player to remove
     */
    public void deletePlayer(Player x){
        players.remove(x);
    }

    /**
     * Method to add a player that has moved on this position.
     *
     * @param x player to add to position
     */
    public void setPlayer(Player x){
        players.add(x);
    }

    /**
     *Method to know if the position is a respawn point or not.
     *
     * @return true if position is a respawn point
     */
    public boolean isRespawnPoint() {
        return respawnPoint;
    }

    /**
     * Method to convert munition on the ammo card in position to string format.
     *
     * @return the string that shows the ammo card value
     */
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

    /**
     * requires x!=null;
     * receives the position that will be linked to this.position,
     * the link is possible only if this and x are doors.
     *
     * @param x position with a link to this
     */
    public void setLinks(Position x){
        if(this.door==true && x.isDoor()){      //a positions to have link to another position must be door
            linked.add(x);
            ndoor++;
        }
    }

    /**
     * Method to take players standing on this position.
     *
     * @return array containing the players in the position this
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
     @*/
    /**
     * Receives the positions that will be linked to this.position, the link is possible only if this and all the
     * positions in x are doors
     *
     * @param x
     */
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

    /**
     * Hows the weapons to allow the player the choice between them.
     *
     * @return weapons in this (if it is a respawn point return will not to be null)
     */
    public WeaponCard[] showWeapons(){
        return weapons;
    }

    /**
     * Method to know if two positions are the same one.
     *
     * @param x position to confront to this
     * @return true if positions are the same
     */
    public boolean equals(Position x){
        return(this.matr[0]==x.matr[0] &&this.matr[1]==x.matr[1]);
    }

    /**
     * called after showWeapons, after having seen the weapons the player comunicate his choice sending the position of
     * weapon in the array.
     *
     * @param i index of weapon was chosen
     * @return the weapon chosen
     */
    public WeaponCard chooseArm(int i){
        WeaponCard x=weapons[i];
        weapons[i]=null;
        return x;
    }

    /**
     * Method to pik up a weapon if position has one or more.
     *
     * @param weapon weapon chosen
     * @return a boolean to show if everything go right
     */
    public boolean pickUpWeapon(WeaponCard weapon){
        boolean done=false;
        String name=weapon.getName();
        int x;
        for( x=0;x<weapons.length && !done; x++){
            if(weapons[x]!= null)
                done=weapons[x].getName().equals(name);
        }
        if(done){
            weapons[x-1]=null;
        }
        return done;
    }

    /**
     * return true if there was an empty space for the given weapon
     *
     * @param x weapon chosen
     * @return a boolean to show if everything go right
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

    /**
     * It returns the ammo card in the position and set the value at null
     * @return
     */
    public AmmoCard pickUpAmmo(){
        AmmoCard a=ammo;
        ammo=null;
        return a;
    }


    /*@requires x!=null;
     @*/
    /**
     * is the position x visible from position this?
     *
     * @param x position to know if is visible from this
     * @return true if x is visible
     */
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
    @*/
    /**
     * is x reachable from this in one step?
     *
     * @param x position to know if is reachable from this
     * @return true if it is
     */
    public boolean reachable(Position x){
        boolean reac=false;
        if((this.matr[0]==x.matr[0]&&((this.matr[1]==x.matr[1]+1)||(this.matr[1]==x.matr[1]-1)))||
                (this.matr[1]==x.matr[1]&&((this.matr[0]==x.matr[0]+1)||(this.matr[0]==x.matr[0]-1)))){     //checks if the distance between x and this is 1
            if(visible(x))      //the distance between x and this is 1, in this case if x si visible then is reachable
                reac=true;
        }
        return reac;
    }

    /**
     * Method to know if an array of positions are reachable in order from the one with 0 index to the one at the end
     * of the array.
     *
     * @param x positions' array
     * @return true if the last position is reachable starting from this and going through the ones in the array
     */
    public boolean reachable(Position[] x){
        boolean reac=this.reachable(x[0]);
        for(int i=1; i<x.length && reac; i++){
            reac=x[i-1].reachable(x[i]);
        }
        return reac;
    }
}