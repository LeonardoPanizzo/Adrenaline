package Adrenaline.model;

import java.util.Collections;
import java.util.Vector;

public class AmmoDeck {
    private int size;
    private Vector<AmmoCard> ammoDeck;

    public AmmoDeck(){
        size=0;
    }

    public void shuffle(){
        Collections.shuffle(ammoDeck);
    }

    public AmmoCard pickUpAmmo(){
        AmmoCard x=ammoDeck.elementAt(size);
        size++;
        if(size>=ammoDeck.capacity())       //se size raggiunge la dimensione del vettore lo azzero, sposto il punatore al posto di spostare gli elementi dentro vector, bisognerà controllare il metodo capacity
            size=0;
        return x;
    }
}
