package model;


public class Player {
    private int number;
    private Position position;
    private int life;
    private int [] damageOrder;         //order as a player has made damage
    private int [] playersDamage;       //damage by all player (position 0 is for player one e so on)
    private int [] damageArray;         //sorted array from higher damage's amount (in position 0) to the lower
    private boolean round;
    private int[] marksGiven;
    private int[] marksReceived;
    private int numberOfDeaths;
    private int[] ammo;
    private int score;
    private WeaponCard[] weapons;
    private PowerupCard[] powerup;
    private boolean finalRound;

    public Player(int number){

        this.number = number;                           //the number is assigned in the same order as the player is connected to the lobby
        this.position = null;                           //the initial position is chosen by the player

        this.life = 10;                                 //remaining player's life. When it is 0, it means death; -1 it means overkill
        this.damageOrder = new int[]{-1, -1, -1, -1};   //at the beginning, no player has made damages
        this.playersDamage = new int[]{0, 0, 0, 0};
        this.damageArray = new int[]{0, 0, 0, 0};

        this.round = false;

        this.marksGiven = new int[]{0, 0, 0, 0};            //marksGiven[i] is the marks' number on player i+1
        this.marksReceived = new int []{0, 0, 0, 0, 0};     //marksReceived[i] is the marks' number by player i+1

        this.numberOfDeaths = 0;
        this.ammo = new int[]{1, 1, 1};                     //1 ammo for each type

        this.score = 0;

        this.weapons = new WeaponCard[]{null, null, null};
        this.powerup = new PowerupCard[]{null, null, null};

        this.finalRound = false;
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

        int[] pointsCopy = points;
        int max = pointsCopy[1];
        pointsCopy[0] = -1;
        for(int k = 0; k<5; k++){
            
        }

        return points;
        //todo aggiungere l'assegnamento ai giocatori del punteggio
    }


}

