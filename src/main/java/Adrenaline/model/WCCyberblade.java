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
        if(isLoaded() && attackedPlayers.length<=2 && attackedPlayers.length>=1){
            Position attackerpos=attacker.getPosition();
            Position attackedpos=attackedPlayers[0].getPosition();
            if(mode2.length==1 && mode2[0]==0 && attackerpos.equals(attackedpos)){
                for(int i=0; i<2; i++)
                    attackedPlayers[0].receivedDamages(attacker);
                loaded=false;
                done=true;
            }else if(mode2.length==2){
                if(mode2[0]==0 && mode2[1]==1 && movements != null && movements.length==1 && attackerpos.equals(attackedpos) && attackerpos.reachable(movements[0])){
                    for(int i=0; i<2; i++)
                        attackedPlayers[0].receivedDamages(attacker);
                    attacker.setPosition(movements[0]);
                    loaded=false;
                    done=true;
                }else if(mode2[0]==1 && mode2[1]==0 && movements.length==1 && movements[0].equals(attackedpos) && attackerpos.reachable(movements[0])){
                    attacker.setPosition(movements[0]);
                    for(int i=0; i<2; i++)
                        attackedPlayers[0].receivedDamages(attacker);
                    loaded=false;
                    done=true;
                }else if(mode2[0]==0 && mode2[1]==2 && attackedPlayers.length==2 && attackerpos.equals(attackedpos) && attackerpos.equals(attackedPlayers[1].getPosition()) && isPaid(attacker, payment, mode2)){
                    for(int i=0; i<2; i++) {
                        attackedPlayers[0].receivedDamages(attacker);
                        attackedPlayers[1].receivedDamages(attacker);
                    }
                    loaded=false;
                    done=true;
                }
            }else if(mode2.length==3 && attackedPlayers.length==2 && movements.length==1){
                if(mode2[0]==0 && mode2[1]==1 && mode2[2]==2 && attackerpos.equals(attackedpos) && attackerpos.reachable(movements[0]) && movements[0].equals(attackedPlayers[0].getPosition()) && isPaid(attacker, payment, mode2)){
                    for(int i=0; i<2; i++)
                        attackedPlayers[0].receivedDamages(attacker);
                    attacker.setPosition(movements[0]);
                    for(int i=0; i<2; i++)
                        attackedPlayers[1].receivedDamages(attacker);
                    loaded=false;
                    done=true;
                }else if(mode2[0]==1 && mode2[1]==0 && mode2[2]==2 && attackerpos.reachable(movements[0]) && movements[0].equals(attackedpos) && attackerpos.equals(attackedPlayers[1].getPosition()) && isPaid(attacker, payment, mode2)){
                    attacker.setPosition(movements[0]);
                    for(int i=0; i<2; i++)
                        attackedPlayers[0].receivedDamages(attacker);
                    for(int i=0; i<2; i++)
                        attackedPlayers[1].receivedDamages(attacker);
                    loaded=false;
                    done=true;
                }else if(mode2[0]==0 && mode2[1]==2 && mode2[2]==1 && attackerpos.reachable(movements[0]) && attackerpos.equals(attackedpos) && attackerpos.equals(attackedPlayers[1].getPosition()) && isPaid(attacker, payment, mode2)){
                    for(int i=0; i<2; i++)
                        attackedPlayers[0].receivedDamages(attacker);
                    for(int i=0; i<2; i++)
                        attackedPlayers[1].receivedDamages(attacker);
                    attacker.setPosition(movements[0]);
                    loaded=false;
                    done=true;
                }
            }
        }
        return done;
    }
}
