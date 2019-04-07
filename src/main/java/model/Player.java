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
        this.life = 11;                                 //remaining player's life. When it is 0, it means death; -1 it means overkill
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

    public int getLife(){
        return this.life;
    }

    public int[] getDamageOrder() {
        return damageOrder;
    }

    public int[] getPlayersDamage() {
        return playersDamage;
    }

    public void action(String name){
        Action action = new Action(name);
    }

    public void recharge (WeaponCard weapon){
        //todo: recharge the weapon received in argument
    }



    public void receivedDamages(int playerNumber){        //playerNumber is the number of the player who makes the damage
        if (this.life >= 0) {
            this.life = this.life - 1;
            int i = 0;
            while (damageOrder[i] != -1) {
                if (damageOrder[i] == playerNumber)       //the player has just done damage before now
                    break;
                i++;
            }
            if (damageOrder[i] == -1)                     //if we correctly ended the while loop, we add the new player
                damageOrder[i] = playerNumber;
            playersDamage[playerNumber]++;              //the damage that has been made from the player was increased
        }
    }

    public boolean endOfRound(){
        this.round = false;
        return this.round;
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

    public int[] sortingPlayer(){                       //Player are sorted from the ones who makes mora damage to the one who make less. When points are the same, the first to make damage receive more points.
        int[] sortedPlayer = new int[]{-1, -1, -1, -1};
        this.damageArray = this.madeDamageArray();
        int k = 0;
        for (int i = 0; i < 4; i++) {
            int damage = damageArray[i];
            if (damage != damageArray[i + 1] && damage != damageArray[i-1] && damage != 0){
                while (playersDamage[k] != damage)
                    k++;
                sortedPlayer[i] = k;
            }
            else if (damage == damageArray[i + 1] && damage != damageArray[i-1] && damage != 0){
                int y = 0;
                for (int z = k; z < 4; z++) {
                    if (playersDamage[z] == damage) {
                        while (sortedPlayer[y] != -1) {
                            y++;
                        }
                        sortedPlayer[y] = z;
                    }
                }
            }
        }
        return sortedPlayer;
    }

    public int[] givePoints(){
         int[] points = new int[]{0, 0, 0, 0};
         final int MAX = 8;             //MAX shows max points assigned to the first player in sortedPlayer[]

         int point = MAX - 2*this.numberOfDeaths;     //Each player's death decrease max points about 2 points

         int[] sortedPlayer = sortingPlayer();

         for (int i=0; i<4; i++){
             if (point <= 0)
                 point = 1;
             if(points[sortedPlayer[i]] != -1)
                points[sortedPlayer[i]] = point;
             point-=2;
         }
         return points;
    }

    public void regeneration(){
        this.numberOfDeaths ++;
        //todo: I have to implement this method
    }
}

