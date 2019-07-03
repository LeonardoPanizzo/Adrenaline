package Adrenaline.Server.model;

import java.io.Serializable;

/**
 * basic mode: Choose a room you can see, but not the room
 * you are in. Deal 1 damage to everyone in that room.
 * in cozy fire mode: Choose a square exactly one move
 * away. Deal 1 damage and 1 mark to everyone on that
 * square.
 */

public class WCFurnace extends WeaponCard implements Serializable {

    public WCFurnace(){
        super("Furnace", new char[]{'r','b'}, new char[]{},false);
    }

    /*
    movements contains the hit positions
     */
    @Override
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment) {
        boolean done = false;
        if(isLoaded() && movements.length>=1){
            if(mode1==0){
                boolean sameroom=true; //all the positions in movements are in the same room
                boolean attackerseeroom=attacker.getPosition().visible(movements[movements.length-1]); //the attacker can see all the positions that are hit
                for(int i=0; i<movements.length-1 && sameroom && attackerseeroom; i++) {
                    sameroom = movements[i].getRoom() == movements[i + 1].getRoom();
                    attackerseeroom=attacker.getPosition().visible(movements[i]);
                }
                boolean differentroom=(attacker.getPosition().getRoom()!=movements[0].getRoom()); //checks if attacker is not on the hit room
                if(sameroom && differentroom && attackerseeroom){
                    attackall(attacker, movements);
                    loaded=false;
                    done=true;
                }
            }else if(mode1==1 && movements.length==1 && attacker.getPosition().visible(movements[0]) && !attacker.getPosition().equals(movements[0])){
                Player[] temp=movements[0].getPlayers();
                for(int i=0; i<temp.length; i++){
                    temp[i].receivedDamages(attacker);
                    temp[i].setMarksReceived(attacker,1);
                    attacker.setMarksGiven(temp[i],1);
                }
                loaded=false;
                done=true;
            }
        }
        return done;
    }
}
