package Adrenaline.model;

/**
 * basic effect: Deal 2 damage to 1 target you can see.
 * with chain reaction: Deal 1 damage to a second target that
 * your first target can see.
 * with high voltage: Deal 2 damage to a third target that
 * your second target can see. You cannot use this effect
 * unless you first use the chain reaction.
 * Notes: This card constrains the order in which you can use
 * its effects. (Most cards don't.) Also note that each target
 * must be a different player
 */

public class WCTHOR extends WeaponCard{

    public WCTHOR(){
        super("T.H.O.R",new char[]{'b','r'},new char[]{'b','b'});
    }


}
