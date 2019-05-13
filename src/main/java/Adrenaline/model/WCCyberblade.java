package Adrenaline.model;

/**
 * basic effect: Deal 2 damage to 1 target on your square.
 * with shadowstep: Move 1 square before or after the basic effect.
 * with slice and dice: Deal 2 damage to a different target on your square.
 * The shadowstep may be used before or after this effect.
 * Notes: Combining all effects allows you to move onto a square and
 * whack 2 people; or whack somebody, move, and whack somebody else;
 * or whack 2 people and then move.
 */

public class WCCyberblade extends WeaponCard{

    public WCCyberblade(){
        super("Cyberblade", new char[]{'y','r'}, new char[]{'0','0','y'});
    }

    @Override
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment){
        boolean done=false;
        return done;
    }
}
