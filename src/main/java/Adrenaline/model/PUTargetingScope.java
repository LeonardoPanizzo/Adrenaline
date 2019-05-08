package Adrenaline.model;

/**
 * You may play this card when you are dealing damage to one or more targets. Pay 1 ammo cube of any color.
 * Choose 1 of those targets and give it an extra point of damage.
 * Note: You cannot use this to do 1 damage to a target that is receiving only marks.
 */

public class PUTargetingScope extends PowerupCard{

    public PUTargetingScope(char colour, Board b){
        super("targeting scope", colour, b);
    }

    public boolean use(Player p1, Player p2, Position[] squares){
        boolean done=false;

        return done;
    }
}