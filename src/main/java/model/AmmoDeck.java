package model;

import java.util.Vector;

public class AmmoDeck {
    private int size;
    private Vector<AmmoTile> AmmoDeck;

    public AmmoDeck(){
        size=0;
    }

    public AmmoTile pickUpAmmo(){
        AmmoTile x=AmmoDeck.elementAt(size);
        size++;
        if(size>=AmmoDeck.capacity())       //se size raggiunge la dimensione del vettore lo azzero, posto il punatore al posto di spostare gli elementi dentro vector, bisognerà controllare il metodo capacity
            size=0;
        return x;
    }
}
