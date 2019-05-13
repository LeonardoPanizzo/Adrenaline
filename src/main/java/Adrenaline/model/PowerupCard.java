package Adrenaline.model;

public class PowerupCard {
    protected char colour;
    protected String name;

    public PowerupCard(String name, char colour){
        this.name=name;
        this.colour=colour;
    }

    public char getColour() {
        return colour;
    }

    public String getName() {
        return name;
    }

    public boolean use(Player p1, Player p2, Position[] squares, char payment){
        return false;
    }
}