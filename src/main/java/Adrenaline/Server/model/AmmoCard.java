package Adrenaline.Server.model;

public class AmmoCard {
    private char[] value;

    public AmmoCard(char [] value){
        this.value=value;
    }

    public char[] getValue(){
        return value;
    }

    public void setValue(char[] value) {
        this.value = value;
    }
}
