package Adrenaline.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void constructorTest(){
        PowerupDeck pwd = new PowerupDeck();
        Player p = new Player(3, pwd);
        int[][] playersDamage = p.getPlayersDamage();
        int[] markGiven = new int[] {0, 0, 0, 0, 0};
        int[] markReceived = new int[] {0, 0, 0, 0, 0};
        int[] ammo = new int[] {1, 1, 1};

        assertNull(p.getPosition(), "Position not null");
        assertTrue(p.getLife()==11, "Life isn't correct");
        assertEquals(-1, playersDamage[0][0], "Order of player 1 isn't correct");
        assertEquals(0, playersDamage[0][1], "Total player 1 damage isn't correct");
        assertEquals(-1, playersDamage[1][0], "Order of player 2 isn't correct");
        assertEquals(0, playersDamage[1][1], "Total player 2 damage isn't correct");
        assertEquals(-1, playersDamage[2][0], "Order of player 3 isn't correct");
        assertEquals(0, playersDamage[2][1], "Total player 3 damage isn't correct");
        assertEquals(-1, playersDamage[3][0], "Order of player 4 isn't correct");
        assertEquals(0, playersDamage[3][1], "Total player 4 damage isn't correct");
        assertEquals(-1, playersDamage[4][0], "Order of player 5 isn't correct");
        assertEquals(0, playersDamage[4][1], "Total player 5 damage isn't correct");
        assertArrayEquals(markGiven, p.getMarksGiven(), "Marks given incorrect");
        assertArrayEquals(markReceived, p.getMarksReceived(), "Marks received incorrect");
        assertEquals(0, p.getNumberOfDeaths(), "Number of death incorrect");
        assertArrayEquals(ammo, p.getAmmo(), "Ammo incorrect");
        assertEquals(0, p.getScore(), "Score incorrect");
    }

    @Test
    public void endOfRoundTest(){
        PowerupDeck pwd = new PowerupDeck();
        Player p = new Player(0, pwd);
        p.endOfRound();

        assertEquals(false, p.isRound());
    }

    @Test
    public void receivedDamagesTest(){
        PowerupDeck pwd = new PowerupDeck();
        Player p = new Player(1, pwd);
        Player player3 = new Player(3, pwd);
        Player player2 = new Player(2, pwd);
        Player player1 = new Player(1, pwd);
        p.receivedDamages(player3);
        int life = p.getLife();
        int[][] playersDamage = p.getPlayersDamage();

        assertEquals(10, life, "Life isn't correct");
        assertEquals(1, playersDamage[3][0], "Order of player 4 isn't correct");
        assertEquals(1, playersDamage[3][1], "Total player 4 damage isn't correct");

        p.receivedDamages(player1);
        p.receivedDamages(player2);
        p.receivedDamages(player3);
        life = p.getLife();
        playersDamage = p.getPlayersDamage();

        assertEquals(7, life, "Life isn't correct");
        assertEquals(-1, playersDamage[0][0], "Order of player 1 isn't correct");
        assertEquals(0, playersDamage[0][1], "Total player 1 damage isn't correct");
        assertEquals(2, playersDamage[1][0], "Order of player 2 isn't correct");
        assertEquals(1, playersDamage[1][1], "Total player 2 damage isn't correct");
        assertEquals(3, playersDamage[2][0], "Order of player 3 isn't correct");
        assertEquals(1, playersDamage[2][1], "Total player 3 damage isn't correct");
        assertEquals(1, playersDamage[3][0], "Order of player 4 isn't correct");
        assertEquals(2, playersDamage[3][1], "Total player 4 damage isn't correct");
        assertEquals(-1, playersDamage[4][0], "Order of player 5 isn't correct");
        assertEquals(0, playersDamage[4][1], "Total player 5 damage isn't correct");

        Player player = new Player(2, pwd);
        int [] marksReceived = new int[]{0, 0, 0, 2, 0};
        player.setMarksReceived(player3, 2);
        player.receivedDamages(player3);
        life = player.getLife();
        playersDamage = player.getPlayersDamage();

        assertEquals(8, life, "Player's Life isn't correct");
        assertEquals(1, playersDamage[3][0], "Order of player 4 isn't correct");
        assertEquals(3, playersDamage[3][1], "Total player 4 damage isn't correct");
        assertEquals(0, player.getMarksReceived()[3], "Marks are not updated");

        player.receivedDamages(player1);
        player.receivedDamages(player3);
        marksReceived = new int[] {0, 3, 0, 0, 0};
        player.setMarksReceived(player1, 3); //controllare il player 3
        player.receivedDamages(player1);
        life = player.getLife();
        playersDamage = player.getPlayersDamage();

        assertEquals(2, life, "Player's Life isn't correct");
        assertEquals(2, playersDamage[1][0], "Order of player 2 isn't correct");
        assertEquals(5, playersDamage[1][1], "Total player 2 damage isn't correct");
        assertEquals(1, playersDamage[3][0], "Order of player 4 isn't correct");
        assertEquals(4, playersDamage[3][1], "Total player 4 damage isn't correct");
        assertEquals(0, marksReceived[3], "Marks are not updated");

        marksReceived = new int[] {0, 5, 0, 0, 0};
        player.setMarksReceived(player1, 5);
        player.receivedDamages(player1);
        player.receivedDamages(player3);
        life = player.getLife();
        playersDamage = player.getPlayersDamage();

        assertEquals(-1, life, "Player's Life isn't correct");
        assertEquals(2, playersDamage[1][0], "Order of player 2 isn't correct");
        assertEquals(8, playersDamage[1][1], "Total player 2 damage isn't correct");
        assertEquals(1, playersDamage[3][0], "Order of player 4 isn't correct");
        assertEquals(4, playersDamage[3][1], "Total player 4 damage isn't correct");
    }

    @Test
    public void sortedPlayersTest(){
        PowerupDeck pwd = new PowerupDeck();
        Player p1 = new Player(1, pwd);
        Player p3 = new Player(3, pwd);
        Player p2 = new Player(3, pwd);
        p1.receivedDamages(p3);
        p1.receivedDamages(p1);
        p1.receivedDamages(p2);
        p1.receivedDamages(p3);
        int [] sorted = p1.sortingPlayers();

        assertEquals(3, sorted[0], "First element incorrect");
        assertEquals(1, sorted[1], "Second element incorrect");
        assertEquals(2, sorted[2], "Third element incorrect");
        assertEquals(0, sorted[3], "Fourth element incorrect");
        assertEquals(4, sorted[4], "Fifth element incorrect");
    }

    @Test
    public void givePointsTest(){
        PowerupDeck pwd = new PowerupDeck();
        Player p = new Player(1, pwd);
        Player p2 = new Player(2, pwd);
        Player p3 = new Player(3, pwd);
        p.receivedDamages(p3);
        p.receivedDamages(p);
        p.receivedDamages(p2);
        p.receivedDamages(p3);
        int [] points = p.givePoints();

        assertEquals(0, points[0],"Player 1 points aren't correct");
        assertEquals(6, points[1],"Player 2 points aren't correct");
        assertEquals(4, points[2],"Player 3 points aren't correct");
        assertEquals(9, points[3],"Player 4 points aren't correct");
        assertEquals(0, points[4],"Player 5 points aren't correct");
    }

    @Test
    public void limitCasesTest(){               //Test on possible critical cases
        //All damages by only one player. After that, one other player attack (all the exceeding damages are thrown away.
        PowerupDeck pwd = new PowerupDeck();
        Player player = new Player(0, pwd);
        Player p4 = new Player(4, pwd);
        Player p1 = new Player(1, pwd);
        for(int i=0; i<12; i++)
            player.receivedDamages(p4);
        player.receivedDamages(p1);
        int [] points = player.givePoints();
        int life = player.getLife();
        int[][] playersDamage = player.getPlayersDamage();

        assertEquals(0, points[0],"Player 1 points aren't correct");
        assertEquals(0, points[1],"Player 2 points aren't correct");
        assertEquals(0, points[2],"Player 3 points aren't correct");
        assertEquals(0, points[3],"Player 4 points aren't correct");
        assertEquals(9, points[4],"Player 5 points aren't correct");

        assertEquals(-1, life, "Life isn't correct");
        assertEquals(-1, playersDamage[0][0], "Order of player 1 isn't correct");
        assertEquals(0, playersDamage[0][1], "Total player 1 damage isn't correct");
        assertEquals(-1, playersDamage[1][0], "Order of player 2 isn't correct");
        assertEquals(0, playersDamage[1][1], "Total player 2 damage isn't correct");
        assertEquals(-1, playersDamage[2][0], "Order of player 3 isn't correct");
        assertEquals(0, playersDamage[2][1], "Total player 3 damage isn't correct");
        assertEquals(-1, playersDamage[3][0], "Order of player 4 isn't correct");
        assertEquals(0, playersDamage[3][1], "Total player 4 damage isn't correct");
        assertEquals(1, playersDamage[4][0], "Order of player 5 isn't correct");
        assertEquals(12, playersDamage[4][1], "Total player 5 damage isn't correct");

        //no damage were made
        Player play = new Player(1, pwd);
        points = play.givePoints();
        life = play.getLife();
        playersDamage = play.getPlayersDamage();

        assertEquals(11, life, "Life isn't correct");
        assertEquals(-1, playersDamage[0][0], "Order of player 1 isn't correct");
        assertEquals(0, playersDamage[0][1], "Total player 1 damage isn't correct");
        assertEquals(-1, playersDamage[1][0], "Order of player 2 isn't correct");
        assertEquals(0, playersDamage[1][1], "Total player 2 damage isn't correct");
        assertEquals(-1, playersDamage[2][0], "Order of player 3 isn't correct");
        assertEquals(0, playersDamage[2][1], "Total player 3 damage isn't correct");
        assertEquals(-1, playersDamage[3][0], "Order of player 4 isn't correct");
        assertEquals(0, playersDamage[3][1], "Total player 4 damage isn't correct");
        assertEquals(-1, playersDamage[4][0], "Order of player 5 isn't correct");
        assertEquals(0, playersDamage[4][1], "Total player 5 damage isn't correct");
    }

    @Test
    public void actionMoveTest(){
        PowerupDeck pwd = new PowerupDeck();
        Player p = new Player(0, pwd);
        Position pos = new Position(1, 0, 'r', true, true);
        Position play = new Position(0, 0, 'r', true, false);
        p.roundBegin();
        p.setPosition(play);
        p.move(pos);

        assertEquals(1, p.getAction(), "Number of action isn't correct");
        assertEquals(1, p.getPosition().getCoordinate()[0], "Action Move doesn't work");

        p.move(play);
        p.move(pos);
    }

    @Test
    public void actionGrabTest (){
        PowerupDeck pwd = new PowerupDeck();
        Player p = new Player(0, pwd);
        Position pos = new Position(1, 0, 'r', true, true);
        p.setPosition(pos);
        p.roundBegin();
        p.grabAmmoCard();

        assertEquals(2, p.getAction(), "First action doesn't work");

        pos = new Position(1, 0, 'r', true, false);
        char[] value = new char[] {'b', 'y', 'r', '_'};
        AmmoCard ammoCard = new AmmoCard();
        ammoCard.setValue(value);
        p.setPosition(pos);
        p.getPosition().setAmmo(ammoCard);
        p.setPosition(pos);
        p.grabAmmoCard();
        p.grabAmmoCard();

        assertEquals(1, p.getAction(), "First action doesn't work");
        assertEquals(2, p.getAmmo()[0], "Blue ammo aren't correct");
        assertEquals(2, p.getAmmo()[1], "Yellow ammo aren't correct");
        assertEquals(2, p.getAmmo()[2], "Red ammo aren't correct");

        p = new Player(0, pwd);
        p.setAction(0);
        pos = new Position(1, 0, 'r', true, false);
        value = new char[] {'b', 'y', 'r', '_'};
        ammoCard = new AmmoCard();
        ammoCard.setValue(value);
        p.setPosition(pos);
        p.getPosition().setAmmo(ammoCard);
        p.setPosition(pos);
        p.grabAmmoCard();

        assertEquals(1, p.getAmmo()[0], "Blue ammo aren't correct");
        assertEquals(1, p.getAmmo()[1], "Yellow ammo aren't correct");
        assertEquals(1, p.getAmmo()[2], "Red ammo aren't correct");
    }

    @Test
    public void grabWeaponTest(){
        PowerupDeck pwd = new PowerupDeck();

        //Pay with ammo and powerupCards
        Board b = new Board(1);
        Player play = new Player(0, pwd);
        play.setAction(2);
        char[] cost = new char[]{'b', 'r', 'y'};
        WeaponCard weapon1 = new WeaponCard("weapon1", cost);
        cost = new char[]{'b', 'y'};
        WeaponCard weapon2 = new WeaponCard("weapon2", cost);
        Position pos = new Position(1, 0, 'r', true, true);
        pos.chooseArm(0);
        pos.giveWeapon(weapon1);
        pos.chooseArm(1);
        pos.giveWeapon(weapon2);
        play.setPosition(pos);
        char[]selectedAmmo = new char[]{'y'};
        PowerupCard[] power = new PowerupCard[]{new PowerupCard("pw1", 'r', b)};
        play.grabWeaponCard(pos.chooseArm(0), selectedAmmo, power);
        play.grabWeaponCard(pos.chooseArm(1), selectedAmmo, power);

        assertEquals(play.getWeapons()[0], weapon1);
        assertNull(play.getWeapons()[1]);

        //Pay with only ammo
        play.setAction(2);
        cost = new char[]{'r', 'b'};
        WeaponCard weapon = new WeaponCard("weapon", cost);
        pos.chooseArm(2);
        pos.giveWeapon(weapon);
        selectedAmmo = new char[]{'b'};
        play.grabWeaponCard(pos.chooseArm(2), selectedAmmo);

        assertEquals(play.getWeapons()[0], weapon1);
        assertEquals(play.getWeapons()[1], weapon);

        //pay with only powerup cards
        play.setAction(2);
        power = new PowerupCard[]{new PowerupCard("pw1", 'y', b)};
        play.grabWeaponCard(pos.chooseArm(1), power);

        assertEquals(play.getWeapons()[0], weapon1);
        assertEquals(play.getWeapons()[1], weapon);
        assertEquals(play.getWeapons()[2], weapon2);
    }

    @Test
    public void reloadTest(){
        PowerupDeck pwd = new PowerupDeck();
        Player p = new Player(1, pwd);
        Board b = new Board(1);
        char[] cost = new char[] {'r','r', 'b', 'b', 'y', 'y'};
        WeaponCard wp = new WeaponCard("name", cost);
        wp.setLoaded(false);
        PowerupCard[] pow = new PowerupCard[]{new PowerupCard("name", 'b', b), new PowerupCard("name2", 'r', b)};
        char[] ammo = new char[] {'b', 'y', 'r'};

        //Reload with only ammo

        //Weapon correctly reloaded
        p.setAmmo('b', 2);
        p.setAmmo('y', 2);
        p.setAmmo('r', 2);
        ammo = new char[] {'b', 'y', 'r', 'b', 'y', 'r'};
        p.reload(wp, ammo);

        assertTrue(wp.isLoaded(), "Weapon isn't loaded");
        assertEquals(0, p.getAmmo()[0], "Ble ammo aren't correct");
        assertEquals(0, p.getAmmo()[1], "Yellow ammo aren't correct");
        assertEquals(0, p.getAmmo()[2], "Red ammo aren't correct");

        //Error in each one ammo colour
        p.setAmmo('b', 2);
        p.setAmmo('y', 2);
        p.setAmmo('r', 2);
        cost = new char[] {'r','r', 'b', 'b', 'b', 'y', 'y'};
        wp = new WeaponCard("name", cost);
        wp.setLoaded(false);
        ammo = new char[] {'b', 'y', 'r', 'b', 'y', 'r', 'b'};
        p.reload(wp, ammo);

        assertFalse(wp.isLoaded(), "Weapon is loaded");
        assertEquals(2, p.getAmmo()[0], "Ble ammo aren't correct");
        assertEquals(2, p.getAmmo()[1], "Yellow ammo aren't correct");
        assertEquals(2, p.getAmmo()[2], "Red ammo aren't correct");

        p.setAmmo('b', 2);
        p.setAmmo('y', 1);
        p.setAmmo('r', 2);
        cost = new char[] {'r','r', 'b', 'b', 'y', 'y'};
        wp = new WeaponCard("name", cost);
        wp.setLoaded(false);
        ammo = new char[] {'b', 'y', 'r', 'b', 'y', 'r'};
        p.reload(wp, ammo);

        assertFalse(wp.isLoaded(), "Weapon is loaded");
        assertEquals(2, p.getAmmo()[0], "Ble ammo aren't correct");
        assertEquals(1, p.getAmmo()[1], "Yellow ammo aren't correct");
        assertEquals(2, p.getAmmo()[2], "Red ammo aren't correct");

        p.setAmmo('b', 2);
        p.setAmmo('y', 2);
        p.setAmmo('r', 1);
        cost = new char[] {'r','r', 'b', 'b', 'y', 'y'};
        wp = new WeaponCard("name", cost);
        wp.setLoaded(false);
        ammo = new char[] {'b', 'y', 'r', 'b', 'y', 'r'};
        p.reload(wp, ammo);

        assertFalse(wp.isLoaded(), "Weapon is loaded");
        assertEquals(2, p.getAmmo()[0], "Ble ammo aren't correct");
        assertEquals(2, p.getAmmo()[1], "Yellow ammo aren't correct");
        assertEquals(1, p.getAmmo()[2], "Red ammo aren't correct");

        //Less ammo selected
        p.setAmmo('b', 2);
        p.setAmmo('y', 2);
        p.setAmmo('r', 2);
        cost = new char[] {'r','r', 'b', 'b', 'y', 'y'};
        wp = new WeaponCard("name", cost);
        wp.setLoaded(false);
        ammo = new char[] {'y', 'r', 'b', 'y', 'r'};
        p.reload(wp, ammo);

        assertFalse(wp.isLoaded(), "Weapon is loaded");
        assertEquals(2, p.getAmmo()[0], "Ble ammo aren't correct");
        assertEquals(2, p.getAmmo()[1], "Yellow ammo aren't correct");
        assertEquals(2, p.getAmmo()[2], "Red ammo aren't correct");

        //More than necessary ammo are selected
        p.setAmmo('b', 2);
        p.setAmmo('y', 2);
        p.setAmmo('r', 2);
        cost = new char[] {'r', 'b', 'b', 'y', 'y'};
        wp = new WeaponCard("name", cost);
        wp.setLoaded(false);
        ammo = new char[] {'b', 'y', 'r', 'b', 'y', 'r'};
        p.reload(wp, ammo);

        assertFalse(wp.isLoaded(), "Weapon is loaded");
        assertEquals(2, p.getAmmo()[0], "Ble ammo aren't correct");
        assertEquals(2, p.getAmmo()[1], "Yellow ammo aren't correct");
        assertEquals(2, p.getAmmo()[2], "Red ammo aren't correct");

        //Reload with only power up

        //Weapon correctly reloaded
        pow = new PowerupCard[]{new PowerupCard("name", 'b', b), new PowerupCard("name2", 'r', b)};
        cost = new char[] {'r', 'b'};
        wp = new WeaponCard("name", cost);
        wp.setLoaded(false);
        p.setPowerup(pow.clone());
        p.reload(wp, pow);

        assertTrue(wp.isLoaded(), "Weapon isn't loaded");
        assertNull(p.getPowerup()[0], "First power up isn't correct");
        assertNull(p.getPowerup()[1], "Second power up isn't correct");

        //Not correct power up selected
        pow = new PowerupCard[]{new PowerupCard("name", 'b', b), new PowerupCard("name2", 'r', b)};
        cost = new char[] {'r', 'b'};
        wp = new WeaponCard("name", cost);
        wp.setLoaded(false);
        p.setPowerup(pow);
        PowerupCard[] power = new PowerupCard[]{new PowerupCard("name", 'r', b), new PowerupCard("name2", 'r', b)};
        p.reload(wp, power);

        assertFalse(wp.isLoaded(), "Weapon is loaded");
        assertNotNull(p.getPowerup()[0], "First power up isn't correct");
        assertNotNull(p.getPowerup()[1], "Second power up isn't correct");

        //Correct ammo selected, but not present in this.ammo
        pow = new PowerupCard[]{new PowerupCard("name", 'b', b), new PowerupCard("name2", 'r', b)};
        cost = new char[] {'r', 'r'};
        wp = new WeaponCard("name", cost);
        wp.setLoaded(false);
        p.setPowerup(pow);
        power = new PowerupCard[]{new PowerupCard("name", 'r', b), new PowerupCard("name2", 'r', b)};
        p.reload(wp, power);

        assertFalse(wp.isLoaded(), "Weapon is loaded");
        assertNotNull(p.getPowerup()[0], "First power up isn't correct");
        assertNotNull(p.getPowerup()[1], "Second power up isn't correct");

        //More than necessary power up are selected
        pow = new PowerupCard[]{new PowerupCard("name", 'b', b), new PowerupCard("name2", 'r', b)};
        cost = new char[] {'b', 'r'};
        wp = new WeaponCard("name", cost);
        wp.setLoaded(false);
        p.setPowerup(pow);
        power = new PowerupCard[]{new PowerupCard("name", 'b', b), new PowerupCard("name2", 'r', b), new PowerupCard("name3", 'b', b)};
        PowerupCard testPU = p.getPowerup()[0];
        p.reload(wp, power);

        assertFalse(wp.isLoaded(), "Weapon is loaded");
        assertNotNull(p.getPowerup()[0], "First power up isn't correct");
        assertNotNull(p.getPowerup()[1], "Second power up isn't correct");
        assertEquals(testPU, p.getPowerup()[0], "First power up isn't correct");

        //Reload with ammo and power up

        //Weapon correctly reloaded
        pow = new PowerupCard[]{new PowerupCard("name", 'b', b), new PowerupCard("name2", 'r', b)};
        cost = new char[] {'b', 'b', 'r', 'r', 'y', 'y'};
        wp = new WeaponCard("name", cost);
        wp.setLoaded(false);
        p.setPowerup(pow);
        p.setAmmo('b', 2);
        p.setAmmo('y', 2);
        p.setAmmo('r', 2);
        power = new PowerupCard[]{new PowerupCard("name", 'b', b), new PowerupCard("name2", 'r', b)};
        ammo = new char[] {'b', 'r', 'y', 'y'};
        p.reload(wp, ammo, power);

        assertTrue(wp.isLoaded(), "Weapon isn't loaded");
        assertEquals(1, p.getAmmo()[0], "Ble ammo aren't correct");
        assertEquals(0, p.getAmmo()[1], "Yellow ammo aren't correct");
        assertEquals(1, p.getAmmo()[2], "Red ammo aren't correct");
        assertNull(p.getPowerup()[0], "First power up isn't correct");
        assertNull(p.getPowerup()[1], "Second power up isn't correct");

        //More than needed power up are selected
        pow = new PowerupCard[]{new PowerupCard("name", 'b', b), new PowerupCard("name2", 'r', b), new PowerupCard("name3", 'r', b)};
        cost = new char[] {'b', 'b', 'r', 'r', 'y', 'y'};
        wp = new WeaponCard("name", cost);
        wp.setLoaded(false);
        p.setPowerup(pow);
        p.setAmmo('b', 2);
        p.setAmmo('y', 2);
        p.setAmmo('r', 2);
        power = new PowerupCard[]{new PowerupCard("name", 'b', b), new PowerupCard("name2", 'r', b), new PowerupCard("name3", 'r', b)};
        testPU = p.getPowerup()[0];
        ammo = new char[] {'b', 'r', 'y', 'y'};
        p.reload(wp, ammo, power);

        assertFalse(wp.isLoaded(), "Weapon is loaded");
        assertEquals(2, p.getAmmo()[0], "Ble ammo aren't correct");
        assertEquals(2, p.getAmmo()[1], "Yellow ammo aren't correct");
        assertEquals(2, p.getAmmo()[2], "Red ammo aren't correct");
        assertNotNull(p.getPowerup()[0], "First power up isn't correct");
        assertNotNull(p.getPowerup()[1], "Second power up isn't correct");
        assertEquals(testPU, p.getPowerup()[0], "First power up isn't correct");

        //More than needed ammo are selected
        pow = new PowerupCard[]{new PowerupCard("name", 'b', b), new PowerupCard("name2", 'r', b), new PowerupCard("name3", 'r', b)};
        cost = new char[] {'b', 'b', 'r', 'r', 'y', 'y'};
        wp = new WeaponCard("name", cost);
        wp.setLoaded(false);
        p.setPowerup(pow);
        p.setAmmo('b', 2);
        p.setAmmo('y', 2);
        p.setAmmo('r', 2);
        power = new PowerupCard[]{new PowerupCard("name", 'b', b), new PowerupCard("name2", 'r', b)};
        testPU = p.getPowerup()[0];
        ammo = new char[] {'b', 'b', 'r', 'y', 'y'};
        p.reload(wp, ammo, power);

        assertFalse(wp.isLoaded(), "Weapon is loaded");
        assertEquals(2, p.getAmmo()[0], "Ble ammo aren't correct");
        assertEquals(2, p.getAmmo()[1], "Yellow ammo aren't correct");
        assertEquals(2, p.getAmmo()[2], "Red ammo aren't correct");
        assertNotNull(p.getPowerup()[0], "First power up isn't correct");
        assertNotNull(p.getPowerup()[1], "Second power up isn't correct");
        assertEquals(testPU, p.getPowerup()[0], "First power up isn't correct");

        //Less than needed power up are selected
        pow = new PowerupCard[]{new PowerupCard("name", 'b', b), new PowerupCard("name2", 'r', b), new PowerupCard("name3", 'r', b)};
        cost = new char[] {'b', 'b', 'r', 'r', 'y', 'y'};
        wp = new WeaponCard("name", cost);
        wp.setLoaded(false);
        p.setPowerup(pow);
        p.setAmmo('b', 2);
        p.setAmmo('y', 2);
        p.setAmmo('r', 2);
        power = new PowerupCard[]{new PowerupCard("name", 'b', b)};
        testPU = p.getPowerup()[0];
        ammo = new char[] {'b', 'b', 'r', 'y', 'y'};
        p.reload(wp, ammo, power);

        assertFalse(wp.isLoaded(), "Weapon is loaded");
        assertEquals(2, p.getAmmo()[0], "Ble ammo aren't correct");
        assertEquals(2, p.getAmmo()[1], "Yellow ammo aren't correct");
        assertEquals(2, p.getAmmo()[2], "Red ammo aren't correct");
        assertNotNull(p.getPowerup()[0], "First power up isn't correct");
        assertNotNull(p.getPowerup()[1], "Second power up isn't correct");
        assertEquals(testPU, p.getPowerup()[0], "First power up isn't correct");

        //Selected pw and ammo are correct, but not present in this.ammo and in this.powerup
        power = new PowerupCard[]{new PowerupCard("name", 'b', b), new PowerupCard("name2", 'r', b), new PowerupCard("name3", 'y', b)};
        cost = new char[] {'b', 'b', 'b', 'r', 'r', 'r', 'y', 'y', 'y'};
        wp = new WeaponCard("name", cost);
        wp.setLoaded(false);
        p.setPowerup(pow);
        p.setAmmo('b', 1);
        p.setAmmo('y', 1);
        p.setAmmo('r', 1);
        pow = new PowerupCard[]{new PowerupCard("name", 'b', b)};
        testPU = p.getPowerup()[0];
        ammo = new char[] {'b', 'b', 'r', 'r', 'y', 'y'};
        p.reload(wp, ammo, power);

        assertFalse(wp.isLoaded(), "Weapon is loaded");
        assertEquals(1, p.getAmmo()[0], "Ble ammo aren't correct");
        assertEquals(1, p.getAmmo()[1], "Yellow ammo aren't correct");
        assertEquals(1, p.getAmmo()[2], "Red ammo aren't correct");
        assertNotNull(p.getPowerup()[0], "First power up isn't correct");
        assertNotNull(p.getPowerup()[1], "Second power up isn't correct");
        assertEquals(testPU, p.getPowerup()[0], "First power up isn't correct");
    }

    @Test
    public void shotTest(){
        PowerupDeck pwd = new PowerupDeck();
        Player p = new Player(1, pwd);
        Board b = new Board(1);
        char[] cost = new char[] {'r','r'};
        WeaponCard wp = new WeaponCard("name", cost);

        p.setAction(2);
        p.shot(wp); //TODO gives error -> andre: a me non da nessun errore

        assertEquals(2, p.getAction(), "Shot doesn't work");

        p.setAction(0);
        p.shot(wp);
        assertEquals(0, p.getAction(), "Shot doesn't work");
    }

    @Test
    public void respawnTest(){
        PowerupDeck pwd = new PowerupDeck();
        Player p = new Player(1, pwd);
        Board b = new Board(1);
        Position testPosition = new Position(0, 2, 'b', true, false);
        PowerupCard pu1 = new PowerupCard("Name1", 'b', b);
        PowerupCard pu2 = new PowerupCard("Name2", 'r', b);
        PowerupCard pu3 = new PowerupCard("Name2", 'r', b);
        PowerupCard[] pu = new PowerupCard[3];
        pu[0] = pu1;
        pu[1] = pu2;
        pu[2] = pu3;

        p.setPowerup(pu);
        p.respawn(pu1, testPosition);

        assertNotEquals(testPosition, p.getPosition(), "Position is not correct");

        Position position = new Position(0, 0, 'b', true, false);
        p.setPosition(position);
        testPosition = new Position(1, 2, 'b', true, true);
        p.respawn(pu1, testPosition);

        assertNull(p.getPowerup()[0], "First powerup not used");
        assertEquals(testPosition, p.getPosition(), "Player isn't respawned");

        p.setPosition(position);
        p.respawn(pu1, testPosition);

        assertNotEquals(testPosition, p.getPosition(), "Player is respawned");
    }

    @Test
    public void usePowerUpTest(){
        PowerupDeck pwd = new PowerupDeck();
        Player p = new Player(1, pwd);
        Board b = new Board(1);
        PowerupCard pu = new PowerupCard("Name", 'b', b);

        p.usePowerup(pu);
        //todo finire implementazione
    }

    @Test
    public void setterGetterTest (){
        PowerupDeck pwd = new PowerupDeck();
        Player p = new Player(0, pwd);
        Board b = new Board(1);
        int[] marks = new int[] {0, 1, 4, 2, 1};
        char[] cost = new char[] {'r','r'};
        p.setScore(2);
        p.setFinalRound(true);
        p.setFirstPlayer(true);
        p.setRound(true);
        p.setAmmo('b', 1);
        p.setAmmo('y', 2);
        p.setAmmo('r', 3);
        p.getAmmo('t');
        int ammoTest1 = p.getAmmo('b');
        int ammoTest2 = p.getAmmo('y');
        int ammoTest3 = p.getAmmo('r');

        assertEquals(2, p.getScore(), "SetScore never works");
        assertTrue(p.isFinalRound(), "Final Round is false");
        assertTrue(p.isFirstPlayer(), "First player is false");
        assertTrue(p.isRound(), "Round is false");
        assertEquals(0, p.getNumber());
        assertEquals(1, ammoTest1, "Blue ammo incorrect");
        assertEquals(2, ammoTest2, "Yellow ammo incorrect");
        assertEquals(3, ammoTest3, "Red ammo incorrect");

        p.setAmmo('b', 5);
        p.setAmmo('y', 5);
        p.setAmmo('r', 5);
        ammoTest1 = p.getAmmo('b');
        ammoTest2 = p.getAmmo('y');
        ammoTest3 = p.getAmmo('r');
        assertEquals(3, ammoTest1, "Blue ammo incorrect");
        assertEquals(3, ammoTest2, "Yellow ammo incorrect");
        assertEquals(3, ammoTest3, "Red ammo incorrect");

        p.setMarksGiven(new Player(2, pwd), 4);

        assertEquals(3, p.getMarksGiven()[2]);
    }
}