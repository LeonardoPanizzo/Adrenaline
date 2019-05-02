package model;

public class WeaponCard {
    protected String name;
    protected char[] costs;
    protected Board board;
    protected boolean loaded;
    protected int damages;      //number of damages given
    protected int marks;        //number of marks given

    public WeaponCard(String name, char[] costs, Board b) {
        this.name = name;
        this.costs = costs;
        this.board = b;
        loaded = true;
    }

    public void reload(){
        this.loaded=true;
    }

    public char[] getCostTaking(){
        char[] temp=new char[5];
        for(int i=1; i<=4; i++)
            temp[i]=costs[i];
        return temp;
    }

    public int[] attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements){
        //todo implementare il metodo
        //todo tenere traccia dei danni assegnati ai giocatori (serve per sapere quante volte chiamare receivedDamage) e dei marchi assegnati
        //todo: l'ideale sarebbe restituire un arraya di due valori: danni fatti e maechi assegnati.
        int[] damagesAndMarks = {this.damages, this.marks};
        return damagesAndMarks;
    }

    public char[] getCostReloading(){
        return costs;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}
