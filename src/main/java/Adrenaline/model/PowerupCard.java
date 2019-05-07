package Adrenaline.model;

public class PowerupCard {
    protected char colour;
    protected String name;
    protected Board board;

    public PowerupCard(String name, char colour, Board b){
        this.name=name;
        this.colour=colour;
        this.board=b;
    }

    public char getColour() {
        return colour;
    }

    public String getName() {
        return name;
    }

    public void activate(Player p){ //p is the player that just used the powerup

    }
}