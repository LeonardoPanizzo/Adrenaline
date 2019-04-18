package model;


public class Player {
    private int number;
    private Position position;
    private int life;
    private int action;                 //actions' counter. Restored to 2 in reloaded
    private int[][] playersDamage;      //For each player are memorized order of damage in [x][0] and total damage in [x][1]
    private boolean round;
    private int[] marksGiven;
    private int[] marksReceived;
    private int numberOfDeaths;
    private int[] ammo;                 //number of each ammo type: blue -> position 0; yellow -> position 1; red -> position 2
    private int score;
    private WeaponCard[] weapons;
    private PowerupCard[] powerup;
    private boolean finalRound;

    /**
     * Is Player Class' constructor.
     * <p>When it is generated, a Player object has a null position, 11 life points,
     * no damage received and the same for marks given and received. It is died no times and it's not his round.
     * It also has one ammo for each type and no powerup and weapon cards.</p>
     *
     * @param number    player's identifier
     * @see Player
     */
    public Player(int number){
        this.number = number;                           //the number is assigned in the same order as the player is connected to the lobby
        this.action = 0;
        this.position = null;                           //the initial position is chosen by the player
        this.life = 11;                                 //remaining player's life. When it is 0, it means death; -1 it means overkill
        this.playersDamage = new int [][]{{-1, 0}, {-1, 0}, {-1, 0}, {-1, 0}, {-1, 0}};
        this.round = false;
        this.marksGiven = new int[]{0, 0, 0, 0, 0};         //marksGiven[i] is the marks' number on player i+1
        this.marksReceived = new int []{0, 0, 0, 0, 0};     //marksReceived[i] is the marks' number by player i+1
        this.numberOfDeaths = 0;
        this.ammo = new int[]{1, 1, 1};                     //1 ammo for each type
        this.score = 0;
        this.weapons = new WeaponCard[]{null, null, null, null};
        this.powerup = new PowerupCard[]{null, null, null, null};
        this.finalRound = false;
    }

    public Position getPosition(){
        return this.position;
    }

    public int getLife(){
        return this.life;
    }

    public int getNumber(){
        return this.number;
    }

    public int[] getMarksGiven() {
        return marksGiven;
    }

    public int[] getMarksReceived() {
        return marksReceived;
    }

    public int getNumberOfDeaths() {
        return numberOfDeaths;
    }

    public int[] getAmmo() {
        return ammo;
    }

    public int getScore() {
        return score;
    }

    public boolean isRound() {
        return round;
    }

    public int getAction() {
        return action;
    }

    public void setMarksGiven(int[] marksGiven) {
        this.marksGiven = marksGiven;
    }

