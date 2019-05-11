package Adrenaline.model;

/**
 * basic mode: Deal 1 damage and 2 marks to
 * 1 target you can see.
 * in scanner mode: Choose up to 3 targets you
 * can see and deal 1 mark to each.
 * Notes: Remember that the 3 targets can be
 * in 3 different rooms.
 */
public class WCZX2 extends WeaponCard{

    public WCZX2(){
        super("ZX-2", new char[]{'y','r'}, new char[]{});
    }

    @Override
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment) {
        boolean done=false;
        if(isLoaded()){
            if(mode1==0 && attackedPlayers.length==1 && attacker.getPosition().visible(attackedPlayers[0].getPosition())){
                attackedPlayers[0].receivedDamages(attacker);
                attackedPlayers[0].setMarksReceived(attacker,2);
                attacker.setMarksGiven(attackedPlayers[0], 2);
                loaded=false;
                done=true;
            }else if(mode1==1 && attackedPlayers.length>=1 && attackedPlayers.length<=3){
                boolean allvisible=true;
                for(int i=0; i<attackedPlayers.length && allvisible; i++){
                    allvisible=attacker.getPosition().visible(attackedPlayers[i].getPosition());
                }
                if(allvisible){
                    for(int i=0; i<attackedPlayers.length; i++){
                        attackedPlayers[i].setMarksReceived(attacker,1);
                        attacker.setMarksGiven(attackedPlayers[i], 1);
                        loaded=false;
                        done=true;
                    }
                }
            }
        }
        return done;
    }
}