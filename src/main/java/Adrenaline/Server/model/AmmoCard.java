package Adrenaline.Server.model;

import java.io.Serializable;

public class AmmoCard implements Serializable {
    private char[] value;

    /**
     * Costructor for AmmoCard. Request a char array with three values: ammo and powerup (if present).
     * @param value value of ammo card
     */
    public AmmoCard(char [] value){
        this.value=value;
    }

    /**
     * A method to return the value of the ammo card. 'r' means one red ammo; the same for 'b' and 'y' for yellow and
     * blue mutnitions. 'p' is to indicate that one power up is to pick up.
     *
     * @return value of ammo card
     */
    public char[] getValue(){
        return value;
    }

    /**
     * A method to set the value of the ammo card.
     *
     * @param value char array to set the value of ammo card
     */
    public void setValue(char[] value) {
        this.value = value;
    }

    /**
     * Method to transform the ammo card's value into string.
     *
     * @return the string that shows the ammo card's value
     */
    public String toString(){
        String info=" ";
        for(int i=0; i<3; i++){     //every AmmoCard has 3 elements
            if(value[i]=='r'){
                info=info+"red ";
            }else if(value[i]=='b'){
                info=info+"blue ";
            } if(value[i]=='y'){
                info=info+"yellow ";
            } if(value[i]=='p'){
                info=info+"powerup ";
            }
        }
        return info;
    }
}
