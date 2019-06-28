package Adrenaline.Server.model;

/**
 * basic effect: Choose a square you can see, but not your
 * square. Call it "the vortex". Choose a target on the vortex
 * or 1 move away from it. Move it onto the vortex and give it
 * 2 damage.
 * with black hole: Choose up to 2 other targets on the
 * vortex or 1 move away from it. Move them onto the vortex
 * and give them each 1 damage.
 * Notes: The 3 targets must be different, but some might
 * start on the same square. It is legal to choose targets on
 * your square, on the vortex, or even on squares you can't
 * see. They all end up on the vortex.
 */

public class WCVortexcannon extends WeaponCard{

    public  WCVortexcannon(){
        super("Vortex Cannon", new char[]{'r','b'}, new char[]{'r'},true);
    }

    /*
    movements[0] is the position of the vortex
     */
    @Override
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment){
        boolean done=false;
        if(isLoaded() && attackedPlayers.length>=1 && movements.length>=1 && !attacker.getPosition().equals(movements[0]) && attacker.getPosition().visible(movements[0]) && ((attackedPlayers[0].getPosition().equals(movements[0]))||(attackedPlayers[0].getPosition().reachable(movements[0])))){
            if(mode2.length==1 && mode2[0]==0){
                attackedPlayers[0].setPosition(movements[0]);
                for(int i=0; i<2; i++)
                    attackedPlayers[0].receivedDamages(attacker);
                loaded=false;
                done=true;
            }else if(mode2.length==2 && mode2[0]==0 && mode2[1]==1 && attackedPlayers.length<=3){
                boolean playersavalaible=true; //used to see if all the players are close enough to the vortex
                for(int i=0; i<attackedPlayers.length && playersavalaible; i++)
                    playersavalaible=((attackedPlayers[i].getPosition().equals(movements[0]))||(attackedPlayers[i].getPosition().reachable(movements[0])));
                if(playersavalaible && isPaid(attacker,payment)){ //last check to see if the second option is possible to do
                    for(int i=0; i<attackedPlayers.length; i++){
                        attackedPlayers[i].setPosition(movements[0]);
                    }
                    attackedPlayers[0].receivedDamages(attacker);//attacked[0] receive two damages one here and one in the for loop with all the other players
                    for(int i=0; i<attackedPlayers.length; i++)
                        attackedPlayers[i].receivedDamages(attacker);
                    loaded=false;
                    done=true;
                }
            }
        }
        return done;
    }
}
