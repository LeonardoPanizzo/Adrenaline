package Adrenaline.Server.model;

/**
 * basic effect: Deal 2 damage to 1 target you can see that is not on your
 * square. Then you may move the target 1 square.
 * with rocket jump: Move 1 or 2 squares. This effect can be used either
 * before or after the basic effect.
 * with fragmenting warhead: During the basic effect, deal 1 damage to
 * every player on your target's original square – including the target,
 * even if you move it.
 * Notes: If you use the rocket jump before the basic effect, you consider
 * only your new square when determining if a target is legal. You can
 * even move off a square so you can shoot someone on it. If you use the
 * fragmenting warhead, you deal damage to everyone on the target's
 * square before you move the target – your target will take 3 damage total.
 */

public class WCRocketLauncher extends WeaponCard{

    public WCRocketLauncher() {
        super("Rocket Launcher", new char[]{'r', 'r'}, new char[]{'0', 'b', 'y'});
    }

    @Override
    /*
    movements[0] is about the movement of attackedpalyer[0], movements[1/2] are about the movements of the attacker
     */
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment) {
        boolean done = false;
        if(isLoaded() && attackedPlayers.length>=1){
            if(mode2.length==1 && mode2[0]==0 && attacker.canSee(attackedPlayers[0]) && !attacker.getPosition().equals(attackedPlayers[0].getPosition())){
                for(int i=0; i<2; i++)
                    attackedPlayers[0].receivedDamages(attacker);
                if(movements.length==1 && attackedPlayers[0].getPosition().reachable(movements[0]))
                    attackedPlayers[0].setPosition(movements[0]);
                loaded=false;
                done=true;
            }else if(mode2.length==2 && mode2[0]==0 && mode2[1]==1 && attacker.canSee(attackedPlayers[0]) && !attacker.getPosition().equals(attackedPlayers[0].getPosition()) && movements[1]!=null && attacker.getPosition().reachable(movements[1]) && isPaid(attacker, payment, mode2)){
                for(int i=0; i<2; i++)
                    attackedPlayers[0].receivedDamages(attacker);
                if(movements[0]!=null && attackedPlayers[0].getPosition().reachable(movements[0]))
                    attackedPlayers[0].setPosition(movements[0]);
                if(movements[2]!=null && movements[1].reachable(movements[2])){
                    attacker.setPosition(movements[2]);
                }else{
                    attacker.setPosition(movements[1]);
                }
                loaded=false;
                done=true;
            }else if(mode2.length==2 && mode2[0]==1 && mode2[1]==0 && movements[1]!=null && attacker.getPosition().reachable(movements[1])){
                if(movements[2]!=null && movements[1].reachable(movements[2]) && movements[2].visible(attackedPlayers[0].getPosition()) && !movements[2].equals(attackedPlayers[0].getPosition()) && isPaid(attacker, payment, mode2)){
                    for(int i=0; i<2; i++)
                        attackedPlayers[0].receivedDamages(attacker);
                    if(movements[0]!=null && attackedPlayers[0].getPosition().reachable(movements[0]))
                        attackedPlayers[0].setPosition(movements[0]);
                    attacker.setPosition(movements[2]);
                    loaded=false;
                    done=true;
                }else if(movements[1].visible(attackedPlayers[0].getPosition()) && !movements[1].equals(attackedPlayers[0].getPosition()) && isPaid(attacker, payment, mode2)){
                    for(int i=0; i<2; i++)
                        attackedPlayers[0].receivedDamages(attacker);
                    if(movements[0]!=null && attackedPlayers[0].getPosition().reachable(movements[0]))
                        attackedPlayers[0].setPosition(movements[0]);
                    attacker.setPosition(movements[1]);
                    loaded=false;
                    done=true;
                }
            }else if(mode2.length==2 && ((mode2[0]==0 && mode2[1]==2) || (mode2[0]==2 && mode2[1]==0)) && attacker.canSee(attackedPlayers[0]) && !attacker.getPosition().equals(attackedPlayers[0].getPosition()) && isPaid(attacker, payment, mode2)){
                Player[] temp=attackedPlayers[0].getPosition().getPlayers();
                for(int i=0; i<2; i++)
                    attackedPlayers[0].receivedDamages(attacker);
                if(movements[0]!=null && attackedPlayers[0].getPosition().reachable(movements[0]))
                    attackedPlayers[0].setPosition(movements[0]);
                for(int i=0; i<temp.length; i++)
                    temp[i].receivedDamages(attacker);
                loaded=false;
                done=true;
            }else if(mode2.length==3 && ((mode2[0]==0 && mode2[1]==1 && mode2[2]==2) || (mode2[0]==0 && mode2[1]==2 && mode2[2]==1) || (mode2[0]==2 && mode2[1]==0 && mode2[2]==1)) && attacker.canSee(attackedPlayers[0]) && movements[1]!=null && attacker.getPosition().reachable(movements[1]) && !attacker.getPosition().equals(attackedPlayers[0].getPosition()) && isPaid(attacker, payment, mode2)){
                Player[] temp=attackedPlayers[0].getPosition().getPlayers();
                for(int i=0; i<2; i++)
                    attackedPlayers[0].receivedDamages(attacker);
                if(movements[0]!=null && attackedPlayers[0].getPosition().reachable(movements[0]))
                    attackedPlayers[0].setPosition(movements[0]);
                for(int i=0; i<temp.length; i++)
                    temp[i].receivedDamages(attacker);
                if(movements[2]!=null && movements[1].reachable(movements[2]))
                    attacker.setPosition(movements[2]);
                loaded=false;
                done=true;
            }else if(mode2.length==3 && ((mode2[0]==1 && mode2[1]==0 && mode2[2]==2) || (mode2[0]==1 && mode2[1]==2 && mode2[2]==0) || (mode2[0]==2 && mode2[1]==1 && mode2[2]==0)) && movements[1]!=null && attacker.getPosition().reachable(movements[1])){
                Player[] temp=attackedPlayers[0].getPosition().getPlayers();
                if(movements[2]!=null && movements[1].reachable(movements[2]) && movements[2].visible(attackedPlayers[0].getPosition()) && isPaid(attacker, payment, mode2)){
                    attacker.setPosition(movements[2]);
                    for(int i=0; i<2; i++)
                        attackedPlayers[0].receivedDamages(attacker);
                    if(movements[0]!=null && attackedPlayers[0].getPosition().reachable(movements[0]))
                        attackedPlayers[0].setPosition(movements[0]);
                    for(int i=0; i<temp.length; i++)
                        temp[i].receivedDamages(attacker);
                    loaded=false;
                    done=true;
                }else if(movements[1].visible(attackedPlayers[0].getPosition()) && isPaid(attacker, payment, mode2)){
                    attacker.setPosition(movements[1]);
                    for(int i=0; i<2; i++)
                        attackedPlayers[0].receivedDamages(attacker);
                    if(movements[0]!=null && attackedPlayers[0].getPosition().reachable(movements[0]))
                        attackedPlayers[0].setPosition(movements[0]);
                    for(int i=0; i<temp.length; i++)
                        temp[i].receivedDamages(attacker);
                    loaded=false;
                    done=true;
                }
            }
        }
        return done;
    }
}
