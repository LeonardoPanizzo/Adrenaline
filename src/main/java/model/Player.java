package model;

public class Player {
    private int number;
    private Position position;
    private int[] life;
    private boolean round;
    private int[] markGiven;
    private int[] markReceived;
    private int numberOfDeaths;
    private int[] ammo;
    private int score;
    private WeaponCard[] weapons;

    public Player(){

        //todo inizializzare numero del giocatore
        //todo non va creata la nuova posizione, gliene si passa una già esistente

        this.life = new int[12];
        //inizializzo il vettore di vite a 0 (valore che indica che nessn danno è presente)
        for(int v : life)
            this.life[v]= 0;

        this.round = false;
        // da inizializzare markGiven a seconda del regolamento

        this.markReceived = new int [12];
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

    public Position getPosition(){
        return this.position;
    }

    public int[] getLife(){
        return this.life;
    }

    public void setDamage(int number){
        int i=0;

        while(this.life[i]!=0){
            i++;
        }
        this.life[i]= number;
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

    public int[] giveOrderDamage(){
        int[] orderDamage = {0, 0, 0, 0, 0};
        char[] damageByPlayer = {'n', 'n', 'n', 'n', 'n'};

        orderDamage[0] = this.life[0];
        damageByPlayer[0] = 'y';

        for(int p=1; p<12; p++){
            if(this.life[p] != orderDamage[p-1] && damageByPlayer[this.life[p]] == 'n') {
                orderDamage[p] = this.life[p];
                damageByPlayer[this.life[p]] = 'y';
            }
        }
        return orderDamage;
    }

    public int[] givePoints(){
        int[] points = {0, 0, 0, 0, 0};
        int[] orderedPlayer = {0, 0, 0, 0, 0}; //giocatori ordinati secondo punteggio decrescente

        for(int p=0; p<12; p++){
            if(this.life[p] == 1)
                points[0] ++;
            else if (this.life[p] == 2)
                points[1]++;
            else if (this.life[p] == 3)
                points[2]++;
            else if (this.life[p] == 4)
                points[3]++;
            else if (this.life[p] == 5)
                points[4]++;
        }



        return points;
        //todo aggiungere l'assegnamento ai giocatori del punteggio
    }


}

