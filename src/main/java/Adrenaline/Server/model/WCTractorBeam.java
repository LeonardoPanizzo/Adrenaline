package Adrenaline.Server.model;

/**
 * basic mode: Move a target 0, 1, or 2 squares to a square
 * you can see, and give it 1 damage.
 * in punisher mode: Choose a target 0, 1, or 2 moves away
 * from you. Move the target to your square
 * and deal 3 damage to it.
 * Notes: You can move a target even if you can't see it.
 * The target ends up in a place where you can see and
 * damage it. The moves do not have to be in the same
 * direction.
 */

public class WCTractorBeam extends WeaponCard{

    public WCTractorBeam(){
        super("Tractor Beam", new char[]{'b'}, new char[]{'r','y'},false);
    }

    @Override
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment) {
        boolean done = false;
        if(isLoaded() && attackedPlayers.length==1){
            if(mode1==0){
                if((movements==null || movements.length==0) && attacker.canSee(attackedPlayers[0])){
                    attackedPlayers[0].receivedDamages(attacker);
                    loaded=false;
                    done=true;
                }else if(movements != null){
                    boolean correctinput = attackedPlayers[0].getPosition().reachable(movements[0]);
                    for (int i = 0; i < movements.length - 1 && correctinput; i++) //in case the attacker wants to move the attacked
                        correctinput = movements[i].reachable(movements[i + 1]);
                    correctinput = correctinput && attacker.getPosition().visible(movements[movements.length - 1]);
                    if (correctinput && movements.length != 0) {
                        attackedPlayers[0].setPosition(movements[movements.length - 1]);
                        attackedPlayers[0].receivedDamages(attacker);
                        loaded = false;
                        done = true;
                    }
                }
            }else if(mode1==1) {
                if((movements==null || movements.length==0) && attacker.getPosition().equals(attackedPlayers[0].getPosition()) && isPaid(attacker, payment)){
                    for (int i = 0; i < 3; i++)
                        attackedPlayers[0].receivedDamages(attacker);
                    loaded=false;
                    done=true;
                }else if (movements != null){
                    boolean correctinput = attackedPlayers[0].getPosition().reachable(movements[0]);
                    for (int i = 0; i < movements.length - 1 && correctinput; i++) //in case the attacker wants to move the attacked
                        correctinput = movements[i].reachable(movements[i + 1]);
                    correctinput = correctinput && attacker.getPosition().equals(movements[movements.length - 1]);
                    if (correctinput && movements.length != 0) {
                        attackedPlayers[0].setPosition(movements[movements.length - 1]);
                        for (int i = 0; i < 3; i++)
                            attackedPlayers[0].receivedDamages(attacker);
                        loaded = false;
                        done = true;
                    }
                }
            }
        }
        return done;
    }
}
