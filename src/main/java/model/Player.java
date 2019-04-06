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

    public void action(String name){
        Action action = new Action(name);
    }

    public void recharge (WeaponCard weapon){
        //todo: recharge the weapon received in argument
    }

    public int getLife(){
        return this.life;
    }

    public void madeDamage(int playerNumber){        //playerNumber is the number of the player who makes the damage
        this.life = this.life -1;

        int i=0;
        while(damageOrder[i] != -1){
            if (damageOrder[i]==playerNumber)
                break;
            i++;
        }
        if (damageOrder[i]==-1)
            damageOrder[i]= playerNumber;

        playersDamage[playerNumber]++;
    }

    public void endOfRound(){
        //invio al server del segnale di fine turno
    }

    private int[] madeDamageArray(){
        int [] arrayD = new int[]{-1, -1, -1, -1};

        arrayD[0]= playersDamage[0];
        for (int i=1; i<4; i++){
            int tempScan = playersDamage[i];
            for(int j=0; j<=i; j++){
                if(tempScan > arrayD[j]){
                    int tempMem = arrayD[j];
                    arrayD[j] = tempScan;
                    tempScan = tempMem;
                }
            }
        }
        return arrayD;
    }

    public void givePoints(){
         int[] sortedPlayer = new int [4];
         int[] points = new int[4];

        this.damageArray = this.madeDamageArray();
        for(int i=0; i<4; i++){
            int damage = damageArray[i];
            int k=0;
            while(playersDamage[k] != damage)
                k++;
            if(damageArray[i] != damageArray[i+1]){
                sortedPlayer[i] = k;
            }
            else{
                int counter= 1;
                while(damageArray[counter+i] == damageArray[i] && counter+i<4)
                    counter++;
                //todo: to finish it
            }

        }



        //todo: I have to finish this method
    }


}

