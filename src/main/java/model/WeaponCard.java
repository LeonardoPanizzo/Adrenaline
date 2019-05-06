package model;

public class WeaponCard {
    protected String name;
    protected char[] costs;
    protected boolean loaded;


    public WeaponCard(String name, char[] costs) {
        this.name = name;
        this.costs = costs;
        loaded = true;
    }

    public String getName() {
        return name;
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
        return costs.clone();           //andrea: altrimenti si ritorna l'attributo e può essere modificato!
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}
