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

    private void damage(Player attacker, Player attacked, int x){
        for(int i=0; i<x; i++)
            attacked.receivedDamages(attacker);
    }

    private void move(Player attacker, Position[] movemets){
        for(int i=0; i<movemets.length; i++){
            attacker.setPosition(movemets[i]);
        }
    }

    @Override
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment) {
    boolean done=false;
    if(isLoaded() && mode2!=null && attackedPlayers.length==1){
        if(mode2.length==1 && mode2[0]==0 && attacker.canSee(attackedPlayers[0])){
            damage(attacker, attackedPlayers[0],2);
            this.loaded=false;
            done=true;
        }else if(mode2.length==2 && mode2[0]==0 && mode2[1]==2 && attacker.canSee(attackedPlayers[0])){//todo:aggiungere il pagamento se passo qualcosa che ha un valore 0 come reagisce il metodo?
            damage(attacker, attackedPlayers[0],3);
            this.loaded=false;
            done=true;
        }else if(mode2.length==2 && ((mode2[0]==0 && mode2[1]==1)||(mode2[0]==1 && mode2[1]==0)) && movements.length<=2){
            if(mode2[0]==0 && attacker.canSee(attackedPlayers[0]) && attacker.getPosition().reachable(movements)){
                damage(attacker, attackedPlayers[0],2);
                move(attacker,movements);
                this.loaded=false;
                done=true;
            }else if(mode2[0]==1 && attacker.getPosition().reachable(movements) && movements[movements.length-1].visible(attackedPlayers[0].getPosition())){
                move(attacker,movements);
                damage(attacker, attackedPlayers[0],2);
                this.loaded=false;
                done=true;
            }
        }else if(mode2.length==3 && movements.length<=2){
            if(((mode2[0]==0 && mode2[1]==2 && mode2[2]==1) || (mode2[0]==0 && mode2[1]==1 && mode2[2]==2)) && attacker.canSee(attackedPlayers[0]) && attacker.getPosition().reachable(movements)){
                damage(attacker, attackedPlayers[0],3);
                move(attacker,movements);
                this.loaded=false;
                done=true;
            }else if(mode2[0]==1 && mode2[1]==0 && mode2[2]==2 && attacker.getPosition().reachable(movements) && movements[movements.length-1].visible(attackedPlayers[0].getPosition())){
                move(attacker,movements);
                damage(attacker, attackedPlayers[0],3);
                this.loaded=false;
                done=true;
            }
        }
    }
    return done;
    }
}
