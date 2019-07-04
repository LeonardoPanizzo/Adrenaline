package Adrenaline.Server.model;

import java.io.Serializable;

/**
 * basic effect: Deal 2 damage and 1 mark to 1 target you can see.
 * with second lock: Deal 1 mark to a different target you can see.
 */
public class WCLockRifle extends WeaponCard implements Serializable {
    public WCLockRifle() {
        super("LockRifle", new char[]{'b', 'b'}, new char[]{'0','r'},true);
    }

    /**
     * if checks that there are 1 or 2 hit players (the second one is hit by an optional effect) and that the first target is visible
     */
    @Override
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment) {
        boolean done = false;
        if (this.isLoaded() && attackedPlayers.length >= 1 && attackedPlayers.length <= 2 && attacker.canSee(attackedPlayers[0])) {
            if (mode2.length == 1 && attackedPlayers.length == 1 && mode2[0] == 0) {//checks if it is only the basic effect and only one attacked
                for (int i = 0; i < 2; i++)
                    attackedPlayers[0].receivedDamages(attacker);
                attackedPlayers[0].setMarksReceived(attacker, 1);
                attacker.setMarksGiven(attackedPlayers[0], 1);
                loaded = false;
                done = true;
            } else if (attackedPlayers.length == 2 && mode2.length == 2 && ((mode2[0] == 0 && mode2[1] == 1) || (mode2[0] == 1 && mode2[1] == 0)) && isPaid(attacker, payment)) { //checks if there is the basic effect and the optional one
                if (attacker.canSee(attackedPlayers[1])) {//checks if the second player is visible
                    for (int i = 0; i < 2; i++)
                        attackedPlayers[0].receivedDamages(attacker);
                    attackedPlayers[0].setMarksReceived(attacker, 1);
                    attacker.setMarksGiven(attackedPlayers[0], 1);
                    attackedPlayers[1].setMarksReceived(attacker, 1);
                    attacker.setMarksGiven(attackedPlayers[1], 1);
                    loaded = false;
                    done = true;
                }
            }
        }
        return done;
    }
}

