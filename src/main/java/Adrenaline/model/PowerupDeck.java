package Adrenaline.model;

import java.util.Collections;
import java.util.Vector;

public class PowerupDeck {
    private int size;
    private Vector<PowerupCard> PUDeck;

    public PowerupDeck(Board board){
        size=0;
        PUDeck=new Vector<PowerupCard>(24);
        for(int i=0;i<2;i++){//create for each power up two copies with the same colour
            PUDeck.add(new PUNewton('b', board));
            PUDeck.add(new PUNewton('r', board));
            PUDeck.add(new PUNewton('y', board));

            PUDeck.add(new PUTagbackGrenade('b', board));
            PUDeck.add(new PUTagbackGrenade('r', board));
            PUDeck.add(new PUTagbackGrenade('y', board));

            PUDeck.add(new PUTargetingScope('b', board));
            PUDeck.add(new PUTargetingScope('r', board));
            PUDeck.add(new PUTargetingScope('y', board));

            PUDeck.add(new PUTeleporter('b', board));
            PUDeck.add(new PUTeleporter('r', board));
            PUDeck.add(new PUTeleporter('y', board));
        }
        shuffle();
    }

    public void shuffle(){
        Collections.shuffle(PUDeck);
    }

    public PowerupCard pickUpPowerup(){
        PowerupCard x=PUDeck.elementAt(size);
        size++;
        if(size>=24)    //the deck contains 24 cards
            size=0;
        //todo aggiungere il rimescolare il mazzo
        return x;
    }
}
