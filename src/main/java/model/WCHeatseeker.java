package model;

/**
 * effect: Choose 1 target you cannot see and deal 3 damage to it.
 * Notes: Yes, this can only hit targets you cannot see
 */
public class WCHeatseeker extends WeaponCard{

    public WCHeatseeker(){
        super("HeatSeeker",new char[]{'r','r','y'});

    }
    @Override
    /*
    if checks that there is only one target,that the weapon is loaded and that the target isn't visible from the attacker
     */
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements){
        if(this.isLoaded() && attackedPlayers.length==1 && !(attacker.getPosition().visible(attackedPlayers[0].getPosition()))){
            for(int i=0;i<3;i++){
                attackedPlayers[0].receivedDamages(attacker.getNumber());
            }
            loaded=false;
            return true;
        }
        return false;
    }
}
