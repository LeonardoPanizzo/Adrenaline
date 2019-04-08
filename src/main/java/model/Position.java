package model;

import java.util.Vector;
import java.lang.*;

public class Position {

    private char room;               //room in which the position is part
    private int[] matr;             //coordinates of the position
    private boolean door;           //if true the position has a door into another room
    private boolean resetPoint;     //tells if the position is reset point and if there will be power up or weapons
    private Vector<Position> linked;       //positions that are reachable through the door
    private int ndoor;              //number of rooms that are reachable through the door
    private AmmoTile ammo;          //munitions found in the position
    private WeaponCard[] arms;      //weapons found in the position
    private int weaponSpot;         //index in WeaponCard[] oh where the weapon was taken and where the new one will be placed in the array
    private AmmoDeck ammoDeck;      //todo it must be given in the constructor so it will be possible to draw
    //todo is needed WeaponDeck for the same reason

    //beacause positions are fixed they wiil be red from a file each time, the file will call the constructor
    public Position(int i, int j, char room, boolean door, boolean resetPoint){
        matr=new int[2];
        arms=new WeaponCard[3];
        matr[0]=i;
        matr[1]=j;
        this.room=room;
        this.door=door;
        this.ndoor=0;
        this.resetPoint=resetPoint;
        if(door){
            linked=new Vector<Position>();
        }
        /*
        if(resetPoint) {
            //pesca le 3 armi
        }
        else {
        //todo this method can be called when there will be ammoDeck
        //    ammo=ammoDeck.pickUpAmmo();
        }
    */
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

    //receives the position that will be linked to this.position, the link is possible only if this and x are doors
    public void setLinks(Position x){
        if(this.door==true && x.isDoor()){      //a positions to have link to another position must be door
            linked.add(x);
            ndoor++;
        }
    }

    //receives the positions that will be linked to this.position, the link is possible only if this and all the positions
    //in x are doors
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

    //shows the weapons to allow the player the choice between them
    public WeaponCard[] showWeapons(){
        return arms;
    }


    //todo should the two methods down here be synchronized?
    //called after showWeapons, after having seen the weapons the player comunicate his choice sending the position of
    //weapon in the array
    public WeaponCard chooseArm(int i){
        weaponSpot=i;   //save the index of the choosen weapon, this index will be used when the discard weapon will be placed in tha spot
        return arms[i];
    }

    //After having received a weapon the discard one (WeaponCard x) is placed in the spot of the one choosen by the player
    public void giveWeapon(WeaponCard x){
        arms[weaponSpot]=x;
        return;
    }

    //return AmmoTile to the player and replace the returned one with a new one
    // todo AmmoTile must be analized to see if the player get a power up or just munitions, will this be done in the action class?
    /*
    public AmmoTile pickUpAmmo(){
        AmmoTile a=ammo;
        ammo=ammoDeck.pickUpAmmo();
        return a;
    }
    */
    //is the position x visible from position this?
    //x!=null
    public boolean visible(Position x){
        boolean vis=false;
        if(this.room==x.room)
            vis=true;       //if x is in the same room ot his then is visible from this
        for(int i=0; (!vis)&&(i<ndoor);i++)         //checks the positions linked to this through the door
            if(linked.elementAt(i).getRoom()==x.room)       //che if the linked.position is in the same room of x
                vis=true;
        return vis;
    }

    //is x reachable from this in one step?
    //x!=null
    public boolean reachable(Position x){
        boolean reac=false;
        if((this.matr[0]==x.matr[0]&&((this.matr[1]==x.matr[1]+1)||(this.matr[1]==x.matr[1]-1)))||
                (this.matr[1]==x.matr[1]&&((this.matr[0]==x.matr[0]+1)||(this.matr[0]==x.matr[0]-1)))){     //checks if the distance between x and this is 1
            if(visible(x))      //the distance between x and this is 1, in this case if x si visible then is reachable
                reac=true;
        }
        return reac;
    }
}