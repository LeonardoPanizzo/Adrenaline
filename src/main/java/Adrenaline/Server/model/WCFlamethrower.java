package Adrenaline.Server.model;

/**
 * basic mode: Choose a square 1 move away and possibly a second square
 * 1 more move away in the same direction. On each square, you may
 * choose 1 target and give it 1 damage.
 * in barbecue mode: Choose 2 squares as above. Deal 2 damage to
 * everyone on the first square and 1 damage to everyone on the second
 * square.
 * Notes: This weapon cannot damage anyone in your square. However,
 * it can sometimes damage a target you can't see – the flame won't go
 * through walls, but it will go through doors. Think of it as a straight-line
 * blast of flame that can travel 2 squares in a cardinal direction.
 */

public class WCFlamethrower extends WeaponCard{

    public WCFlamethrower(){
        super("Flamethrower", new char[]{'r'}, new char[]{'y','y'});
    }

    @Override
    /**
     * in mode1==0 checks that the attackedplayer[i] is in the movements[i] to respect the fact that can be hit one
     *  player for position
     * onedirectionx and onedirectiony are used to see that the fire is shot in only one direction,
     *  so all the position have the same x coordinate or the same y coordinate
     */
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment) {
        boolean done = false;
        if(isLoaded() && movements.length>=1 && movements.length<=2){
            boolean closeposition=attacker.getPosition().reachable(movements[0]); //checks that the attacker position and the position in movement are close
            if(closeposition && movements.length==2)
                closeposition=movements[0].reachable(movements[1]);
            boolean onedirectionx=attacker.getPosition().getCoordinate()[0]==movements[0].getCoordinate()[0]; //checks that the x coordinate is the same for all the position
            if(onedirectionx && movements.length==2)
                onedirectionx=movements[0].getCoordinate()[0]==movements[1].getCoordinate()[0] && movements[1] != attacker.getPosition();
            boolean onedirectiony=attacker.getPosition().getCoordinate()[1]==movements[0].getCoordinate()[1];//checks that the y coordinate is the same for all the position
            if(onedirectiony && movements.length==2)
                onedirectiony=movements[0].getCoordinate()[1]==movements[1].getCoordinate()[1] && movements[1] != attacker.getPosition();


            if(mode1==0 && attackedPlayers.length>=1 && attackedPlayers.length<=2 && attackedPlayers.length==movements.length){
                boolean attackedposition = true; //checks that attackedplayers[i] is in the movements[i]
                for(int i=0; attackedposition && i<movements.length; i++)
                    attackedposition=attackedPlayers[i].getPosition().equals(movements[i]);
                if(attackedposition && closeposition && (onedirectionx || onedirectiony)){
                    for(int i=0; i<attackedPlayers.length; i++)
                        attackedPlayers[i].receivedDamages(attacker);
                    loaded=false;
                    done=true;
                }
            }else if(mode1==1){
                if(closeposition && (onedirectionx || onedirectiony) && isPaid(attacker, payment)){
                    Player[]temp0=movements[0].getPlayers();//contains all the players in movements[0]
                    for(int i=0; i<temp0.length; i++) {
                        temp0[i].receivedDamages(attacker);
                        temp0[i].receivedDamages(attacker);
                    }
                    if(movements.length==2){
                        Player[] temp1=movements[1].getPlayers();//contains all the players in movements[1]
                        for(int i=0; i<temp1.length; i++)
                            temp1[i].receivedDamages(attacker);
                    }
                    loaded=false;
                    done=true;
                }
            }
        }
        return done;
    }
}
