package Adrenaline.model;

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
    private PowerupDeck powerUpDeck;

    /**
     * Is Player Class' constructor.
     * <p>When it is generated, a Player object has a null position, 11 life points,
     * no damage received and the same for marks given and received. It is died no times and it's not his round.
     * It also has one ammo for each type and no powerup and weapon cards.</p>
     *
     * @param number    player's identifier
     * @see Player
     */
    public Player(int number, PowerupDeck powerUpDeck){
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
        this.weapons = new WeaponCard[]{null, null, null};
        this.powerup = new PowerupCard[]{null, null, null};
        this.finalRound = false;
        this.firstPlayer = false;
        this.madeDamage = 0;
        this.damagedPlayers = new int[] {-1, -1, -1, -1};
        this.powerUpDeck = powerUpDeck;
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
        return marksGiven.clone();
    }

    public int[] getMarksReceived() {
        return marksReceived.clone();
    }

    public int getNumberOfDeaths() {
        return numberOfDeaths;
    }

    public WeaponCard[] getWeapons() {
        return weapons.clone();
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
        int numb = player.getNumber();
        this.marksGiven[numb] += marks;
        if(this.marksGiven[numb] > 3)
            this.marksGiven[numb] = 3;
        if(marks == 0)
            this.marksGiven[numb] = 0;
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
        int numb = player.getNumber();
        this.marksReceived[numb] += marks;
        if(this.marksReceived[numb] > 3)
            this.marksReceived[numb] = 3;
        if(marks == 0)
            this.marksReceived[numb] = 0;
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
     * Change the player's position in one other that is reachable for the initial player's position. If more than 3
     * position are given, only the first three are thoughtful.
     *
     * @param position positions Array where the player want to go
     * @see Player
     * @see Position
     */
    public void move(Position[] position){
        if(this.action > 0){
            for(int i=0; i<3 && i<position.length; i++){
                if (this.position.reachable(position[i]))
                    this.position = position[i];
                this.action--;
            }
        }
        else
            System.out.println("Actions completed");
    }

    /**
     * To use only when the player move before grab something.
     *
     * @param posArray positions where the player wants to go
     * @see Player
     */
    public void moveAndGrab(Position[] posArray){
        if(this.action > 0){
            for(int i=0; i<posArray.length; i++){
                if (this.position.reachable(posArray[i]))
                    this.position = posArray[i];
            }
        }
        else
            System.out.println("Actions completed");
    }

    /**
     * Grab a weapon from the position where the player is.
     * <p>If the position is a respawn point, the palyer can't grab an AmmoCard</p>
     * <p>If the position isn't a respawn point, the player grab an AmmoCard. This will be converted into ammo and, maybe, a powerup card</p>
     *
     * @see AmmoCard
     * @see Position
     * @see PowerupCard
     * @see PowerupDeck
     */
    public void grabAmmoCard(){
        if(this.action >0) {
            if (this.position.isRespawnPoint()) {
                System.out.println("You can't grab an AmmoCard");
            }
            else {
                AmmoCard ammo = this.position.getAmmo();
                if (ammo == null) {
                    System.out.println("No AmmoCard is present");
                }
                else {
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
                            System.out.println("pesca");//todo pesca powerup
                    }
                    this.action--;
                    this.position.setAmmo(null);
                }
            }
        }
        else
            System.out.println("Actions completed");
    }

    /**
     * Show if the player can see one other player.
     *
     * @param x the player we want to know if is seen
     * @return true if x is seen. False if it isn't.
     * @see Player
     */
    public boolean canSee(Player x){
        return this.getPosition().visible(x.getPosition());
    }

    /**
     * Take a weapon card when player is in a respawn point. The taking cost is payed by ammo and power up cards.
     *
     * @param weapon the weapon player wants to take
     * @param ammo selected ammo to pay the cost
     * @param powerUp selected power up cards to pay the cost
     * @see PowerupCard
     * @see PowerupDeck
     * @see Player
     */
    public boolean grabWeaponCard (WeaponCard weapon, char[] ammo, PowerupCard[] powerUp) {
        if (this.action > 0) {
            char[] cost = weapon.getCostTaking();
            int counter = cost.length;
            char[] tempAmmo = ammo.clone();
            PowerupCard[] tempPowerUp = powerUp.clone();
            for (int i = 0; i < cost.length; i++) {
                for (int a = 0; a < ammo.length; a++) {           //control if the ammo is in ammoArray
                    if (ammo[a] == cost[i]) {
                        cost[i] = 'n';
                        ammo[a]--;
                        counter--;
                        break;
                    }
                }
                for (int pu = 0; pu < powerUp.length; pu++) {     //control if the ammo is in powerUpArray
                    if (powerUp[pu] != null) {
                        if (powerUp[pu].getColour() == cost[i]) {
                            powerUp[pu] = null;
                            counter--;
                            break;
                        }
                    }
                }
            }
            if (counter == 0 && cost.length == ammo.length + powerUp.length) {        //if true, weaponCard is taken
                boolean controller1 = this.updateAmmo(tempAmmo);
                boolean controller2 = this.updatePowerup(tempPowerUp);
                if (controller1 && controller2) {
                    int cont = 0;
                    while (this.weapons[cont] != null) {
                        cont++;
                    }
                    this.weapons[cont] = weapon;
                    this.action--;
                    return true;
                }
            }
            else
                System.out.println("Selected ammo and power up are incorrect");
            return false;
        }
        else
            System.out.println("Action completed");
        return false;
    }

    /**
     * Take a weapon card when player is in a respawn point. The taking cost is payed by ammo only.
     *
     * @param weapon the weapon player wants to take
     * @param ammo selected ammo to pay the cost
     * @see Player
     */
    public boolean grabWeaponCard (WeaponCard weapon, char[] ammo){
        if(this.action > 0) {
            char[] cost = weapon.getCostTaking().clone();
            int counter = cost.length;
            char[] tempAmmo = ammo.clone();
            for (int i = 0; i < cost.length; i++) {
                for (int a = 0; a < ammo.length; a++) {           //control if the ammo is in ammoArray
                    if (ammo[a] == cost[i]) {
                        cost[i] = 'n';
                        ammo[a]--;
                        counter--;
                        break;
                    }
                }
            }
            if (counter == 0 && cost.length == ammo.length) {        //if true, weaponCard is taken
                boolean controller = this.updateAmmo(tempAmmo);
                if (controller) {
                    int cont = 0;
                    while (this.weapons[cont] != null) {
                        cont++;
                    }
                    this.weapons[cont] = weapon;
                    this.action--;
                    return true;
                }
            } else
                System.out.println("Selected ammo are incorrect");
            return false;
        }
        else
            System.out.println("Action completed");
        return false;
    }

    /**
     * Take a weapon card when player is in a respawn point. The taking cost is payed by power up cards only.
     *
     * @param weapon the weapon player wants to take
     * @param powerUp selected power up cards to pay the cost
     * @see PowerupCard
     * @see PowerupDeck
     * @see Player
     */
    public boolean grabWeaponCard (WeaponCard weapon, PowerupCard[] powerUp){
        if(this.action > 0) {
            char[] cost = weapon.getCostTaking();
            int counter = cost.length;
            PowerupCard[] tempPowerUp = powerUp.clone();
            for (int i = 0; i < cost.length; i++) {
                for (int pu = 0; pu < powerUp.length; pu++) {     //control if the ammo is in powerUpArray
                    if (powerUp[pu] != null) {
                        if (powerUp[pu].getColour() == cost[i]) {
                            powerUp[pu] = null;
                            counter--;
                            break;
                        }
                    }
                }
            }
            if (counter == 0 && cost.length == powerUp.length) {        //if true, weaponCard is taken
                boolean controller = this.updatePowerup(tempPowerUp);
                if (controller) {
                    int cont = 0;
                    while (this.weapons[cont] != null) {
                        cont++;
                    }
                    this.weapons[cont] = weapon;
                    this.action--;
                    return true;
                }
            } else
                System.out.println("Selected power up are incorrect");
            return false;
        }
        else
            System.out.println("Action completed");
        return false;
    }

    /**
     *Use a weapon card to shot at one or more player. To do that, fire mode, optional fire mode and Power up to pay
     * that, players to attack and movements to do need to be selected.
     *
     * @param weapChosen the weapon we want to use
     * @param playersAttacked player attacked
     * @param mode1 0 -> first fire mode; 1 -> second fire mode (more ammo can be needed)
     * @param mode2 array where is selected the sequence of the optional fire mode
     * @param movements sometimes it's possible to move as optional fire effect
     * @param payment power up used to pay fire optional mode extra ammo cost
     * @see Player
     * @see WeaponCard
     * @see PowerupCard
     */
    public void shot(WeaponCard weapChosen, Player[] playersAttacked, int mode1, int[] mode2, Position[] movements,  PowerupCard[] payment){
        if (this.action > 0) {
            boolean control = false;
            for(int i=0; i<3; i++){
                if(this.weapons[i] != null && this.weapons[i].getName().equals(weapChosen.getName())){
                    control = true;
                }
            }
            if(control){
                boolean success = weapChosen.attack(this, mode1, mode2, playersAttacked, movements, payment);
                if(success)
                    action--;
                else
                    System.out.println("impossible to attack");
            }
            else
                System.out.println("You don't have this weapon");
        }
        else
            System.out.println("Actions completed");
    }

    /**
     * Reload a weapon using power up cards and Ammo. The value in this.ammo and this.powerup are updated
     * if the selected ammo and the selected power up cards are the correct ones.
     *
     * @param weapon weapon to reload
     * @param ammo array of ammo to reload the weapon
     * @param powerUp array of power up cards to reload the weapon
     * @see Player
     * @see PowerupCard
     * @see WeaponCard
     */
    public void reload (WeaponCard weapon, char[] ammo, PowerupCard[] powerUp){
        char[] cost = weapon.getCostReloading();
        int counter = cost.length;
        char[] tempAmmo = ammo.clone();
        PowerupCard[] tempPowerUp = powerUp.clone();
        for(int i=0; i<cost.length; i++){
            for(int a=0; a<ammo.length; a++){           //control if the ammo is in ammoArray
                if(ammo[a] == cost[i]) {
                    cost[i] = 'n';
                    ammo[a]--;
                    counter--;
                    break;
                }
            }
            for(int pu=0; pu<powerUp.length; pu++){     //control if the ammo is in powerUpArray
                if(powerUp[pu] != null) {
                    if(powerUp[pu].getColour() == cost[i]) {
                        powerUp[pu] = null;
                        counter--;
                        break;
                    }
                }
            }
        }
        if(counter == 0 && cost.length == ammo.length + powerUp.length){        //if true, weaponCard is reloaded
            boolean controller1 = this.updateAmmo(tempAmmo);
            boolean controller2 = this.updatePowerup(tempPowerUp);
            if(controller1 && controller2)
                weapon.reload();
        }
        else
            System.out.println("Selected ammo and power up are incorrect");
    }

    /**
     * Reload a weapon using only Ammo. The value in this.ammo is updated if the selected ammo are the correct ones.
     *
     * @param weapon weapon to reload
     * @param ammo array of ammo to reload the weapon
     * @see Player
     * @see PowerupCard
     * @see WeaponCard
     */
    public void reload (WeaponCard weapon, char[] ammo){
        char[] cost = weapon.getCostReloading();
        int counter = cost.length;
        char[] tempAmmo = ammo.clone();
        for(int i=0; i<cost.length; i++){
            for(int a=0; a<ammo.length; a++){           //control if the ammo is in ammoArray
                if(ammo[a] == cost[i]) {
                    ammo[a]--;
                    counter--;
                    break;
                }
            }
        }
        if(counter == 0 && cost.length == ammo.length){                       //if true, weaponCard is reloaded
            boolean controller = this.updateAmmo(tempAmmo);
            if(controller)
                weapon.reload();
        }
        else
            System.out.println("Selected ammo are incorrect");
    }

    /**
     * Reload a weapon using only power up cards. The value in this.powerup is updated if the selected power up cards
     * are the correct ones.
     *
     * @param weapon weapon to reload
     * @param powerUp array of power up cards to reload the weapon
     * @see Player
     * @see PowerupCard
     * @see WeaponCard
     */
    public void reload (WeaponCard weapon, PowerupCard[] powerUp){
        char[] cost = weapon.getCostReloading();
        int counter = cost.length;
        PowerupCard[] tempPowerUp = powerUp.clone();
        for(int i=0; i<cost.length; i++){
            for(int p=0; p<powerUp.length; p++){     //control if the ammo is in powerUpArray
                if(powerUp[p] != null) {
                    if(powerUp[p].getColour() == cost[i]) {
                        powerUp[p] = null;
                        counter--;
                        break;
                    }
                }
            }
        }
        if(counter == 0 && cost.length == powerUp.length){                       //if true, weaponCard is reloaded
            boolean controller = this.updatePowerup(tempPowerUp);
            if(controller)
                weapon.reload();
        }
        else
            System.out.println("Selected power up cards are incorrect");
    }

    /**
     * The given array of ammo is compared with the value of this.ammo. This value is updated only if all the values
     * contained in the ammo array are present in this.ammo. Otherwise this value does not change and an error message
     * is given.
     *
     * @param ammo selected ammo to reload the weapon
     * @return a boolean that indicates if the process ends successfully
     */
    public boolean updateAmmo(char[] ammo){
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

    /**
     * The given array of ammo is compared with the value of this.ammo. This value is updated only if all the values
     * contained in the ammo array are present in this.ammo. Otherwise this value does not change and an error message
     * is given.
     *
     * @param am selected ammo to reload the weapon
     * @return a boolean that indicates if the process ends successfully
     */
    public boolean updateAmmo(int[] am){
        boolean control=false;
        if(am[0]<=ammo[0] && am[1]<=ammo[1] && am[2]<=ammo[2]){
            ammo[0]=ammo[0]-am[0];
            ammo[1]=ammo[1]-am[1];
            ammo[2]=ammo[2]-am[2];
            control=true;
        }
        return control;
    }

    /**
     * The given array of power up cards is compared with the value of this.powerup. This value is updated only if all
     * the value contained in the power up cards array are present in this.powerup. Otherwise this value does not change
     * and an error message is given.
     *
     * @param powerUp selected power up cards selected to reload the weapon
     * @return a boolean that indicates if the process ends successfully
     */
    public boolean updatePowerup(PowerupCard[] powerUp){
        PowerupCard[] tempPowerUpPlayer = this.powerup.clone();
        boolean controller = true;
        for(int pu=0; pu<powerUp.length; pu++){
            for(int scan=0; scan<this.powerup.length; scan++){
                if(this.powerup[scan] != null) {
                    if (powerUp[pu].getName().equals(this.powerup[scan].getName()) && powerUp[pu].colour == this.powerup[scan].getColour()) {
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

    /**
     * The player respown in a selected respawn point. A power up card with the same color is discard.
     *
     * @param powerup the power up to discard to respawn in the respawn point with the same color
     * @param position the position where the player want to respawn
     */
    public void respawn (PowerupCard powerup, Position position){
        this.numberOfDeaths ++;
        this.life = 11;
        boolean control1 = false;
        for(int i=0; i<3; i++) {
            if (this.powerup[i] != null && this.powerup[i].getName().equals(powerup.getName()) && this.powerup[i].getColour() == powerup.getColour()) {
                control1 = true;
            }
        }
        if(control1) {
            PowerupCard[] pow = new PowerupCard[1];
            pow[0] = powerup;
            char colour = powerup.getColour();
            if (position.getRoom() == colour && position.isRespawnPoint()) {
                boolean control = this.updatePowerup(pow);
                if (control)
                    this.position = position;
                else
                    System.out.println("You don't have this PowerUp Card");
            } else
                System.out.println("Incorrect Position");
        }
    }

    /**
     * The power up card is used and then, if this it's possible, it is removed from this.powerup.
     *
     * @param powerup the card we want to use
     * @param attacked players that will receive the power up card effects
     * @param position position where the player want to go
     * @param ammoColor ammo we want to use to pay the cost
     */
    public void usePowerup(PowerupCard powerup, Player attacked, Position[] position, char ammoColor){
        boolean isPresent = false;
        int counter = 0;
        for(int i=0; i<this.powerup.length && !isPresent; i++){
            if (this.powerup[i] != null && this.powerup[i].getName().equals(powerup.getName()) && this.powerup[i].getColour() == powerup.getColour()) {
                counter = i;
                isPresent = true;
            }
        }
        if(isPresent) {
            boolean control = powerup.use(this, attacked, position, ammoColor);
            if (control) {
                this.powerup[counter] = null;
            }
            else
                System.out.println("Impossible to use this power up card");
        }
        else
            System.out.println("You don't have this power up");
    }


    public void drawPowerup(){
        int counter = 0;
        while (counter<3 && powerup[counter] != null)
            counter++;
        if(counter != 3) {
            this.powerup[counter] = this.powerUpDeck.pickUpPowerup();
            //System.out.println("The power up is: " + this.powerup[0].getName());
        }
        else
            System.out.println("You can't draw more power up cards");
    }

}
