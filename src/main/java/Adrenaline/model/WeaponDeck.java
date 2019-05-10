package Adrenaline.model;

import java.util.Collections;
import java.util.Vector;

public class WeaponDeck {
    private int size;
    private Vector<WeaponCard> weaponDeck;

    public WeaponDeck(){
        size=0;
        weaponDeck=new Vector<WeaponCard>(21);
        weaponDeck.add(new WCLockRifle());
        weaponDeck.add(new WCHeatseeker());
        shuffle();
    }

    public void shuffle(){
        Collections.shuffle(weaponDeck);
    }

    public WeaponCard pickUpWeapon(){
        WeaponCard x=weaponDeck.elementAt(size);
        size++;
        if(size>=21) {       //se size raggiunge la dimensione del vettore lo azzero, sposto il punatore al posto di spostare gli elementi dentro vector, bisognerà controllare il metodo capacity
            size = 0;
            shuffle();
        }
        return x;
    }
}