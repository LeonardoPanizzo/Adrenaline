package model;

public class Player {
    private Position position;
    private int[] life;
    private boolean round;
    private int markGiven;
    private int[] markReceived;
    private int numberOfDeaths;
    private int[] ammo;
    private int score;
    private WeaponCard[] weapons;

    public Player(){
        this.position = new Position();

        this.life = new int[12];
        //inizializzo il vettore di vite a 0 (valore che indica che nessn danno è presente)
        for(int v : life)
            this.life[v]= 0;

        this.round = false;
        this.markGiven = 0;

        this.markReceived = new int [15];
        //inizializzo il vettore machiRicevuti a 0 (valore che indica nessun marchio)
        for(int mr : markReceived)
            this.markReceived[mr]=0;

        this.numberOfDeaths = 0;

        this.ammo = new int[3];
        //inizializzo il vettore munizioni a {1, 1, 1} (valore che indica una municione per tipo)
        for(int m : ammo)
            this.ammo[m] = 1;

        this.score = 0;
        this.weapons = new WeaponCard[4];
    }

    public void action(){
        //scelta delle azioni del turno
    }

    public void recharge(WeaponCard army){
        //scelgo l'arma e scarto le munizioni o potenziamenti per la ricarica
    }

    public void endOfRound(){
        //invio al server del segnale di fine turno
    }

    public int[] givePoints(){
        int[] points = {0, 0, 0, 0, 0};
        for(int p : life){
            if(life[p] == 1)
                points[0] ++;
            else if (life[p] == 2)
                points[1]++;
            else if (life[p] == 3)
                points[2]++;
            else if (life[p] == 4)
                points[3]++;
            else if (life[p] == 5)
                points[4]++;
        }
        return points;
    }
}
