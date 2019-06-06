package Adrenaline.Server.model;

import java.util.Vector;

public class SupportPosition {
    private char room;               //room in which the position is part
    private int[] matr;             //coordinates of the position
    private boolean door;           //if true the position has a door into another room
    private boolean respawnPoint;     //tells if the position is respawn point and if there will be power up or weapons [cambiato da reset a respawn per leggibilità. Andrea]
    private int ndoor;              //number of rooms that are reachable through the door

    public SupportPosition(int[] matr, char room, boolean door, boolean respawnPoint){
        this.matr=matr;
        this.room=room;
        this.door=door;
        this.ndoor=0;
        this.respawnPoint=respawnPoint;
    }

    public int getj(){
        return matr[1];
    }

    public boolean getdoor(){
        return door;
    }

    public char getroom(){
        return room;
    }

    public int geti(){
        int a=matr[0];
        return a;
    }

    public boolean getrespawn(){
        return respawnPoint;
    }

    public boolean equals(SupportPosition x){
        if(this.matr[0]==x.matr[0] && this.matr[1]==x.matr[1] && this.room== x.room && this.door==x.door && this.respawnPoint==x.respawnPoint)
            return true;
        return false;
    }
    /*public String toString(){
        String a=matr[0]+"  "+matr[1]+" "+room+" "+door+" "+respawnPoint+" ";
        return a;
    }*/
}
