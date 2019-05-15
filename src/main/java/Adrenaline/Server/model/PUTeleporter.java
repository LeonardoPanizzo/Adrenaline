package Adrenaline.Server.model;

/**
 * using this power up the player's figure will be teleported in a position choosed by the player, this power up
 * can be played at any time during the player's own turn
 */

public class PUTeleporter extends PowerupCard {

    public PUTeleporter(char colour){
        super("teleporter", colour);
    }

    @Override
    public boolean use(Player p1, Player p2, Position[] squares, char payment){
        boolean done=false;
        if(squares.length==1 && squares[0] != null){
            p1.setPosition(squares[0]);
            done=true;
        }
        return done;
    }
}
