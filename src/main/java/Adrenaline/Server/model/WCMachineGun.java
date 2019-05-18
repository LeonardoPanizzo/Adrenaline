package Adrenaline.Server.model;

/**
 * basic effect: Choose 1 or 2 targets you can see and deal
 * 1 damage to each.
 * with focus shot: Deal 1 additional damage to one of those
 * targets.
 * with turret tripod: Deal 1 additional damage to the other
 * of those targets and/or deal 1 damage to a different target
 * you can see.
 * Notes: If you deal both additional points of damage,
 * they must be dealt to 2 different targets. If you see only
 * 2 targets, you deal 2 to each if you use both optional
 * effects. If you use the basic effect on only 1 target, you can
 * still use the the turret tripod to give it 1 additional damage.
 */

public class WCMachineGun extends WeaponCard{

    public WCMachineGun(){
        super("Machine Gun", new char[]{'b','r'}, new char[]{'0','y','b'});
    }

    @Override
    /*
    mode2[1] select attackedplayers[0], mode2[2] select attackedplayers[2] if attackedplayers.length==3 otherwise select attackedplayers[1]
     */
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment) {
        boolean done = false;
        if(isLoaded() && attackedPlayers.length>=1 && attackedPlayers.length<=3 && mode2.length>=1){
            if(mode2.length==1 && (mode2[0]==0 && attackedPlayers.length==1 || attackedPlayers.length == 2) && attacker.canSee(attackedPlayers[0]) && attacker.canSee(attackedPlayers[1])){
                for(int i=0; i<attackedPlayers.length; i++) {
                    attackedPlayers[i].receivedDamages(attacker);
                }
                loaded=false;
                done=true;
            }else if(mode2.length==2){
                if((mode2[0]==0 && mode2[1]==1)||(mode2[0]==1 && mode2[1]==0)){
                    boolean allvisible=true;
                    for(int i=0; i<attackedPlayers.length && allvisible; i++)
                        allvisible=attacker.canSee(attackedPlayers[i]);
                    if(allvisible && isPaid(attacker, payment, mode2)){
                        attackedPlayers[0].receivedDamages(attacker);//optional effect n1
                        for(int i=0; i<attackedPlayers.length; i++)
                            attackedPlayers[i].receivedDamages(attacker);
                        loaded=false;
                        done=true;
                    }
                }else if((mode2[0]==0 && mode2[1]==2)||(mode2[0]==2 && mode2[1]==0) && attackedPlayers.length>=2){
                    boolean allvisible=true;
                    for(int i=0; i<attackedPlayers.length && allvisible; i++)
                        allvisible=attacker.canSee(attackedPlayers[i]);
                    if(allvisible && isPaid(attacker, payment, mode2)){
                        for(int i=0; i<attackedPlayers.length; i++)
                            attackedPlayers[i].receivedDamages(attacker);
                        attackedPlayers[attackedPlayers.length-1].receivedDamages(attacker); //optional effect n2
                        loaded=false;
                        done=true;
                    }
                }
            }else if(mode2.length==3 && attackedPlayers.length>=2){
                if((mode2[0]==0 && mode2[1]==1 && mode2[2]==2)||(mode2[0]==0 && mode2[1]==2 && mode2[2]==1)||(mode2[0]==1 && mode2[1]==0 && mode2[2]==2)||(mode2[0]==1 && mode2[1]==2 && mode2[2]==0)||(mode2[0]==2 && mode2[1]==0 && mode2[2]==1)||(mode2[0]==2 && mode2[1]==1 && mode2[2]==0)){
                    boolean allvisible=true;
                    for(int i=0; i<attackedPlayers.length && allvisible; i++)
                        allvisible=attacker.canSee(attackedPlayers[i]);
                    if(allvisible && isPaid(attacker, payment, mode2)){
                        attackedPlayers[0].receivedDamages(attacker);//optional effect n1
                        attackedPlayers[attackedPlayers.length-1].receivedDamages(attacker); //optional effect n2
                        for(int i=0; i<attackedPlayers.length; i++)
                            attackedPlayers[i].receivedDamages(attacker);
                        loaded=false;
                        done=false;
                    }
                }
            }
        }
        return done;
    }
}

