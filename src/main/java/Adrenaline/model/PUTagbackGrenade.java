package Adrenaline.model;

/**
 * You may play this card when you receive damage from a player you can see.
 * Give that player 1 mark.
 */

public class PUTagbackGrenade extends PowerupCard{

    public PUTagbackGrenade(char colour, Board b){
        super("targetback grenade", colour, b);
    }

    public boolean use(Player p1, Player p2, Position[] squares) {
        boolean done=false;
        if(p1.getPosition().visible(p2.getPosition())){
            p2.setMarksReceived(p1,1);
            p1.setMarksGiven(p2,1);
            done=true;
        }
        return done;
    }
}
