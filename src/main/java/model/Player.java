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
    private boolean firstPlayer;
    private int madeDamage;
    private int[] damagedPlayers;

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
        this.firstPlayer = false;
        this.madeDamage = 0;
        this.damagedPlayers = new int[] {-1, -1, -1, -1};
    }

    public Position getPosition(){
        return this.position;
    }

    public void setRound(boolean round) {
        this.round = round;
    }

    /*public int getMadeDamage() {
        return madeDamage;
    }

    public int[] getDamagedPlayers() {
        return damagedPlayers;
    }
*/

    public void setPowerup(PowerupCard[] powerup) {
        this.powerup = powerup;
    }

    public PowerupCard[] getPowerup() {
        return powerup;
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

    /**
     * Return the ammo amount which have the selected color.
     *
     * @param color the char to show which ammo amount are wanted
     * @return the ammo amount for the chosen color. If the
     */
    public int getAmmo(char color) {
        if(color == 'b')
            return this.ammo[0];
        if(color == 'y')
            return this.ammo[1];
        if(color == 'r')
            return this.ammo[2];
        else {
            System.out.println("Error: incorrect color!");
            return -1;
        }
    }

    /**
     * Set the ammo amount available to the player. The player can have at most 3 ammo for each color.
     *
     * @param color the char that show which ammo are you setting: b -> blue; y -> yellow; r -> red
     * @param ammo the ammo amount to assigned to the selected color
     * @see Player
     */
    public void setAmmo(char color, int ammo) {
        if(color == 'b') {
            this.ammo[0] = ammo;
            if(this.ammo[0] >3)
                this.ammo[0] = 3;
        }
        if(color == 'y') {
            this.ammo[1] = ammo;
            if (this.ammo[1] > 3)
                this.ammo[1] = 3;
        }
        if(color == 'r') {
            this.ammo[2] = ammo;
            if (this.ammo[2] > 3)
                this.ammo[2] = 3;
        }
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

    public void setAction(int action) {
        this.action = action;
    }

    /**
     * When the player give marks, the value is incremented by the given value. If the given value is 0, the total
     * ammount of marks is set to 0 (NOT increment by 0).
     *
     * @param player the player who receives marks
     * @param marks number of marks are received by the player
     * @see Player
     */
    public void setMarksGiven(Player player, int marks) {
        int number = player.getNumber();
        this.marksGiven[number] += marks;
        if(this.marksGiven[number] > 3)
            this.marksGiven[number] = 3;
        if(marks == 0)
            this.marksGiven[number] = 0;
    }

    /**
     * When the player receive marks, the value is incremented by the given value. If the given value is 0, the total
     * ammount of marks is set to 0 (NOT increment by 0).
     *
     * @param player the player who gives marks
     * @param marks number of marks are given by the player
     * @see Player
     */
    public void setMarksReceived(Player player, int marks) {
        int number = player.getNumber();
        this.marksReceived[number] += marks;
        if(this.marksReceived[number] > 3)
            this.marksReceived[number] = 3;
        if(marks == 0)
            this.marksReceived[number] = 0;
    }

    /**
     * Increment the player's score.
     *
     * @param points number of points will increment the total score
     */
    public void setScore(int points) {
        this.score += points;
    }

    public void setFinalRound(boolean finalRound) {
        this.finalRound = finalRound;
    }

    public boolean isFinalRound() {
        return finalRound;
    }

    public void setFirstPlayer(boolean firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * The round begin, so the player can do 2 actions.
     */
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
    public void move(Position position){
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
    public void grab(){ //todo implementare quando si raccoglie un'arma
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

    /**
     * Select weapon cards, mode, palyers and attackedPlayer and shot at them. If the attack isn't possible, an error
     * message is given to the player and the actions counter is not updated.
     *
     * @param weapChosen the weapon card used to shot
     * @see WeaponCard
     * @see Player
     * @see Position
     */
    public void shot(WeaponCard weapChosen){ //todo: aggiungere il controllo delle munizioni
        if (this.action != 0) {
            System.out.print("Select fire mode");
            int mode1 = -1;
            int[] mode2 = new int[3];                   //todo definire dimensioni array di effetti in mode2
            Player[] attackedPlayer = new Player[]{new Player(1)};    //todo definire dimensioni array di giocatori attaccati
            for(int l=0; l<attackedPlayer.length; l++)
                this.damagedPlayers[l] = attackedPlayer[l].getNumber();
            Position[] movements = new Position[3];     //todo definire dimensioni array di movimenti da fare
            //todo prendere il valore scelto di mode1 o mode2
            //todo prendere un array di giocatori scelti
            //todo prendere un array di movimenti
            boolean validAttack = weapChosen.attack(this, mode1, mode2, attackedPlayer, movements);
            if (!validAttack)
                System.out.println("Invalid Attack!");
            else
                this.action--;
        }
        else
            System.out.println("Actions completed");
    }
/*
    /**
     * Reload the selected weapon only if are present the necessary ammo. If they aren't, the ammo amount will not change.
     *
     * @param weapon the WeaponCard the player wants to reload
     * @see WeaponCard
     * @see Player
     *
    public void reload (WeaponCard weapon){     //todo: aggiungere la possibilità di pagare con powerup
        int[] tempAmmo = new int [3];
        for(int p=0; p<3; p++)
            tempAmmo[p] = this.ammo[p];
        char[] cost = weapon.getCostReloading();
        int counter = cost.length;
        for(int i=0; i<cost.length; i++){                                   //all cost array is analyzed to see if the necessary ammo is present
            if (cost[i] == 'b') {
                if(this.ammo[0] == 0) {
                    System.out.println("Not enough Blue Ammo to reload");
                    for(int p=0; p<3; p++)
                        this.ammo[p] = tempAmmo[p];                         //in each case ammo are not enough, initial this.ammo value is restored and the weapon can't to be loaded
                    break;
                }
                else {
                    this.ammo[0] -= 1;
                    counter--;                                              //if the current ammo is present, its value is decreased by one
                }
            }
            else if(cost[i] == 'y') {
                if(this.ammo[1] == 0) {
                    System.out.println("Not enough Yellow Ammo to reload");
                    for(int p=0; p<3; p++)
                        this.ammo[p] = tempAmmo[p];
                    break;
                }
                else {
                    this.ammo[1] -= 1;
                    counter--;
                }
            }
            else if(cost[i] == 'r') {
                if (this.ammo[2] == 0) {
                    System.out.println("Not enough Red Ammo to reload");
                    for(int p=0; p<3; p++)
                        this.ammo[p] = tempAmmo[p];
                    break;
                }
                else {
                    this.ammo[2] -= 1;
                    counter--;
                }
            }
        }
        if(counter == 0)            //counter shows how many elements in cost have to be analyzed. Only when all elements are successfully analyzed, then the weapon is reloaded
            weapon.reload();
    }
*/
    public void reload (WeaponCard weapon, char[] ammo, PowerupCard[] powerUp){
        char[] cost = weapon.getCostReloading();
        int counter = cost.length;
        char[] tempAmmo = ammo.clone();
        PowerupCard[] tempPowerUp = powerUp.clone();
        for(int i=0; i<cost.length; i++){
            for(int a=0; a<ammo.length; a++){           //control if the ammo is in ammoArray
                if(ammo[a] == cost[i]) {
                    ammo[a]--;
                    counter--;
                }
            }
            for(int pu=0; pu<powerUp.length; pu++){     //control if the ammo is in powerUpArray
                if(powerUp[pu] != null) {
                    if(powerUp[pu].getColour() == cost[i]) {
                        powerUp[pu] = null;
                        counter--;
                    }
                }
            }
        }
        if(counter == 0){                       //if true, weaponCard is reloaded
            boolean controller1 = this.updateAmmo(tempAmmo);
            boolean controller2 = this.updatePowerup(tempPowerUp);
            if(controller1 && controller2)
                weapon.reload();
        }
        else
            System.out.println("Selected ammo and power up are incorrect");
    }

    public void reload (WeaponCard weapon, char[] ammo){
        char[] cost = weapon.getCostReloading();
        int counter = cost.length;
        char[] tempAmmo = ammo.clone();
        for(int i=0; i<cost.length; i++){
            for(int a=0; a<ammo.length; a++){           //control if the ammo is in ammoArray
                if(ammo[a] == cost[i]) {
                    ammo[a]--;
                    counter--;
                }
            }
        }
        if(counter == 0){                       //if true, weaponCard is reloaded
            boolean controller = this.updateAmmo(tempAmmo);
            if(controller)
                weapon.reload();
        }
        else
            System.out.println("Selected ammo and incorrect");
    }

    public void reload (WeaponCard weapon, PowerupCard[] powerUp){
        char[] cost = weapon.getCostReloading();
        int counter = cost.length;
        PowerupCard[] tempPowerUp = powerUp.clone();
        for(int i=0; i<cost.length; i++){
            for(int pu=0; pu<powerUp.length; pu++){     //control if the ammo is in powerUpArray
                if(powerUp[pu] != null) {
                    if(powerUp[pu].getColour() == cost[i]) {
                        powerUp[pu] = null;
                        counter--;
                    }
                }
            }
        }
        if(counter == 0){                       //if true, weaponCard is reloaded
            boolean controller = this.updatePowerup(tempPowerUp);
            if(controller)
                weapon.reload();
        }
        else
            System.out.println("Selected power up card are incorrect");
    }

    private  boolean updateAmmo(char[] ammo){
        int[] tempPlayerAmmo = this.ammo.clone();
        boolean control = true;
        for(int a=0; a<ammo.length; a++){
            if(ammo[a] == 'b'){
                if(this.ammo[0] == 0) {
                    System.out.println("Selected blue ammo aren't correct");
                    this.ammo = tempPlayerAmmo.clone();
                    control = false;
                    break;
                }
                else
                    this.ammo[0]--;
            }
            if(ammo[a] == 'y'){
                if(this.ammo[1] == 0) {
                    System.out.println("Selected yellow ammo aren't correct");
                    this.ammo = tempPlayerAmmo.clone();
                    control = false;
                    break;
                }
                else
                    this.ammo[1]--;
            }
            if(ammo[a] == 'r'){
                if(this.ammo[2] == 0) {
                    System.out.println("Selected red ammo aren't correct");
                    this.ammo = tempPlayerAmmo.clone();
                    control = false;
                    break;
                }
                else
                    this.ammo[2]--;
            }
        }
        return control;
    }

    private boolean updatePowerup(PowerupCard[] powerUp){
        PowerupCard[] tempPowerUpPlayer = this.powerup.clone();
        boolean controller = true;
        for(int pu=0; pu<powerUp.length; pu++){
            for(int scan=0; scan<this.powerup.length; scan++){
                if(this.powerup[scan] != null) {
                    if (powerUp[pu].getName().equals(this.powerup[scan].getName())) {
                        this.powerup[scan] = null;
                        break;
                    }
                    else{
                        if(scan == this.powerup.length -1) {
                            controller = false;
                            break;
                        }
                    }
                }
            }
        }
        if(!controller){                                //if controller is false, the weapon can't be reloaded
            this.powerup = tempPowerUpPlayer.clone();
            System.out.println("Selected power up cards aren't correct");
        }
        return controller;
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
     * @param  player  the player who makes damage identifier
     * @see Player
     */
    public void receivedDamages(Player player) {        //player is the one who makes the damage
        if (this.life >= 0) {
            int damageCounter = 1;
            if (playersDamage[player.getNumber()][0] == -1) {
                for (int i = 0; i < 5; i++) {
                    if (playersDamage[i][0] != -1)
                        damageCounter++;
                }
                playersDamage[player.getNumber()][0] = damageCounter;
            }
            playersDamage[player.getNumber()][1]++;
            playersDamage[player.getNumber()][1] += this.marksReceived[player.getNumber()];
            int attackDamage = 1 + this.marksReceived[player.getNumber()];            //damage makes by this attack
            this.setMarksReceived(player, 0);
            player.setMarksGiven(this, 0);
            this.life = this.life - attackDamage;
            if(this.life < -1){
                playersDamage[player.getNumber()][1] = playersDamage[player.getNumber()][1] + this.life + 1;       //only the damages that cause life = -1 are considered; this.life is, in this line, negative
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

    public void respawn (PowerupCard powerup, Position position){
        this.numberOfDeaths ++;
        this.life = 11;
        this.ammo = new int[]{1, 1, 1};
        char colour = powerup.getColour();
        if(position.getRoom() == colour && position.isRespawnPoint())
            this.position = position;
        else
            System.out.println("Incorrect Position");
    }

    public void usePowerup(PowerupCard powerup){
        powerup.activate(this);
        //todo rimuovere il powerup dalla lista di this
    }

    public PowerupCard drawPowerup(){
        //one PowerupCard is randomly taken
        return new PowerupDeck().pickUpPowerup();   //todo da sistemare
    }

}

