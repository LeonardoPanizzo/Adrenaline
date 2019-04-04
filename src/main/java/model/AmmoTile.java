package model;

//todo non sarebbe più coerente chiamare questa classe ammoCard?

public class AmmoTile {
    private char[] value;

    public AmmoTile(){

    }

    //in UML avevamo messo che questo metodo restituisce un intero, non credo sia comodo lavorarci
    public char[] getValue(){
        return value;
    }
}
