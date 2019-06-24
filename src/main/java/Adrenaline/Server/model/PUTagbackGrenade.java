package Adrenaline.Server.model;

import java.io.Serializable;

/**
 * You may play this card when you receive damage from a player you can see.
 * Give that player 1 mark.
 */

public class PUTagbackGrenade extends PowerupCard implements Serializable {

    public PUTagbackGrenade(char colour){
        super("tagback grenade", colour);
    }

    public boolean use(Player p1, Player p2, Position[] squares, char payment) {
        boolean done=false;
        if(p1.getPosition().visible(p2.getPosition())){
            p2.setMarksReceived(p1,1);
            p1.setMarksGiven(p2,1);
            done=true;
        }
        return done;
    }
}
