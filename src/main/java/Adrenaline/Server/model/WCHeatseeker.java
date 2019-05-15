package Adrenaline.Server.model;

/**
 * effect: Choose 1 target you cannot see and deal 3 damage to it.
 * Notes: Yes, this can only hit targets you cannot see
 */
public class WCHeatseeker extends WeaponCard{

    public WCHeatseeker(){
        super("HeatSeeker",new char[]{'r','r','y'}, new char[]{});

    }
    @Override
    /*
    if checks that there is only one target,that the weapon is loaded and that the target isn't visible from the attacker
     */
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment){
        boolean done=false;
        if(this.isLoaded() && attackedPlayers.length==1 && !(attacker.canSee(attackedPlayers[0]))){
            for(int i=0;i<3;i++){
                attackedPlayers[0].receivedDamages(attacker);
            }
            loaded=false;
            done=true;
        }
        return done;
    }
}
