package model;

/**
 * Using this powerup a player can move another player's figure of 1 or 2 suares in one direction
 */

public class PUNewton extends PowerupCard{

    public PUNewton (char colour, Board b){
        super("Newton", colour, b);
    }

    @Override
    public void activate(Player p){
        int x,y;
        do {
            Player p1=new Player(7);//

            //todo il giocatore deve scegliere il giocatore p1 da muovere
        }while(p.getNumber()==p1.getNumber()); //il power up newton non può spostare il giocatore che ha invocato la carta
        Position [][] pos=board.getBoard();
        do {
            //todo far scegliere al giocatore dove far fare il movimento al giocatore, ipotizziamo sia stata scelta la posizione x,y
        }while(!p1.getPosition().reachable(pos[x][y])); //la posizione scelta deve essere raggiungibile dalla posizione attuale
        int x1,y1;
        int c[]=new int[2];
        c=p1.getPosition().getCoordinate();
        x1=c[0]-x;
        y1=c[1]-y;      //uso queste coordinate per capire in che direzione si sta muovendo p1



    }

}
