package Adrenaline.Server.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void constructorTest(){
        Board b = new Board(1);
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
        Board b = new Board(1);
        PowerupDeck pwd = new PowerupDeck();
        Player p = new Player(0, pwd);
        p.endOfRound();

        assertEquals(false, p.isRound());
    }

    @Test
    public void receivedDamagesTest(){
        Board b = new Board(1);
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
        Board b = new Board(1);
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
        Board b = new Board(1);
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
        Board b = new Board(1);
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
        Board b = new Board(1);
        PowerupDeck pwd = new PowerupDeck();
        Player p = new Player(0, pwd);
        AmmoDeck ad = new AmmoDeck();
        WeaponDeck wd = new WeaponDeck();
        Position[] pos = {new Position(1, 0, 'r', true, true, ad, wd)};
        Position[] play = {new Position(0, 0, 'r', true, false, ad, wd)};
        p.roundBegin();
        p.setFirstPosition(play[0]);
        p.move(pos);

        assertEquals(1, p.getAction(), "Number of action isn't correct");
        assertEquals(1, p.getPosition().getCoordinate()[0], "Action Move doesn't work");

        p.move(play);
        p.move(pos);
    }

    @Test
    public void actionGrabTest (){
        Board b = new Board(1);
        PowerupDeck pwd = new PowerupDeck();
        Player p = new Player(0, pwd);
        AmmoDeck ad = new AmmoDeck();
        WeaponDeck wd = new WeaponDeck();
        Position pos = new Position(1, 0, 'r', true, true, ad, wd);
        p.setFirstPosition(pos);
        p.roundBegin();
        p.grabAmmoCard();

        assertEquals(2, p.getAction(), "First action doesn't work");

        pos = new Position(1, 0, 'r', true, false, ad, wd);
        char[] value = new char[] {'b', 'y', 'r', 'p'};
        AmmoCard ammoCard = new AmmoCard(value);
        p.setFirstPosition(pos);
        p.getPosition().setAmmo(ammoCard);
        p.setFirstPosition(pos);
        p.grabAmmoCard();
        p.grabAmmoCard();

        assertEquals(1, p.getAction(), "First action doesn't work");
        assertEquals(2, p.getAmmo()[0], "Blue ammo aren't correct");
        assertEquals(2, p.getAmmo()[1], "Yellow ammo aren't correct");
        assertEquals(2, p.getAmmo()[2], "Red ammo aren't correct");
        assertNotNull(p.getPowerup()[0], "Power up cards aren't correct");

        p = new Player(0, pwd);
        p.setAction(0);
        pos = new Position(1, 0, 'r', true, false, ad, wd);
        value = new char[] {'b', 'y', 'r', '_'};
        ammoCard = new AmmoCard(value);
        p.setFirstPosition(pos);
        p.getPosition().setAmmo(ammoCard);
        p.setFirstPosition(pos);
        p.grabAmmoCard();

        assertEquals(1, p.getAmmo()[0], "Blue ammo aren't correct");
        assertEquals(1, p.getAmmo()[1], "Yellow ammo aren't correct");
        assertEquals(1, p.getAmmo()[2], "Red ammo aren't correct");
    }

    @Test
    public void grabWeaponTest(){
        Board b = new Board(1);
        PowerupDeck pwd = new PowerupDeck();
        AmmoDeck ad = new AmmoDeck();
        WeaponDeck wd = new WeaponDeck();

        //Pay with ammo and powerupCards
        Player play = new Player(0, pwd);
        play.setAction(2);
        char[] cost = new char[]{'b', 'r', 'y'};
        WeaponCard weapon1 = new WeaponCard("weapon1", cost, cost);
        cost = new char[]{'b', 'y'};
        WeaponCard weapon2 = new WeaponCard("weapon2", cost, cost);
        cost = new char[]{'r', 'b'};
        WeaponCard weapon = new WeaponCard("weapon", cost, cost);
        Position pos = new Position(1, 0, 'r', true, true, ad, wd);
        pos.chooseArm(0);
        pos.chooseArm(1);
        pos.chooseArm(2);
        pos.giveWeapon(weapon1);
        pos.giveWeapon(weapon2);
        pos.giveWeapon(weapon);
        play.setFirstPosition(pos);

        char[]selectedAmmo = new char[]{'b', 'b'};
        play.grabWeaponCard(weapon1, selectedAmmo);
        PowerupCard[] power = new PowerupCard[]{new PowerupCard("pw1", 'b')};
        play.grabWeaponCard(weapon1, power);

        selectedAmmo = new char[]{'y'};
        power = new PowerupCard[]{new PowerupCard("pw1", 'r')};
        WeaponCard temp = pos.chooseArm(0);
        boolean control = play.grabWeaponCard(temp, selectedAmmo, power);
        if(!control)
            pos.giveWeapon(temp);
        temp = pos.chooseArm(1);
        control = play.grabWeaponCard(temp, selectedAmmo, power);
        if(!control)
            pos.giveWeapon(temp);

        assertEquals(play.getWeapons()[0], weapon1);
        assertNull(play.getWeapons()[1]);

        //Pay with only ammo
        play.setAction(2);
        pos.giveWeapon(weapon);
        selectedAmmo = new char[]{'b'};
        play.grabWeaponCard(pos.chooseArm(2), selectedAmmo);

        assertEquals(play.getWeapons()[0], weapon1);
        assertEquals(play.getWeapons()[1], weapon);

        //pay with only powerup cards
        play.setAction(2);
        power = new PowerupCard[]{new PowerupCard("pw1", 'y')};
        play.grabWeaponCard(pos.chooseArm(0), power);

        assertEquals(play.getWeapons()[0], weapon1);
        assertEquals(play.getWeapons()[1], weapon);
        assertEquals(play.getWeapons()[2], weapon2);
    }

    @Test
    public void reloadTest(){
        Board b = new Board(1);
        PowerupDeck pwd = new PowerupDeck();
        Player p = new Player(1, pwd);
        char[] cost = new char[] {'r','r', 'b', 'b', 'y', 'y'};
        WeaponCard wp = new WeaponCard("name", cost, cost);
        wp.setLoaded(false);
        PowerupCard[] pow = new PowerupCard[]{new PowerupCard("name", 'b'), new PowerupCard("name2", 'r')};
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
        wp = new WeaponCard("name", cost, cost);
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
        wp = new WeaponCard("name", cost, cost);
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
        wp = new WeaponCard("name", cost, cost);
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
        wp = new WeaponCard("name", cost, cost);
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
        wp = new WeaponCard("name", cost, cost);
        wp.setLoaded(false);
        ammo = new char[] {'b', 'y', 'r', 'b', 'y', 'r'};
        p.reload(wp, ammo);

        assertFalse(wp.isLoaded(), "Weapon is loaded");
        assertEquals(2, p.getAmmo()[0], "Ble ammo aren't correct");
        assertEquals(2, p.getAmmo()[1], "Yellow ammo aren't correct");
        assertEquals(2, p.getAmmo()[2], "Red ammo aren't correct");

        //Reload with only power up

        //Weapon correctly reloaded
        pow = new PowerupCard[]{new PowerupCard("name", 'b'), new PowerupCard("name2", 'r')};
        cost = new char[] {'r', 'b'};
        wp = new WeaponCard("name", cost, cost);
        wp.setLoaded(false);
        p.setPowerup(pow.clone());
        p.reload(wp, pow);

        assertTrue(wp.isLoaded(), "Weapon isn't loaded");
        assertNull(p.getPowerup()[0], "First power up isn't correct");
        assertNull(p.getPowerup()[1], "Second power up isn't correct");

        //Not correct power up selected
        pow = new PowerupCard[]{new PowerupCard("name", 'b'), new PowerupCard("name2", 'r')};
        cost = new char[] {'r', 'b'};
        wp = new WeaponCard("name", cost, cost);
        wp.setLoaded(false);
        p.setPowerup(pow);
        PowerupCard[] power = new PowerupCard[]{new PowerupCard("name", 'r'), new PowerupCard("name2", 'r')};
        p.reload(wp, power);

        assertFalse(wp.isLoaded(), "Weapon is loaded");
        assertNotNull(p.getPowerup()[0], "First power up isn't correct");
        assertNotNull(p.getPowerup()[1], "Second power up isn't correct");

        //Correct ammo selected, but not present in this.ammo
        pow = new PowerupCard[]{new PowerupCard("name", 'b'), new PowerupCard("name2", 'r')};
        cost = new char[] {'r', 'r'};
        wp = new WeaponCard("name", cost, cost);
        wp.setLoaded(false);
        p.setPowerup(pow);
        power = new PowerupCard[]{new PowerupCard("name", 'r'), new PowerupCard("name2", 'r')};
        p.reload(wp, power);

        assertFalse(wp.isLoaded(), "Weapon is loaded");
        assertNotNull(p.getPowerup()[0], "First power up isn't correct");
        assertNotNull(p.getPowerup()[1], "Second power up isn't correct");

        //More than necessary power up are selected
        pow = new PowerupCard[]{new PowerupCard("name", 'b'), new PowerupCard("name2", 'r')};
        cost = new char[] {'b', 'r'};
        wp = new WeaponCard("name", cost, cost);
        wp.setLoaded(false);
        p.setPowerup(pow);
        power = new PowerupCard[]{new PowerupCard("name", 'b'), new PowerupCard("name2", 'r'), new PowerupCard("name3", 'b')};
        PowerupCard testPU = p.getPowerup()[0];
        p.reload(wp, power);

        assertFalse(wp.isLoaded(), "Weapon is loaded");
        assertNotNull(p.getPowerup()[0], "First power up isn't correct");
        assertNotNull(p.getPowerup()[1], "Second power up isn't correct");
        assertEquals(testPU, p.getPowerup()[0], "First power up isn't correct");

        //Reload with ammo and power up

        //Weapon correctly reloaded
        pow = new PowerupCard[]{new PowerupCard("name", 'b'), new PowerupCard("name2", 'r')};
        cost = new char[] {'b', 'b', 'r', 'r', 'y', 'y'};
        wp = new WeaponCard("name", cost, cost);
        wp.setLoaded(false);
        p.setPowerup(pow);
        p.setAmmo('b', 2);
        p.setAmmo('y', 2);
        p.setAmmo('r', 2);
        power = new PowerupCard[]{new PowerupCard("name", 'b'), new PowerupCard("name2", 'r')};
        ammo = new char[] {'b', 'r', 'y', 'y'};
        p.reload(wp, ammo, power);

        assertTrue(wp.isLoaded(), "Weapon isn't loaded");
        assertEquals(1, p.getAmmo()[0], "Ble ammo aren't correct");
        assertEquals(0, p.getAmmo()[1], "Yellow ammo aren't correct");
        assertEquals(1, p.getAmmo()[2], "Red ammo aren't correct");
        assertNull(p.getPowerup()[0], "First power up isn't correct");
        assertNull(p.getPowerup()[1], "Second power up isn't correct");

        //More than needed power up are selected
        pow = new PowerupCard[]{new PowerupCard("name", 'b'), new PowerupCard("name2", 'r'), new PowerupCard("name3", 'r')};
        cost = new char[] {'b', 'b', 'r', 'r', 'y', 'y'};
        wp = new WeaponCard("name", cost, cost);
        wp.setLoaded(false);
        p.setPowerup(pow);
        p.setAmmo('b', 2);
        p.setAmmo('y', 2);
        p.setAmmo('r', 2);
        power = new PowerupCard[]{new PowerupCard("name", 'b'), new PowerupCard("name2", 'r'), new PowerupCard("name3", 'r')};
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
        pow = new PowerupCard[]{new PowerupCard("name", 'b'), new PowerupCard("name2", 'r'), new PowerupCard("name3", 'r')};
        cost = new char[] {'b', 'b', 'r', 'r', 'y', 'y'};
        wp = new WeaponCard("name", cost, cost);
        wp.setLoaded(false);
        p.setPowerup(pow);
        p.setAmmo('b', 2);
        p.setAmmo('y', 2);
        p.setAmmo('r', 2);
        power = new PowerupCard[]{new PowerupCard("name", 'b'), new PowerupCard("name2", 'r')};
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
        pow = new PowerupCard[]{new PowerupCard("name", 'b'), new PowerupCard("name2", 'r'), new PowerupCard("name3", 'r')};
        cost = new char[] {'b', 'b', 'r', 'r', 'y', 'y'};
        wp = new WeaponCard("name", cost, cost);
        wp.setLoaded(false);
        p.setPowerup(pow);
        p.setAmmo('b', 2);
        p.setAmmo('y', 2);
        p.setAmmo('r', 2);
        power = new PowerupCard[]{new PowerupCard("name", 'b')};
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
        power = new PowerupCard[]{new PowerupCard("name", 'b'), new PowerupCard("name2", 'r'), new PowerupCard("name3", 'y')};
        cost = new char[] {'b', 'b', 'b', 'r', 'r', 'r', 'y', 'y', 'y'};
        wp = new WeaponCard("name", cost, cost);
        wp.setLoaded(false);
        p.setPowerup(pow);
        p.setAmmo('b', 1);
        p.setAmmo('y', 1);
        p.setAmmo('r', 1);
        pow = new PowerupCard[]{new PowerupCard("name", 'b')};
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
        Board b = new Board(1);
        PowerupDeck pd = new PowerupDeck();
        AmmoDeck ad = new AmmoDeck();
        WeaponDeck wd = new WeaponDeck();
        Player p0 = new Player(0, pd);
        p0.setAction(2);
        Player p1 = new Player(1, pd);
        Player p2 = new Player(2, pd);
        Player p3 = new Player(3, pd);

        //Test shot with weapon with just one mode
        WeaponCard wep0 = new WCHeatseeker();
        WeaponCard wep = new WCZX2();
        Position[][] board = b.getBoard();
        Position pos0 = board[0][2];
        Position pos1 = board[1][2];
        Position pos2 = board[0][1];
        Position pos3 = board[1][2];
        p2.setFirstPosition(pos2);
        p3.setFirstPosition(pos3);
        pos0.chooseArm(0);
        pos0.chooseArm(1);
        pos0.chooseArm(2);
        pos0.giveWeapon(wep0);
        p0.setFirstPosition(pos0);
        p1.setFirstPosition(pos1);
        char[] selAmmo = new char[]{'r', 'y'};
        p0.grabWeaponCard(wep0, selAmmo);
        Player[] playerAttacked = {p1};
        int[] mode2 = new int[] {-1};
        p0.shot(wep0, playerAttacked, -1, mode2, null, null);
        p0.shot(wep, playerAttacked, -1, mode2, null, null);

        assertEquals(11, p1.getLife(), "P1 life isn't correct");

        pos1 = b.getBoard()[1][3];
        p1.setFirstPosition(pos1);
        p0.shot(wep0, playerAttacked, -1, mode2, null, null);

        assertEquals(8, p1.getLife(), "P1 life isn't correct");

        p0.setAction(0);
        p0.shot(wep0, playerAttacked, -1, mode2, null, null);

        assertEquals(8, p1.getLife(), "P1 life isn't correct");

        //Test shot with weapon with more optional mode

        //Base effect only
        p0.setAction(2);
        p0.setAmmo('r', 1);
        WeaponCard wep1 = new WCLockRifle();
        pos0.chooseArm(0);
        pos0.chooseArm(1);
        pos0.chooseArm(2);
        pos0.giveWeapon(wep1);
        selAmmo = new char[]{'b'};
        p0.grabWeaponCard(wep1, selAmmo);
        playerAttacked = new Player[]{p1};
        pos1 = new Position(0, 0, 'b', true, false, ad, wd);
        p1.setFirstPosition(pos1);
        mode2 = new int[] {0};
        p0.shot(wep1, playerAttacked, -1, mode2, null, null);

        assertEquals(6, p1.getLife(), "P1 life isn't correct");
        assertEquals(1, p1.getMarksReceived()[0], "P1 received marks aren't corrected");
        assertEquals(1, p0.getMarksGiven()[1], "P0 given marks aren't correct");

        //With optional effect
        playerAttacked = new Player[]{p1, p2};
        p2.setFirstPosition(pos1);
        p0.setAmmo('b', 2);
        p0.setAmmo('r', 1);
        p0.setAction(2);
        selAmmo = new char[] {'b', 'b'};
        p0.reload(wep1, selAmmo);
        mode2 = new int[] {0, 1};
        p0.shot(wep1, playerAttacked, -1, mode2, null, null);

        assertEquals(3, p1.getLife(), "P1 life isn't correct");
        assertEquals(1, p1.getMarksReceived()[0], "P1 received marks aren't corrected");
        assertEquals(1, p0.getMarksGiven()[1], "P0 given marks to p1 aren't correct");
        assertEquals(1, p2.getMarksReceived()[0], "P2 received marks aren't corrected");
        assertEquals(1, p0.getMarksGiven()[2], "P0 given marks to p2 aren't correct");

        //Test shot with weapon with two different fire modes

        //First mode
        p0.setAction(2);
        p0.setAmmo('r', 1);
        WeaponCard wep2 = new WCZX2();
        pos0.chooseArm(0);
        pos0.chooseArm(1);
        pos0.chooseArm(2);
        pos0.giveWeapon(wep2);
        p0.setMarksGiven(p1, 0);
        p1.setMarksReceived(p0, 0);
        selAmmo = new char[]{'r'};
        p0.grabWeaponCard(wep2, selAmmo);
        playerAttacked = new Player[]{p1};
        int mode1 = 0;
        p0.shot(wep2, playerAttacked, mode1, null, null, null);

        assertEquals(2, p1.getLife(), "Life p1 isn't correct");
        assertEquals(2, p0.getMarksGiven()[1], "Marks given to p1 aren't correct");
        assertEquals(2, p1.getMarksReceived()[0], "p1 marks received from p0 are't correct");

        //Second mode
        p0.setAction(2);
        p0.setAmmo('r', 1);
        p0.setAmmo('y', 1);
        selAmmo = new char[]{'y', 'r'};
        p0.reload(wep2, selAmmo);
        p0.setMarksGiven(p1, 0);
        p1.setMarksReceived(p0, 0);
        p0.setMarksGiven(p2, 0);
        p2.setMarksReceived(p0, 0);
        p0.setMarksGiven(p3, 0);
        p3.setMarksReceived(p0, 0);
        playerAttacked = new Player[]{p1, p2, p3};
        mode1 = 1;
        p0.shot(wep2, playerAttacked, mode1, null, null, null);

        assertTrue(pos0.visible(pos3));
        assertEquals(2, p1.getLife(), "Life p1 isn't correct");
        assertEquals(1, p0.getMarksGiven()[1], "Marks given to p1 aren't correct");
        assertEquals(1, p1.getMarksReceived()[0], "p1 marks received from p0 are't correct");
        assertEquals(1, p0.getMarksGiven()[2], "Marks given to p2 aren't correct");
        assertEquals(1, p2.getMarksReceived()[0], "p2 marks received from p0 are't correct");
        assertEquals(1, p0.getMarksGiven()[3], "Marks given to p3 aren't correct");
        assertEquals(1, p3.getMarksReceived()[0], "p3 marks received from p0 are't correct");
    }

    @Test
    public void respawnTest(){
        Board b = new Board(1);
        PowerupDeck pwd = new PowerupDeck();
        Player p = new Player(1, pwd);
        AmmoDeck ad = new AmmoDeck();
        WeaponDeck wd = new WeaponDeck();
        Position testPosition = new Position(0, 2, 'b', true, false, ad, wd);
        PowerupCard pu1 = new PowerupCard("Name1", 'b');
        PowerupCard pu2 = new PowerupCard("Name2", 'r');
        PowerupCard pu3 = new PowerupCard("Name2", 'r');
        PowerupCard[] pu = new PowerupCard[3];
        pu[0] = pu1;
        pu[1] = pu2;
        pu[2] = pu3;

        p.setPowerup(pu);
        p.respawn(pu1, testPosition);

        assertNotEquals(testPosition, p.getPosition(), "Position is not correct");

        Position position = new Position(0, 0, 'b', true, false, ad, wd);
        p.setFirstPosition(position);
        testPosition = new Position(1, 2, 'b', true, true, ad, wd);
        p.respawn(pu1, testPosition);

        assertNull(p.getPowerup()[0], "First powerup not used");
        assertEquals(testPosition, p.getPosition(), "Player isn't respawned");

        p.setFirstPosition(position);
        p.respawn(pu1, testPosition);

        assertNotEquals(testPosition, p.getPosition(), "Player is respawned");
    }

    @Test
    public void setterGetterTest (){
        Board b = new Board(1);
        PowerupDeck pwd = new PowerupDeck();
        Player p = new Player(0, pwd);
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

    @Test
    public void moveAndGrabTest(){
        Board b = new Board(1);
        PowerupDeck pd = new PowerupDeck();
        Player p0 = new Player(0, pd);
        p0.setFirstPosition(b.getBoard()[0][0]);
        p0.setAction(2);
        Position[] move = new Position[] {b.getBoard()[0][1]};
        p0.moveAndGrab(move);

        assertEquals(2, p0.getAction(), "Life isn't correct");

        p0.setAction(0);
        move = new Position[] {b.getBoard()[0][2]};
        p0.moveAndGrab(move);

        assertEquals(b.getBoard()[0][1], p0.getPosition(), "Position isn't correct");
        assertEquals(0, p0.getAction(), "Life isn't correct");
    }

    @Test
    public void updateAmmoTest(){   //public boolean updateAmmo(int[] am)
        Board b = new Board(1);
        PowerupDeck pd = new PowerupDeck();
        Player p0 = new Player(0, pd);
        int[] am = new int[] {1, 1, 1};
        int[] am2 = new int[] {0, 0, 0};
        p0.updateAmmo(am);

        assertArrayEquals(am2, p0.getAmmo(), "Ammo aren't correct");
    }

    @Test
    public void drawPoweUpTest(){
        Board b = new Board(1);
        PowerupDeck pd = new PowerupDeck();
        Player p0 = new Player(0, pd);
        PowerupCard[] settings = new PowerupCard[]{null, null, null};
        p0.setPowerup(settings);
        p0.drawPowerup();

        assertNotNull(p0.getPowerup()[0]);
        assertNull(p0.getPowerup()[1]);
        assertNull(p0.getPowerup()[2]);

        p0.drawPowerup();
        p0.drawPowerup();
        p0.drawPowerup();       //to control that no errors are given

        assertNotNull(p0.getPowerup()[0]);
        assertNotNull(p0.getPowerup()[1]);
        assertNotNull(p0.getPowerup()[2]);
    }

    @Test
    public void usePowerupTest(){
        Board b = new Board(1);
        PowerupDeck pd = new PowerupDeck();
        Player p0 = new Player(0, pd);
        PowerupCard[] pu = {new PUTeleporter('r')};
        Position[] pos = {b.getBoard()[0][3]};
        p0.setFirstPosition(b.getBoard()[0][0]);
        p0.setPowerup(pu);
        p0.usePowerup(pu[0], null, pos, ' ');
        pos = new Position[]{b.getBoard()[2][3]};
        p0.usePowerup(pu[0], null, pos, ' ');
        p0.usePowerup(pu[0], null, pos, ' ');

        assertEquals(b.getBoard()[2][3], p0.getPosition(), "Position isn't correct");
    }

    @Test
    public void equalTest(){
        PowerupDeck pd = new PowerupDeck();
        Player p = new Player(0, pd);
        Player p0 = new Player(0, pd);
        Player p1 = new Player(1, pd);

        assertTrue(p.equals(p0), "Equals doesn't work");
        assertFalse(p.equals(p1), "Equals doesn't work");
    }

    @Test
    public void choseFirstGamePositionTest(){
        PowerupDeck pd = new PowerupDeck();
        WeaponDeck wd = new WeaponDeck();
        AmmoDeck ad = new AmmoDeck();
        Player p = new Player(0, pd);
        PowerupCard spawn = p.getPowerup()[0];
        char color = spawn.getColour();
        Position first = new Position(0, 0, color, false, true, ad, wd);
        p.choseFirstGamePosition(spawn, first);

        assertEquals(first, p.getPosition(), "Position isn't correct");
    }
}