package Adrenaline.model;

/**
 * basic effect: Deal 2 damage to 1 target you can see.
 * with phase glide: Move 1 or 2 squares. This effect can be
 * used either before or after the basic effect.
 * with charged shot: Deal 1 additional damage to your
 * target.
 * Notes: The two moves have no ammo cost. You don't have
 * to be able to see your target when you play the card.
 * For example, you can move 2 squares and shoot a target
 * you now see. You cannot use 1 move before shooting and
 * 1 move after.
 */

public class WCPlasmaGun extends WeaponCard{
    public WCPlasmaGun(){
        super("PlasmaGun",new char[]{'b','y'},new char[]{'0','b'});
    }

    private void damage(Player attacker, Player attacked){
        for(int i=0; i<2; i++)
            attacked.receivedDamages(attacker);
    }

    @Override
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment) {
    boolean done=false;
    if(isLoaded() && mode2!=null &&attackedPlayers.length==1){
        if(mode2.length==1 && mode2[0]==0 && attacker.canSee(attackedPlayers[0])){


        }
    }
    return done;
    }
}
