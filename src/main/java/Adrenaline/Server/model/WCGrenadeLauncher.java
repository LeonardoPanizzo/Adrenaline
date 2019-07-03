package Adrenaline.Server.model;

import java.io.Serializable;

/**
 * basic effect: Deal 1 damage to 1 target you can see. Then you may move
 * the target 1 square.
 * with extra grenade: Deal 1 damage to every player on a square you can
 * see. You can use this before or after the basic effect's move.
 * Notes: For example, you can shoot a target, move it onto a square with
 * other targets, then damage everyone including the first target.
 * Or you can deal 2 to a main target, 1 to everyone else on that square,
 * then move the main target. Or you can deal 1 to an isolated target and
 * 1 to everyone on a different square. If you target your own square,
 * you will not be moved or damaged.
 */

public class WCGrenadeLauncher extends WeaponCard implements Serializable {

    public WCGrenadeLauncher(){
        super("Grenade Launcher", new char[]{'r'}, new char[]{'0','r'},true);
    }

    @Override
    /*
    movements[0] the position where attacked.players[0] will be moved, movements[1] where the grenade hits
     */
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment){
        boolean done=false;
        if(isLoaded() && attackedPlayers.length==1 && attacker.canSee(attackedPlayers[0])){
            if(mode2.length==1 && mode2[0]==0){
                attackedPlayers[0].receivedDamages(attacker);
                if(movements!=null && movements.length==1 && attackedPlayers[0].getPosition().reachable(movements[0])){
                    attackedPlayers[0].setPosition(movements[0]);
                }
                loaded=false;
                done=true;
            }else if(mode2.length==2){
                if(mode2[0]==0 && mode2[1]==1 && movements.length==2 && attacker.getPosition().visible(movements[1]) && isPaid(attacker, payment)){
                    attackedPlayers[0].receivedDamages(attacker);
                    if(movements[0] != null && attackedPlayers[0].getPosition().reachable(movements[0])){
                        attackedPlayers[0].setPosition(movements[0]);
                    }
                    Player[] temp=movements[1].getPlayers();
                    for(int i=0; i<temp.length; i++){
                        temp[i].receivedDamages(attacker);
                    }
                    loaded=false;
                    done=true;
                }else if(mode2[0]==1 && mode2[1]==0 && movements.length==2 && attacker.getPosition().visible(movements[1]) && isPaid(attacker, payment)){
                    Player[] temp=movements[1].getPlayers();
                    for(int i=0; i<temp.length; i++) {
                        temp[i].receivedDamages(attacker);
                    }
                    attackedPlayers[0].receivedDamages(attacker);
                    if(movements[0] != null && attackedPlayers[0].getPosition().reachable(movements[0])){
                        attackedPlayers[0].setPosition(movements[0]);
                    }
                    loaded=false;
                    done=true;
                }
            }
        }
        return done;
    }
}
