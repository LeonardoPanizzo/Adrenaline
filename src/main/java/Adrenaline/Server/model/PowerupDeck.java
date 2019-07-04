package Adrenaline.Server.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Vector;

public class PowerupDeck implements Serializable {
    private int size;
    private Vector<PowerupCard> PUDeck;

    /**
     * Constructor for PowerupDeck. All powerup are created and then the deck is shuffled. There are three copies for
     * each powerup: one for each color.
     */
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

    /**
     * Method to shuffle the deck
     */
    public void shuffle(){
        Collections.shuffle(PUDeck);
    }

    /**
     * Method to draw a power up card from the deck. When the size is reached, the deck is re-shuffled again.
     *
     * @return the power up that was drown
     */
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
