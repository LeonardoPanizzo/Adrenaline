package Adrenaline.Server.model;

/**
 * effect: Deal 3 damage and 1 mark to 1 target you can see.
 * Your target must be at least 2 moves away from you.
 * Notes: For example, in the 2-by-2 room, you cannot shoot
 * a target on an adjacent square, but you can shoot a target
 * on the diagonal. If you are beside a door, you can't shoot
 * a target on the other side of the door, but you can shoot
 * a target on a different square of that room.
 */

public class WCWhisper extends WeaponCard{

    public WCWhisper() {
        super("Whisper", new char[]{'b', 'b','y'}, new char[]{},false);
    }

    @Override
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment) {
        boolean done = false;
        if(isLoaded() && attackedPlayers.length==1){
            Position attackerposition=attacker.getPosition();
            Position attackedposition=attackedPlayers[0].getPosition();
            if(attackerposition.visible(attackedposition) && !attackerposition.reachable(attackedposition) && !attackerposition.equals(attackedposition)){//checks if the positions are at least two steps away from each other
                for(int i=0; i<3; i++){
                    attackedPlayers[0].receivedDamages(attacker);
                }
                attackedPlayers[0].setMarksReceived(attacker, 1);
                attacker.setMarksGiven(attackedPlayers[0], 1);
                done=true;
                loaded=false;
            }
        }
        return done;
    }
}
