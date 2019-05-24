package Adrenaline.Server.model;

/**
 * basic mode: Choose up to 3 targets on
 * different squares, each exactly 1 move away.
 * Deal 1 damage to each target.
 * in tsunami mode: Deal 1 damage to all
 * targets that are exactly 1 move away.
 */

public class WCShockwave extends WeaponCard{

    public WCShockwave(){
        super("Shock Wave", new char[]{'y'}, new char[]{'y'});
    }

    /*
    in mode1==0 it works with attacked players, if mode1==1 works with movements
     */
    @Override
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment) {
        boolean done = false;
        if(isLoaded()){
            if(mode1==0 && attackedPlayers != null && attackedPlayers.length>=1 && attackedPlayers.length<=3){
                boolean differentposition=!(attacker.getPosition().equals(attackedPlayers[0].getPosition())); //checks that all the players are in different positions
                for(int i=0; i<attackedPlayers.length && differentposition; i++){
                    for(int j=i+1; j<attackedPlayers.length && differentposition; j++){
                        differentposition=!(attackedPlayers[i].getPosition().equals(attackedPlayers[j].getPosition()));
                    }
                }
                boolean correctattacker=true; //checks that the attackedplayers are reachable for the attacker but are not in the same position
                for(int i=0; i<attackedPlayers.length && correctattacker; i++){
                    correctattacker=attacker.getPosition().reachable(attackedPlayers[i].getPosition());
                }
                if(differentposition && correctattacker){
                    for(int i=0; i<attackedPlayers.length; i++)
                        attackedPlayers[i].receivedDamages(attacker);
                    loaded=false;
                    done=true;
                }
            }else if(mode1==1 && movements.length>=1){
                boolean close=true;//checks if the movements position are reachable for the attacker
                for(int i=0; close && i<movements.length; i++)
                    close=attacker.getPosition().reachable(movements[i]);
                boolean differentposition=!(attacker.getPosition().equals(movements[0])); //checks that all the positions passed are different from each other
                for(int i=0; differentposition && i<movements.length; i++){
                    for(int j=i; j<movements.length; j++){
                        differentposition=!(movements[i].equals(movements[j]));
                    }
                }
                if(movements.length==4) {
                    attackall(attacker, movements);
                    loaded = false;
                    done = true;
                }
            }
        }
        return done;
    }
}
