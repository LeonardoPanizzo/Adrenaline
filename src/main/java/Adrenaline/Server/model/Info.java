package Adrenaline.Server.model;

public class Info{
    private Board board;
    private Player[] players;

    public Info(Board b, Player[] p){
        this.board=b;
        this.players=p;
    }

    public Board getBoard(){
        return board;
    }

    public Player[] getPlayers(){
        return players;
    }
}

