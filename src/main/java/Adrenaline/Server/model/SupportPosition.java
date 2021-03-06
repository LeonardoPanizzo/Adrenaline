package Adrenaline.Server.model;

import java.io.Serializable;
import java.util.Vector;

public class SupportPosition implements Serializable {
    private char room;               //room in which the position is part
    private int[] matr;             //coordinates of the position
    private boolean door;           //if true the position has a door into another room
    private boolean respawnPoint;     //tells if the position is respawn point and if there will be power up or weapons
    private int ndoor;              //number of rooms that are reachable through the door

    /**
     * Constructor for support position. It use the classical position's element.
     *
     * @param matr coordinates of the position
     * @param room room in which the position is part
     * @param door if true the position has a door into another room
     * @param respawnPoint tells if the position is respawn point and if there will be power up or weapons
     */
    public SupportPosition(int[] matr, char room, boolean door, boolean respawnPoint){
        this.matr=matr;
        this.room=room;
        this.door=door;
        this.ndoor=0;
        this.respawnPoint=respawnPoint;
    }

    /**
     * Method to have y coordinate
     *
     * @return y coordinate
     */
    public int getj(){
        return matr[1];
    }

    /**
     * Method to know if a door is present
     *
     * @return true if a door is present
     */
    public boolean getdoor(){
        return door;
    }

    /**
     * Method to see in which room the position is.
     *
     * @return the room's color
     */
    public char getroom(){
        return room;
    }

    /**
     * Method to have the x coordinate
     *
     * @return the x coordinate
     */
    public int geti(){
        int a=matr[0];
        return a;
    }

    /**
     * Method to know if the position is a respawn point or not.
     *
     * @return true if the position is a respawn point
     */
    public boolean getrespawn(){
        return respawnPoint;
    }

    /**
     * Method to see if two positions are the same one or not.
     *
     * @param x position to confront to this
     * @return true if the positions are the same one
     */
    public boolean equals(SupportPosition x){
        if(this.matr[0]==x.matr[0] && this.matr[1]==x.matr[1] && this.room== x.room && this.door==x.door && this.respawnPoint==x.respawnPoint)
            return true;
        return false;
    }

    /**
     * Method to convert SupportPosition into a string
     *
     * @return the string which represent SupportPosition
     */
    public String toString(){
        String a=matr[0]+"  "+matr[1]+" "+room+" "+door+" "+respawnPoint+" ";
        return a;
    }
}
