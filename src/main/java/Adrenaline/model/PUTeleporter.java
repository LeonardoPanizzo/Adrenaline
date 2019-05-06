package Adrenaline.model;

/**
 * using this power up the player's figure will be teleported in a position choosed by the player, this power up
 * can be played at any time during the player's own turn
 */

public class PUTeleporter extends PowerupCard {

    public PUTeleporter(char colour, Board b){
        super("teleporter", colour, b);
    }

    @Override
    public void activate(Player p) {
        Position [][] pos=board.getBoard();
        //todo: ricevere in input la scelta della posizione in cui andare fra quelle nella board
        p.setPosition(pos[0][1]); //0,1 ipotetica posizione scelta dal giocatore

    }
}
