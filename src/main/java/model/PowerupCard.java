package model;

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

    public void activate(Player p){

    }
}
