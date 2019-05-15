package Adrenaline.Server.model;

import java.util.Collections;
import java.util.Vector;

public class PowerupDeck {
    private int size;
    private Vector<PowerupCard> PUDeck;

    public PowerupDeck(){
        size=0;
        PUDeck=new Vector<PowerupCard>(24);
        for(int i=0;i<2;i++){//create for each power up two copies with the same colour
            PUDeck.add(new PUNewton('b'));
            PUDeck.add(new PUNewton('r'));
            PUDeck.add(new PUNewton('y'));

            PUDeck.add(new PUTagbackGrenade('b'));
            PUDeck.add(new PUTagbackGrenade('r'));
            PUDeck.add(new PUTagbackGrenade('y'));

            PUDeck.add(new PUTargetingScope('b'));
            PUDeck.add(new PUTargetingScope('r'));
            PUDeck.add(new PUTargetingScope('y'));

            PUDeck.add(new PUTeleporter('b'));
            PUDeck.add(new PUTeleporter('r'));
            PUDeck.add(new PUTeleporter('y'));
        }
        shuffle();
    }

    public void shuffle(){
        Collections.shuffle(PUDeck);
    }

    public PowerupCard pickUpPowerup(){
        PowerupCard x=PUDeck.elementAt(size);
        size++;
        if(size>=24) {   //the deck contains 24 cards
            size = 0;
            shuffle();
        }
        return x;
    }
}
