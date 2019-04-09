package model;

import java.util.Collections;
import java.util.Vector;

public class WeaponDeck {
    private int size;
    private Vector<WeaponCard> weaponDeck;
    //todo inserirò le carte qui dentro, nel costruttore

    public WeaponDeck(){
        size=0;
    }

    public void shuffle(){
        Collections.shuffle(weaponDeck);
    }

    public WeaponCard pickUpWeapon(){
        WeaponCard x=weaponDeck.elementAt(size);
        size++;
        if(size>=weaponDeck.capacity())       //se size raggiunge la dimensione del vettore lo azzero, sposto il punatore al posto di spostare gli elementi dentro vector, bisognerà controllare il metodo capacity
            size=0;
        return x;
    }
}
