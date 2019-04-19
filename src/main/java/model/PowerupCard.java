package model;

public class PowerupCard {
    protected char colour;
    protected String name;
    protected Board board; //serve il collegamento a board per poi poter accedere alle posizioni
    //protected Player[] players; todo serve la lista dei giocatori per poter decidere su chi attivare gli effetti, se
            //todo mettiamo la lista dei giocatori nella classe Board qui non ce n'è bisogno, penso sia più comodo
            //todo averla nella classe board cosiì è piu semplice gestire i turni

    public PowerupCard(String name, char colour, Board b){
        this.name=name;
        this.colour=colour;
        this.board=b;
    }

    public char getColour() {
        return colour;
    }

    public void activate(Player p){ //p is the player that just used the powerup

    }
}
