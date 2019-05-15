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
        if(isLoaded()){
            if(attackedPlayers.length==1 && attacker.canSee(attackedPlayers[0]) && !attacker.getPosition().reachable(attackedPlayers[0].getPosition())){//checks if the target is visible and not in the same position as the attacker
                if(mode1==0) {
                    attackedPlayers[0].receivedDamages(attacker);
                    attackedPlayers[0].setMarksReceived(attacker, 1);
                    attacker.setMarksGiven(attackedPlayers[0], 1);
                    //todo: dare i marchi agli altri giocatori che si trovano nella stessa posizione di attackedplayers[0]
                    // [Andrea] penso convenga considereare passati come parametri i giocatori che ricevono il marchio
                    done = true;
                    loaded = false;
                }else if(mode1==1){
                    if(isPaid(attacker, payment)){
                        attackedPlayers[0].receivedDamages(attacker);
                        attackedPlayers[0].setMarksReceived(attacker, 2);
                        attacker.setMarksGiven(attackedPlayers[0], 2);
                        //todo: dare i marchi agli altri giocatori che si trovano nella stessa posizione di attackedplayers[0]
                        done = true;
                        loaded = false;
                    }
                }
            }
        }
        return done;
    }
}
