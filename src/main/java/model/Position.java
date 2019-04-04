package model;

import java.util.Vector;
import java.lang.*;

public class Position {
    //un contatore che dice la posizione dell'arma presa e dove sarà inserita quella nuova

    private char room;               //stanza di cui fa parte la posizione
    private int[] matr;             //coordinate della stanza
    private boolean door;           //se è vero la stanza ha una porta con un'altra stanza
    private boolean resetPoint;     //indica se la stanza è reset point e quindi se ci saranno munizioni o armi
    private Vector<Position> linked;       //posizioni a cui si arriva attraverso la porta, uso Vector perchè è sincronizzato
    private int ndoor;              //numero di stanze a cui la posizione è collegata
    private AmmoTile ammo;          //munizioni che si trovano più eventuali armi
    private WeaponCard[] arms;
    private int weaponSpot;         //quando pesco un'arma ricordo il posto da cui è stata presa per poi inserirci quella nuova
    private AmmoDeck ammoDeck;      //todo gli si dovrà passare come argomento del costruttore il deck in modo  che poi possa pescare

    //da modificare se door==false position door non serve
    //probabilmete visto che le posizioni sono fisse penso che sia meglio se le leggiamo da file prima della partita ogni volta
    public Position(int i, int j, char room, boolean door, boolean resetPoint){
        matr=new int[2];
        arms=new WeaponCard[3];
        matr[0]=i;
        matr[1]=j;
        this.room=room;
        this.door=door;
        this.ndoor=0;
        this.resetPoint=resetPoint;
        if(door){
            linked=new Vector<Position>();
        }
        /*
        if(resetPoint) {
            //pesca le 3 armi
        }
        else {
        //todo questo metodo potrà essere chiamato quando avrà l'attributo ammoDeck
        //    ammo=ammoDeck.pickUpAmmo();
        }
    */
    }

    public char getRoom(){
        return room;
    }

    public boolean isDoor(){
        return door;
    }

    public int[] getCoordinate() {
        return matr;
    }

    //prende in ingresso la posizione da aggiungere al vettore che contiene le stanze a cui si può accedere se la
    //posizione è porta
    public void setLinks(Position x){
        linked.add(x);
        ndoor++;
    }

    //prende in ingresso le posizione da aggiungere al vettore che contiene le stanze a cui si può accedere se la
    //posizione è porta
    public void setLinks(Position[] x){
        for(int i=0; i<x.length; i++)
            linked.add(x[i]);
        ndoor=ndoor+x.length;
    }

    //mostra le armi per permettere al giocatore di fare la scelta
    public WeaponCard[] showWeapon(){
        return arms;
    }


    //todo i due metodi qui sotto dovranno essere sincronizzati?
    //collegato a showWeapon(), dopo ave visto le armi disponibili il giocatore indica l'arma che vuole prendere
    //indicandone la posizione
    public WeaponCard chooseArm(int i){
        weaponSpot=i;   //tiene in memoria la posizione da cui è stata presa l'arma per sapere dove inserire l'arma che riceve dopo
        return arms[i];
    }

    //dopo aver preso un'arma con chooseArm() il giocatore dovrà restituire un'arma che verrà messa nella posizione
    //dell'arma presa precedentemente
    public void giveWeapon(WeaponCard x){
        arms[weaponSpot]=x;
        return;
    }

    //restituisce al giocatore un carta munizioni e ne mette un'altra al suo post
    //todo l'analisi della carta, ovvero quali munizioni dà o se dà una carta potenziamento viene fatto nella classe Action
    /*
    public AmmoTile pickUpAmmo(){
        AmmoTile a=ammo;
        ammo=ammoDeck.pickUpAmmo();
        return a;
    }
    */
    //la posizione passata come paramentro è visibile da this?
    //ipotizzo che la posizione passata come argomento esiste
    public boolean visible(Position x){
        boolean vis=false;
        if(this.room==x.room)
            vis=true;       //se la posizione è nella stessa stanza imposto visibile a true
        for(int i=0; (!vis)&&(i<ndoor);i++)         //controlla le posizione collegate tramite la porta
            if(linked.elementAt(i).getRoom()==x.room)       //controlla che il parametro passato abbia il colore di una delle stanze collegate tramite la porta
                vis=true;
        return vis;
    }

    //la posizione passata come parametro è raggiungibile in un passo?
    //ipotizzo che la posizione passata come argomento esiste
    public boolean reachable(Position x){
        boolean reac=false;
        if((this.matr[0]==x.matr[0]&&((this.matr[1]==x.matr[1]+1)||(this.matr[1]==x.matr[1]-1)))||
                (this.matr[1]==x.matr[1]&&((this.matr[0]==x.matr[0]+1)||(this.matr[0]==x.matr[0]-1)))){     //controlla che la distanza fra this e x sia 1
            if(visible(x))      //so che x dista 1 da this, quindi se è visibile è anche raggiungibile
                reac=true;
        }
        return reac;
    }
}