package model;

/**
 * basic effect: Deal 2 damage and 1 mark to 1 target you can see.
 * with second lock: Deal 1 mark to a different target you can see.
 */
public class WCLockRifle extends WeaponCard{
    public WCLockRifle(){
        super("LockRifle",new char[]{'b','b'});
    }

    @Override
    /*
    if checks that there 1 or 2 hitten players (the second one is hitten by an optional effect) and that the first target is visible
     */
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements) {
        boolean done=false;
        if(this.isLoaded() && attackedPlayers.length>=1 && attackedPlayers.length<=2 && attacker.getPosition().visible(attackedPlayers[0].getPosition())){
            if(mode2.length==1){//checks if it is only the basic effect
                for(int i=0;i<2;i++)
                    attackedPlayers[0].receivedDamages(attacker.getNumber());
                    //dare un marcgio a attacked[0]
                done=true;
            }else if(attackedPlayers.length==2 && mode2.length==2 &&((mode2[0]==0 && mode2[1]==1)||(mode2[0]==1 &&mode2[1]==0))){ //checks if there is the basic effect and the optional one
                   if(attacker.getPosition().visible(attackedPlayers[1].getPosition())){//checks if the second player is visible
                       for(int i=0;i<2;i++)
                           attackedPlayers[0].receivedDamages(attacker.getNumber());
                       //dare un marchio a attacked[0] e uno a attacked [1]
                       done=true;
                   }
            }
        }
        return done;
    }
}
