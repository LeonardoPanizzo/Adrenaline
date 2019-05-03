package model;

public class WCHeatseeker extends WeaponCard{

    public WCHeatseeker(Board b){
        super("HeatSeeker",new char[]{'r','r','y'},b);

    }
    //andre: penso vada aggiunto qui: @Override
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements){
        if(this.isLoaded() && attackedPlayers.length==1 && !(attacker.getPosition().visible(attackedPlayers[0].getPosition()))){
            for(int i=0;i<3;i++){
                attackedPlayers[0].receivedDamages(attacker.getNumber());
            }
            loaded=false;
            return true;
        }
        return false;
    }
}
