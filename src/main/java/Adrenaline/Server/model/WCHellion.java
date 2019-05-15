package Adrenaline.Server.model;

/**
 * basic mode: Deal 1 damage to 1 target you can see at least
 * 1 move away. Then give 1 mark to that target and everyone
 * else on that square.
 * in nano-tracer mode: Deal 1 damage to 1 target you can
 * see at least 1 move away. Then give 2 marks to that target
 * and everyone else on that square.
 */
public class WCHellion extends WeaponCard{

    public WCHellion() {
        super("Hellion", new char[]{'r','y'}, new char[]{'r'});
    }

    @Override
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment){
        boolean done=false;
        if(isLoaded() && attackedPlayers.length==1 && attacker.canSee(attackedPlayers[0]) && !attacker.getPosition().equals(attackedPlayers[0].getPosition())){
                if(mode1==0) {
                    attackedPlayers[0].receivedDamages(attacker);
                    Player[] playertomark=attackedPlayers[0].getPosition().getPlayers();
                    for(int i=0; i<playertomark.length; i++){
                        attackedPlayers[i].setMarksReceived(attacker, 1);
                        attacker.setMarksGiven(attackedPlayers[i], 1);
                    }
                    done = true;
                    loaded = false;
                }else if(mode1==1 && isPaid(attacker, payment)){
                        attackedPlayers[0].receivedDamages(attacker);
                        Player[] playertomark=attackedPlayers[0].getPosition().getPlayers();
                        for(int i=0; i<playertomark.length; i++) {
                            attackedPlayers[i].setMarksReceived(attacker, 2);
                            attacker.setMarksGiven(attackedPlayers[i], 2);
                        }
                        done = true;
                        loaded = false;
                }
        }
        return done;
    }
}
