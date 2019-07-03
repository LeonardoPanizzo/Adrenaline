package Adrenaline.Server.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Vector;

public class AmmoDeck implements Serializable {
    private int size;
    private Vector<AmmoCard> ammoDeck;

    /**
     * Costructor for AmmoDeck. Initial size is setted to 0. The initial capacity is 36. The deck is composed by 12
     * kind of cards. Some ones are present in 4 copies, other in 3 and other in 2.
     * When the deck is created, it is immediately shuffled.
     */
    public AmmoDeck(){
        size=0;
        ammoDeck=new Vector<AmmoCard>(36);
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
        shuffle();
    }

    /**
     * Method to put in a random sequence the cards in it.
     */

    public void shuffle(){
        Collections.shuffle(ammoDeck);
    }

    /**
     * A card is taken from the deck. The the size is incremented. When the size has the same value like initial
     * capacity, the deck is recomposed and shuffle again.
     * @return
     */
    public AmmoCard pickUpAmmo(){
        AmmoCard x=ammoDeck.elementAt(size);
        size++;
        if(size>=36) {      //se size raggiunge la dimensione del vettore lo azzero, sposto il punatore al posto di spostare gli elementi dentro vector, bisognerà controllare il metodo capacity
            shuffle();
            size = 0;
        }
        return x;
    }
}