    public void setMarksReceived(int[] marksReceived) {
        this.marksReceived = marksReceived;
        for(int i =0; i<5; i++){
            if(this.marksReceived[i]>3)              //one player can receive only 3 damages from one other player
                this.marksReceived[i] = 3;
        }
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void roundBegin(){
        this.action = 2;
    }

    /**
     * Change the player's position in one other that is reachable for the initial player's position.
     *
     * @param position position where the player want to go
     * @see Player
     * @see Position
     */
    public void action(Position position){      //move
        if(this.action > 0){
            if(this.position.reachable(position))
                this.position = position;
            this.action--;
        }
        else
            System.out.println("Actions completed");

    }

    /**
     * Grab a weapon from the position where the player is.
     * <p>If the position is a respawn point, the palyer can choose a new weapon</p>
     * <p>If the position isn't a respawn point, the player grab a AmmoCard. This will converted into ammo and, maybe, a powerup card</p>
     *
     * @see AmmoCard
     * @see Position
     * @see PowerupCard
     * @see PowerupDeck
     */
    public void action(){                       //grab
        if(this.action >0) {
            if (this.position.isRespawnPoint()) {
                WeaponCard[] weapons = this.position.showWeapons();
                int wepChoosen = 0;
                //todo accept index wepChoosen
                WeaponCard choosen = this.position.chooseArm(wepChoosen);
                int wepGiven = 0;
                //todo accept index wepGiven
                this.position.giveWeapon(this.weapons[wepGiven]);
                this.action--;
            }
            else {
                AmmoCard ammo = new AmmoCard();
                ammo = this.position.getAmmo();
                for (int i = 0; i < 4; i++) {
                    if (ammo.getValue()[i] == 'b') {
                        if (this.ammo[0] < 3)
                            this.ammo[0]++;
                    } else if (ammo.getValue()[i] == 'y') {
                        if (this.ammo[1] < 3)
                            this.ammo[1]++;
                    } else if (ammo.getValue()[i] == 'r') {
                        if (this.ammo[2] < 3)
                            this.ammo[2]++;
                    } else if (ammo.getValue()[i] == 'p')
                        this.powerup[3] = this.drawPowerup();
                }
                this.action--;
            }
        }
        else
            System.out.println("Actions completed");
    }

    public void action(WeaponCard wepChoosen){      //shot
        //todo implement how to use the weaponCard
    }

    public void reload (WeaponCard weapon){
        //todo: recharge the weapon received in argument
    }

    public void endOfRound(){
        this.round = false;
    }

    /**
     * Assign to the player the damage received.
     * If player's life gives the possibility to receive more damage (life >=0), then it is
     * decreased and for the player who makes damage are saved:
     * <p>-When he makes damage, compared to the other players;</p>
     * <p>-How much damage he has done to this player.</p>
     * All damages coming from the other player are saved in this way in an array 5x2.
     *
     * @param  playerNumber  the player who makes damage identifier
     * @see         Player
     */
    public void receivedDamages(int playerNumber) {        //playerNumber is the number of the player who makes the damage
        if (this.life >= 0) {
            int damageCounter = 1;
            if (playersDamage[playerNumber][0] == -1) {
                for (int i = 0; i < 5; i++) {
                    if (playersDamage[i][0] != -1)
                        damageCounter++;
                }
                playersDamage[playerNumber][0] = damageCounter;
            }
            playersDamage[playerNumber][1]++;
            playersDamage[playerNumber][1] += this.marksReceived[playerNumber];
            int attackDamage = 1 + this.marksReceived[playerNumber];            //damage makes by this attack
            this.marksReceived[playerNumber] = 0;
            this.life = this.life - attackDamage;
            if(this.life < -1){
                playersDamage[playerNumber][1] = playersDamage[playerNumber][1] + this.life + 1;       //only the damages that cause life = -1 are considered; this.life is, in this line, negative
                this.life = -1;
            }
        }

        //todo: control powerup Venom and use it
    }

    /**
     * The players who made damage are sorted from the one with higher damage to the one with the less one.
     * When two or more player has the same damage, the first one who made damage is the one that will received
     * more points.
     *
     * @return  an array with all player sorted
     * @see Player
     */
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

    /**
     * Calculates all points have to been sum to the score of each player has made damage.
     * All player who make damage will receive at least one point.
     * Maximum amount of points is initially 8 and it will decrease any time the player who receive damage will
     * die.
     * <p>The starting amount of points is, from the first to the last player in sortedPlayer,
     * is 8 -> 6 -> 4 -> 2 -> 1. To the player who has made the first blood, one more point is given.</p>
     *
     * @return  array where the element points[x] is the amount of points to give to player x.
     * @see Player
     */
    public int[] givePoints(){
        int[] points = new int[]{0, 0, 0, 0, 0};
        final int MAX = 8;                              //MAX shows max points assigned to the first player in sortedPlayer[]

        int point = MAX - 2*this.numberOfDeaths;        //Each player's death decrease max points about 2 points

        int[] sortedPlayer = this.sortingPlayers();

        for (int i=0; i<5; i++){
            if (point <= 0)
                point = 1;
            if(playersDamage[sortedPlayer[i]][1] != 0) {
                points[sortedPlayer[i]] = point;
                if(playersDamage[sortedPlayer[i]][0] == 1)
                    points[sortedPlayer[i]]++;
            }
            point-=2;
        }
        return points;
    }

    public int[][] getPlayersDamage() {
        return playersDamage;
    }

    public void regeneration(PowerupCard powerup){
        this.numberOfDeaths ++;
        this.life = 11;
        this.ammo = new int[]{1, 1, 1};
        //todo: capire come settare la posizione del respawn point senza creare una nuova posizione
        if(powerup.getColour() == 'b')
            this.position = new Position(2, 0, 'b', true, true);
        else if(powerup.getColour() == 'y')
            this.position = new Position(3, 2, 'y', true, true);
        else if(powerup.getColour() == 'r')
            this.position = new Position(0, 1, 'r', true, true);
    }

    public void usePowerup(PowerupCard powerup, WeaponCard weapon){
        //todo: implement the powerup effect
    }

    public PowerupCard drawPowerup(){
        //one PowerupCard is randomly taken
        return new PowerupDeck().pickUpPowerup();
    }

}

