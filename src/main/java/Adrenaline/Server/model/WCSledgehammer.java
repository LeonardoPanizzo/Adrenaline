package Adrenaline.Server.model;

import java.io.Serializable;

/**
 * basic mode: Deal 2 damage to 1 target on
 * your square.
 * in pulverize mode: Deal 3 damage to 1 target
 * on your square, then move that target 0, 1,
 * or 2 squares in one direction.
 * Notes: Remember that moves go through
 * doors, but not walls.
 */

public class WCSledgehammer extends WeaponCard implements Serializable {

    public WCSledgehammer(){
        super("SledgeHammer", new char[]{'y'}, new char[]{'r'},false);
    }

    @Override
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment){
        boolean done=false;
        if(isLoaded() && attackedPlayers.length==1 && attacker.getPosition().equals(attackedPlayers[0].getPosition())){
            if(mode1==0){
                for(int i=0; i<2; i++)
                    attackedPlayers[0].receivedDamages(attacker);
                loaded=false;
                done=true;
            }else if(mode1==1 && isPaid(attacker,payment)){
                if(movements == null || movements.length==0){
                    for(int i=0; i<3; i++)
                        attackedPlayers[0].receivedDamages(attacker);
                    loaded=false;
                    done=true;
                }else if(movements.length==1 && attacker.getPosition().reachable(movements[0])){
                    for(int i=0; i<3; i++)
                        attackedPlayers[0].receivedDamages(attacker);
                    attackedPlayers[0].setPosition(movements[0]);
                    loaded=false;
                    done=true;
                }else if(movements.length==2 && attacker.getPosition().reachable(movements)){
                    boolean sameX = attacker.getPosition().getCoordinate()[0]==movements[0].getCoordinate()[0] && movements[0].getCoordinate()[0]==movements[1].getCoordinate()[0];
                    boolean sameY = attacker.getPosition().getCoordinate()[1]==movements[0].getCoordinate()[1] && movements[0].getCoordinate()[1]==movements[1].getCoordinate()[1];
                    if(sameX || sameY){     //checks if it is moving in one direction
                        for(int i=0; i<3; i++)
                            attackedPlayers[0].receivedDamages(attacker);
                        attackedPlayers[0].setPosition(movements[1]);
                        loaded=false;
                        done=true;
                    }
                }
            }
        }
        return done;
    }
}
