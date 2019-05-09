package Adrenaline.model;

import java.util.Collections;
import java.util.Vector;

public class AmmoDeck {
    private int size;
    private Vector<AmmoCard> ammoDeck;

    public AmmoDeck(){
        size=0;
        for(int i=0;i<4;i++){
            ammoDeck.add(new AmmoCard(new char[]{'p','y','r'}));//create 4 copies of this ammo
            ammoDeck.add(new AmmoCard(new char[]{'p','r','b'}));//create 4 copies of this ammo
            ammoDeck.add(new AmmoCard(new char[]{'p','y','b'}));//create 4 copies of this ammo
        }
        for(int i=0;i<3;i++){
            ammoDeck.add(new AmmoCard(new char[]{'b','y','y'}));
            ammoDeck.add(new AmmoCard(new char[]{'y','b','b'}));
            ammoDeck.add(new AmmoCard(new char[]{'y','r','r'}));
            ammoDeck.add(new AmmoCard(new char[]{'r','y','y'}));
            ammoDeck.add(new AmmoCard(new char[]{'r','b','b'}));
            ammoDeck.add(new AmmoCard(new char[]{'b','r','r'}));
        }
        for(int i=0;i<2;i++){
            ammoDeck.add(new AmmoCard(new char[]{'p','b','b'}));
            ammoDeck.add(new AmmoCard(new char[]{'p','r','r'}));
            ammoDeck.add(new AmmoCard(new char[]{'p','y','y'}));
        }

    }

    public void shuffle(){
        Collections.shuffle(ammoDeck);
    }

    public AmmoCard pickUpAmmo(){
        AmmoCard x=ammoDeck.elementAt(size);
        size++;
        if(size>=36)       //se size raggiunge la dimensione del vettore lo azzero, sposto il punatore al posto di spostare gli elementi dentro vector, bisognerà controllare il metodo capacity
            shuffle();
            size=0;
        return x;
    }
}
