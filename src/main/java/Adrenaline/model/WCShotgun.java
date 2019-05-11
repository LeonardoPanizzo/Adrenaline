package Adrenaline.model;

/**
 * basic mode: Deal 3 damage to 1 target on
 * your square. If you want, you may then move
 * the target 1 square.
 * in long barrel mode: Deal 2 damage to
 * 1 target on any square exactly one move
 * away.
 */

public class WCShotgun extends WeaponCard{

    public WCShotgun(){
        super("Shotgun", new char[]{'y','y'}, new char[]{});
    }

    @Override
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment) {
        boolean done = false;
        if(isLoaded()){
            if(mode1==0 && attackedPlayers.length==1 && attacker.getPosition().equals(attackedPlayers[0].getPosition())){
                for(int i=0; i<3; i++)
                    attackedPlayers[0].receivedDamages(attacker);
                loaded=false;
                done=true;
                if(movements!=null && movements.length==1){ //todo: se l'input del movimento è sbagliato l'attacco viene fatto comunque ma non viene spostato l'attaccato
                    if(attackedPlayers[0].getPosition().reachable(movements[0]))
                        attackedPlayers[0].setPosition(movements[0]);
                }
            }else if(mode1==1 && attackedPlayers.length==1 && attacker.getPosition().reachable(attackedPlayers[0].getPosition())){//checks if the attacked is one position away from the attacker
                for(int i=0; i<2; i++){
                    attackedPlayers[0].receivedDamages(attacker);
                }
                loaded=false;
                done=true;
            }
        }
        return done;
    }
}
