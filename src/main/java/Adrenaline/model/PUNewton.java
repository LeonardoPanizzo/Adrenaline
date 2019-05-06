package Adrenaline.model;

/**
 * Using this powerup a player can move another player's figure of 1 or 2 squares in one direction
 */

public class PUNewton extends PowerupCard{
    //mi serve Board per poter accedere alle posizioni e ai giocatori, potremmo mettere questi attributi della classe Board
    //protected in modo che ci si possa accedere facilmente dalle altre claasi
    public PUNewton (char colour, Board b){
        super("Newton", colour, b);
    }


    //qui forse è più semplice usare l'id del giocatore e non il giocatore in sè
    @Override
    public void activate(Player p){
        int x,y;
        Player p1= new Player(6);
        do {
            //todo il giocatore deve scegliere il giocatore p1 da muovere
        }while(p.getNumber()==p1.getNumber()); //il power up newton non può spostare il giocatore che ha invocato la carta
        Position [][] pos=board.getBoard();
        do {
            //todo far scegliere al giocatore dove far fare il movimento al giocatore, ipotizziamo sia stata scelta la posizione x,y
            x=1;
            y=1;
        }while((!p1.getPosition().reachable(pos[x][y]))&&(0<=x && x<=4) && (0<=y && y<=3)); //la posizione scelta deve essere raggiungibile dalla posizione attuale
        int x1,y1;
        int c[]=new int[2];
        c=p1.getPosition().getCoordinate();
        x1=c[0]-x;
        y1=c[1]-y;      //uso queste coordinate per capire in che direzione si sta muovendo p1
            //se x1=1 si sta muovendo verso sinistra, -1 verso destra
            //se y1=1 si sta muvendo verso l'alto,  -1 verso il basso
            //so che solo uno dei due fra x1 e y1 può essere diverso da zero perchè la posizione attuale e quella
            //rappresentata da x,y sono a distanza 1
        //todo metodo da implementare che mi dice se la posizione esiste
        //if(pos[x][y]!=null)
        Position firstSquare=pos[x][y];
        x1=x-x1;
        y1=y-y1;     //x1 e y1 sono le potenziali coordinate del secondo square di movimento
        if((0<=x1 && x1<=4) && (0<=y1 && y1<=3)) {
            if (pos[x1][y1] != null) {
                Position secondSquare = pos[x1][y1];
            }
        }
        //todo chiedere al giocatore la direzione in cui muoversi e poi chiedergli se vuole fare uno o due passi
    }

}