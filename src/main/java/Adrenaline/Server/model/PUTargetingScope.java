package Adrenaline.Server.model;

import java.io.Serializable;

/**
 * You may play this card when you are dealing damage to one or more targets. Pay 1 ammo cube of any color.
 * Choose 1 of those targets and give it an extra point of damage.
 * Note: You cannot use this to do 1 damage to a target that is receiving only marks.
 */

public class PUTargetingScope extends PowerupCard implements Serializable {

    public PUTargetingScope(char colour){
        super("targeting scope", colour);
    }

    public boolean use(Player p1, Player p2, Position[] squares, char payment){
        boolean done=false;
        char[] paym=new char[1];
        paym[0]=payment;
        if(p1.updateAmmo(paym)){
            p2.receivedDamages(p1);
            done=true;
        }
        return done;
    }
}