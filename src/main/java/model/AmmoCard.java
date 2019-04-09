package model;

//todo non sarebbe più coerente chiamare questa classe ammoCard?

public class AmmoCard {
    private char[] value;

    public AmmoCard(){

    }

    //in UML avevamo messo che questo metodo restituisce un intero, non credo sia comodo lavorarci
    public char[] getValue(){
        return value;
    }
}
