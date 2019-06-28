package Adrenaline.Server.model;

/**
 * basic effect: Deal 2 damage to 1 target you can see.
 * with chain reaction: Deal 1 damage to a second target that
 * your first target can see.
 * with high voltage: Deal 2 damage to a third target that
 * your second target can see. You cannot use this effect
 * unless you first use the chain reaction.
 * Notes: This card constrains the order in which you can use
 * its effects. (Most cards don't.) Also note that each target
 * must be a different player
 */

public class WCTHOR extends WeaponCard{

    public WCTHOR(){
        super("T.H.O.R",new char[]{'b','r'},new char[]{'0','b','b'},true);
    }

    @Override
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment){
        boolean done=false;
        if(isLoaded() && attackedPlayers.length>=1 && attackedPlayers.length<=3 && mode2 != null && mode2.length>=1){
            if(mode2.length==1 && mode2[0]==0 && attackedPlayers.length==1 && attacker.canSee(attackedPlayers[0])){
                for (int i = 0; i < 2; i++)
                    attackedPlayers[0].receivedDamages(attacker);
                loaded=false;
                done=true;
            }else if(mode2.length==2 && attackedPlayers.length==2 && mode2[0]==0 && mode2[1]==1 && attacker.canSee(attackedPlayers[0]) && attackedPlayers[0].canSee(attackedPlayers[1]) && isPaid(attacker, payment, mode2)){
                for (int i = 0; i < 2; i++)
                    attackedPlayers[0].receivedDamages(attacker);
                attackedPlayers[1].receivedDamages(attacker);
                loaded=false;
                done=true;
            }else if(mode2.length==3 && attackedPlayers.length==3 && mode2[0]==0 && mode2[1]==1 && mode2[2]==2 && attacker.canSee(attackedPlayers[0]) && attackedPlayers[0].canSee(attackedPlayers[1]) && attackedPlayers[1].canSee(attackedPlayers[2]) && isPaid(attacker, payment, mode2)){
                for (int i = 0; i < 2; i++) {
                    attackedPlayers[0].receivedDamages(attacker);
                    attackedPlayers[2].receivedDamages(attacker);
                }
                attackedPlayers[1].receivedDamages(attacker);
                loaded=false;
                done=true;
            }
        }
        return done;
    }
}
