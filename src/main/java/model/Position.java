package model;

public class Position {
    int room;               //stanza di cui fa parte la posizione
    int[] matr;             //coordinate della stanza
    boolean door;           //se è vero la stanza ha una poarta con un'altra stanza
    int positionDoor;       //up,down,right,left, todo DIVRà ESSERE UN VETTORE PERCHè UNA STANZA PUò AVERE PIù PORTE
    boolean resetPoint;
    Position linked;       //posizione a cui si arriva attraverso la porta, non so se conviene avere questa variabile o calcolare la posizione collegata tramite positionDoor
    PowerupCard powerup;
    WeaponCard[] arms;


    //da modificare se door==false position door non serve
    //probabilmete visto che le posizioni sono fisse penso che sia meglio se le leggiamo da file prima della partita ogni volta
    public Position(int i, int j, int room, boolean door, int positionDoor, boolean resetPoint){
        matr=new int[2];
        arms=new WeaponCard[3];
        matr[0]=i;
        matr[1]=j;
        this.room=room;
        this.door=door;
        this.positionDoor=positionDoor;
        this.resetPoint=resetPoint;
        if(resetPoint) {
            //pesca le 3 armi
        }
        else {
            //pesca il potenziamento
        }
    }

    public int getRoom(){
        return room;
    }

    public PowerupCard getPowerup(){
        PowerupCard a=powerup;
        powerup=pesca();//metodo da implementare
        return a;
    }

    //la posizione passata come paramentro è visibile?
    //todo CONSIDERARE CHE PUò ESSERCI PIù DI UNA STANZA COLLEGATA
    public boolean visible(Position x){
        if((this.room==x.room)||(this.door && this.linked.getRoom()==x.room))
            return true;
        else
            return false;
    }

    //la posizione passata come parametro è raggiungibile in un passo?
    //ipotizzo che la posizione passata come argomento esiste
    //todo CONSIDERARE CHE PUò ESSERCI PIù DI UNA STANZA COLLEGATA
    public boolean reachable(Position x){
        if(this.room==x.room || ((this.door)&&(this.linked.room==x.room))){
            if(this.matr[0]==x.matr[0]&&((this.matr[1]==x.matr[1]+1)||(this.matr[1]==x.matr[1]-1)))
                return true;
            if(this.matr[1]==x.matr[1]&&((this.matr[0]==x.matr[0]+1)||(this.matr[0]==x.matr[0]-1)))
                return true;
        }else
            return false;
    }
}