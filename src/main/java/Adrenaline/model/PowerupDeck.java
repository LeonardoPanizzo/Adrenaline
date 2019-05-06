package Adrenaline.model;

import java.util.Collections;
import java.util.Vector;

public class PowerupDeck {
    private int size;
    private Vector<PowerupCard> powerupDeck;

    public PowerupDeck(){
        size=0;
    }

    public void shuffle(){
        Collections.shuffle(powerupDeck);
    }

    public PowerupCard pickUpPowerup(){
        PowerupCard x=powerupDeck.elementAt(size);
        size++;
        if(size>=powerupDeck.capacity())       //se size raggiunge la dimensione del vettore lo azzero, sposto il punatore al posto di spostare gli elementi dentro vector, bisognerà controllare il metodo capacity
            size=0;
        return x;
    }
}
