package Adrenaline.model;

/**
 * Using this powerup a player can move another player's figure of 1 or 2 squares in one direction
 */

public class PUNewton extends PowerupCard{

    public PUNewton (char colour, Board b){
        super("Newton", colour, b);
    }

    @Override
    public boolean use(Player p1, Player p2, Position[] squares){
        boolean done=false;

        return done;
    }

}