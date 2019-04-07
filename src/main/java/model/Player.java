package model;


public class Player {
    private int number;
    private Position position;
    private int life;
    private int[][] playersDamage;      //For each player are memorized order of damage in [x][0] and total damage in [x][1]
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
        this.life = 11;                                 //remaining player's life. When it is 0, it means death; -1 it means overkill
        this.playersDamage = new int [][]{{-1, 0}, {-1, 0}, {-1, 0}, {-1, 0}, {-1, 0}};
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

    public int getLife(){
        return this.life;
    }

    public void action(String name){
        Action action = new Action(name);
    }

    public void recharge (WeaponCard weapon){
        //todo: recharge the weapon received in argument
    }

    public boolean endOfRound(){
        this.round = false;
        return this.round;
    }

    public void receivedDamages(int playerNumber) {        //playerNumber is the number of the player who makes the damage
        if (this.life >= 0) {
            this.life = this.life - 1;
            int damageCounter = 1;
            if (playersDamage[playerNumber][0] == -1) {
                for (int i = 0; i < 5; i++) {
                    if (playersDamage[i][0] != -1)
                        damageCounter++;
                }
                playersDamage[playerNumber][0] = damageCounter;
            }
            playersDamage[playerNumber][1]++;
        }
    }

    public int[] sortingPlayers(){
        int[] sortedPlayers = new int[] {-1, -1, -1, -1, -1};
        int[][] playersDamage = this.getPlayersDamage();
        sortedPlayers[0] = 0;
        int memScan;
        for(int i=1; i<5; i++){
            int tempScan = i;
            for(int j=0; j<i; j++) {
                if (sortedPlayers[j] != -1){
                    if (playersDamage[tempScan][1] > playersDamage[sortedPlayers[j]][1]){
                        memScan = sortedPlayers[j];
                        sortedPlayers[j] = tempScan;
                        tempScan = memScan;
                        if(sortedPlayers[j+1] == -1)
                            sortedPlayers[j+1] = tempScan;
                    }
                    else if (playersDamage[tempScan][1] == playersDamage[sortedPlayers[j]][1]){
                        if (playersDamage[sortedPlayers[j]][0] > playersDamage[i][0]){
                            memScan = sortedPlayers[j];
                            sortedPlayers[j] = tempScan;
                            tempScan = memScan;

                        }
                        if(sortedPlayers[j+1] == -1)
                            sortedPlayers[j+1] = tempScan;
                    }
                }
            }
        }
        return sortedPlayers;
    }

    public int[] givePoints(){
        int[] points = new int[]{0, 0, 0, 0, 0};
        final int MAX = 8;             //MAX shows max points assigned to the first player in sortedPlayer[]

        int point = MAX - 2*this.numberOfDeaths;     //Each player's death decrease max points about 2 points

        int[] sortedPlayer = this.sortingPlayers();

        for (int i=0; i<5; i++){
            if (point <= 0)
                point = 1;
            if(playersDamage[sortedPlayer[i]][1] != 0)
                points[sortedPlayer[i]] = point;
            point-=2;
        }
        return points;
    }

    public int[][] getPlayersDamage() {
        return playersDamage;
    }

    public void regeneration(){
        this.numberOfDeaths ++;
        //todo: I have to implement this method
    }
}

