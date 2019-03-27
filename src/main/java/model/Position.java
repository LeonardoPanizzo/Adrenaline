package model;

public class Position {
    int room;
    int[] matr;
    boolean door;
    int positionDoor;       //up,down,right,left
    boolean resetPoint;
    PowerupCard potenziamento;
    WeaponCard[] arms;

    public void position(int i, int j, int room, boolean door, int positionDoor, boolean resetPoint){
        matr=new int[2];
        arms=new WeaponCard[3];
        matr[0]=i;
        matr[1]=j;
        this.room=room;
        this.door=door;
        this.positionDoor=positionDoor;
        this.resetPoint=resetPoint;
        if(resetPoint) {
            //pesca le 3 armi
        }
        else {
            //pesca il potenziamento
        }
    }
}