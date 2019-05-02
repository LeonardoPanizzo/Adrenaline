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

    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements){
        //viene chiamato il metodo delle sottoclassi
        return false;
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
