package Adrenaline.Server.model;

/**
 * basic mode: Deal 1 damage to every other player
 * on your square.
 * in reaper mode: Deal 2 damage to every other player
 * on your square.
 */

public class WCElectroscythe extends WeaponCard{

    public WCElectroscythe(){
        super("ElectroScythe", new char[]{'b'}, new char[]{'b','r'});
    }

    @Override
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment){
        boolean done=false;
        if(isLoaded()){
            Player[] tempatta=attacker.getPosition().getPlayers();
            if(mode1==0){
                for(int i=0; i<tempatta.length; i++)
                    tempatta[i].receivedDamages(attacker);
                loaded=false;
                done=true;
            }else if(mode1==1 && isPaid(attacker,payment)){
                for(int i=0; i<tempatta.length; i++) {
                    tempatta[i].receivedDamages(attacker);
                    tempatta[i].receivedDamages(attacker);
                }
                loaded=false;
                done=true;
            }
        }
        return done;
    }
}
