package Adrenaline.Server.model;

import java.io.Serializable;

public class PowerupCard implements Serializable {
    protected char colour;
    protected String name;

    /**
     * PowerupCard constructor. A power up has a name and a color.
     *
     * @param name the card's name
     * @param colour the card's color
     */
    public PowerupCard(String name, char colour){
        this.name=name;
        this.colour=colour;
    }

    /**
     * Method to have back the card's color
     *
     * @return card's color
     */
    public char getColour() {
        return colour;
    }

    /**
     * Method to have back the card's name
     *
     * @return the card's name
     */
    public String getName() {
        return name;
    }

    /**
     * Abstract method to use all power ups
     *
     * @param p1 player who use the power up card
     * @param p2 player attacked
     * @param squares position where move p2 or p1
     * @param payment payment in ammo
     * @return true if all goes right
     */
    public boolean use(Player p1, Player p2, Position[] squares, char payment){
        return false;
    }
}