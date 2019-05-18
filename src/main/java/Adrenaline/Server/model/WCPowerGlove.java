package Adrenaline.Server.model;

/**
 * basic mode: Choose 1 target on any square
 * exactly 1 move away. Move onto that square
 * and give the target 1 damage and 2 marks.
 * in rocket fist mode: Choose a square
 * exactly 1 move away. Move onto that square.
 * You may deal 2 damage to 1 target there.
 * If you want, you may move 1 more square in
 * that same direction (but only if it is a legal
 * move). You may deal 2 damage to 1 target
 * there, as well.
 * Notes: In rocket fist mode, you're flying
 * 2 squares in a straight line, punching
 * 1 person per square.
 */

public class WCPowerGlove extends WeaponCard{

    public WCPowerGlove(){
        super("Power Glove", new char[]{'y','b'}, new char[]{'b'});
    }

    @Override
    /*
    the position are checked on the attacked players, not in movements
     */
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment) {
        boolean done = false;
        if(isLoaded() && attackedPlayers.length>=1 && attacker.getPosition().reachable(attackedPlayers[0].getPosition())){
            if(mode1==0 && attackedPlayers.length==1){
                attackedPlayers[0].receivedDamages(attacker);
                attackedPlayers[0].setMarksReceived(attacker,2);
                attacker.setMarksGiven(attackedPlayers[0],2);
                attacker.setPosition(attackedPlayers[0].getPosition());
                loaded=false;
                done=true;
            }else if(mode1==1){
                if(attackedPlayers.length==1 && isPaid(attacker, payment)){
                    for(int i=0; i<2; i++)
                        attackedPlayers[0].receivedDamages(attacker);
                    attacker.setPosition(attackedPlayers[0].getPosition());
                    loaded=false;
                    done=true;
                }else if(attackedPlayers.length==2 && attackedPlayers[0].getPosition().reachable(attackedPlayers[1].getPosition()) && isPaid(attacker, payment)){
                    for(int i=0; i<2; i++){
                        attackedPlayers[i].receivedDamages(attacker);
                        attackedPlayers[i].receivedDamages(attacker);
                    }
                    attacker.setPosition(attackedPlayers[1].getPosition());
                    loaded=false;
                    done=true;
                }
            }
        }
        return done;
    }
}
