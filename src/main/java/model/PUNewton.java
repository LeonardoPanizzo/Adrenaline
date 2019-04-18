package model;

/**
 * Using this powerup a player can move another player's figure of 1 or 2 suares in one direction
 */

public class PUNewton extends PowerupCard{

    public PUNewton (char colour, Board b){
        super("Newton", colour, b);
    }

    @Override
    public void activate(Player p){
        //todo il giocatore deve scegliere il giocatore p1 da muovere


    }

}
