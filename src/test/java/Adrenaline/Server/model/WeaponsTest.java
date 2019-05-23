package Adrenaline.Server.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeaponsTest {  //TODO: testare isPayd nelle due versioni in modo approfondito
    @Test
    public void CyberBladeTest(){
        Board b = new Board(1);
        PowerupDeck pd = new PowerupDeck();
        Player p0 = new Player(0, pd);
        p0.setAction(2);
        Player p1 = new Player(0, pd);
        Position pos = b.getBoard()[0][2];
        WeaponCard weapon = new WCCyberblade();
        pos.chooseArm(0);
        pos.chooseArm(1);
        pos.chooseArm(2);
        pos.giveWeapon(weapon);
        p0.setFirstPosition(pos);
        p1.setFirstPosition(pos);
        char[] ammoSel = new char[] {'r'};
        Player[] players = {p1};
        p0.grabWeaponCard(weapon,ammoSel);
        int[]mode2 = new int[] {0};

        //mode2 = {0}
        p0.shot(weapon, players, -1, mode2, null, null);

        assertEquals(9, p1.getLife(), "Shot goes wrong");

        //mode2 = {0,1} and mode2 = {1,0}
        p0.setAmmo('r', 1);
        ammoSel = new char[] {'r', 'y'};
        p0.reload(weapon, ammoSel);
        p0.setAction(2);
        mode2 = new int[] {0,1};
        p0.shot(weapon, players, -1, mode2, null, null);

        assertEquals(9, p1.getLife(), "Shot goes right");
        assertEquals(2, p0.getAction(), "Actions are correct");

        mode2 = new int[]{0,1};
        Position[] posArray = new Position[]{b.getBoard()[1][2]};
        p0.shot(weapon, players, -1, mode2, posArray, null);

        assertEquals(7, p1.getLife(), "Shot goes wrong");
        assertEquals(1, p0.getAction(), "Actions are correct");

        p0.setAmmo('r', 1);
        p0.setAmmo('y', 1);
        ammoSel = new char[] {'r', 'y'};
        p0.reload(weapon, ammoSel);
        p0.setAction(2);
        p0.setPosition(b.getBoard()[1][2]);
        posArray = new Position[]{b.getBoard()[0][2]};
        mode2 = new int[] {1,0};
        p0.shot(weapon, players, -1, mode2, posArray, null);

        assertEquals(5, p1.getLife(), "Shot goes wrong");
        assertEquals(1, p0.getAction(), "Actions are correct");

        //mode2 = {0,2}
        Player p2 = new Player(2, pd);
        p2.setFirstPosition(b.getBoard()[0][2]);
        p0.setPosition(b.getBoard()[0][2]);
        p1.setPosition(b.getBoard()[0][2]);
        p0.setAmmo('r', 1);
        p0.setAmmo('y', 2);
        ammoSel = new char[] {'r', 'y'};
        p0.reload(weapon, ammoSel);
        p0.setAction(2);
        mode2 = new int[] {0,2};
        players = new Player[] {p1, p2};
        p0.shot(weapon, players, -1, mode2, null, null);

        assertEquals(3, p1.getLife(), "Shot goes wrong");
        assertEquals(9, p2.getLife(), "Shot goes wrong");

        //mode2 = {0, 1, 2}, {1, 0, 2}, {1, 2, 0}
        p0.setPosition(b.getBoard()[0][2]);
        p1.setPosition(b.getBoard()[0][2]);
        p2.setPosition(b.getBoard()[1][2]);
        p0.setAmmo('r', 1);
        p0.setAmmo('y', 2);
        ammoSel = new char[] {'r', 'y'};
        p0.reload(weapon, ammoSel);
        p0.setAction(2);
        mode2 = new int[] {0,1,2};
        posArray = new Position[]{b.getBoard()[1][2]};
        p0.shot(weapon, players, -1, mode2, posArray, null);

        assertEquals(1, p1.getLife(), "Shot goes wrong");
        assertEquals(7, p2.getLife(), "Shot goes wrong");

        p1 = new Player(1, pd);
        p0.setPosition(b.getBoard()[1][2]);
        p1.setFirstPosition(b.getBoard()[0][2]);
        p2.setPosition(b.getBoard()[0][2]);
        p0.setAmmo('r', 1);
        p0.setAmmo('y', 2);
        ammoSel = new char[] {'r', 'y'};
        p0.reload(weapon, ammoSel);
        p0.setAction(2);
        mode2 = new int[] {1, 0, 2};
        posArray = new Position[]{b.getBoard()[0][2]};
        players = new Player[] {p1, p2};
        p0.shot(weapon, players, -1, mode2, posArray, null);

        assertEquals(9, p1.getLife(), "Shot goes wrong");
        assertEquals(5, p2.getLife(), "Shot goes wrong");

        p0.setFirstPosition(b.getBoard()[0][2]);
        p1.setFirstPosition(b.getBoard()[0][2]);
        p2.setFirstPosition(b.getBoard()[0][2]);
        p0.setAmmo('r', 1);
        p0.setAmmo('y', 1);
        ammoSel = new char[] {'r', 'y'};
        p0.reload(weapon, ammoSel);
        p0.setAction(2);
        mode2 = new int[] {0, 2, 1};
        posArray = new Position[]{b.getBoard()[1][2]};
        players = new Player[] {p1, p2};
        PowerupCard[] power = {new PowerupCard("Power", 'y')};
        p0.setPowerup(power);

        assertNotNull(power[0]);
        assertEquals('y', power[0].getColour());

        p0.shot(weapon, players, -1, mode2, posArray, power);

        assertEquals(7, p1.getLife(), "Shot goes wrong");
        assertEquals(3, p2.getLife(), "Shot goes wrong");
    }

    @Test
    public void ElectroScytheTest(){
        Board b = new Board(2);
        PowerupDeck pd = new PowerupDeck();
        Player p0 = new Player(0, pd);
        p0.setAction(2);
        Player p1 = new Player(1, pd);
        p1.setAction(2);
        Player p2 = new Player(2, pd);
        p2.setAction(2);
        Player p3 = new Player(3, pd);
        p0.setFirstPosition(b.getBoard()[0][2]);
        p1.setFirstPosition(b.getBoard()[1][2]);
        p2.setFirstPosition(b.getBoard()[1][2]);
        p3.setFirstPosition(b.getBoard()[1][2]);
        Position pos = b.getBoard()[0][2];
        WeaponCard weapon = new WCElectroscythe();
        pos.chooseArm(0);
        pos.chooseArm(1);
        pos.chooseArm(2);
        pos.giveWeapon(weapon);
        char[] ammoSel = new char[] {};
        Player[] players = {};
        PowerupCard[] payment = new PowerupCard[]{new PowerupCard("Power", 'b')};
        p0.setPowerup(payment);
        p0.grabWeaponCard(weapon,ammoSel);
        Position[] movePosition = new Position[]{b.getBoard()[0][2]};
        p1.move(movePosition);
        p2.move(movePosition);

        //mode1 = 0
        p0.shot(weapon, players, 0, null, null, payment);

        assertEquals(10, p1.getLife(), "p1 life isn't correct");
        assertEquals(10, p2.getLife(), "p2 life isn't correct");
        assertEquals(11, p3.getLife(), "p3 life isn't correct");

        //Mode1 = 1
        p0.setAmmo('r', 1);
        p0.setAmmo('b', 1);
        p0.setAction(2);
        char[]selAmmo = new char[]{'b'};
        p0.reload(weapon, selAmmo);
        p0.shot(weapon, players, 1, null, null, payment);

        assertEquals(8, p1.getLife(), "p1 life isn't correct");
        assertEquals(8, p2.getLife(), "p2 life isn't correct");
        assertEquals(11, p3.getLife(), "p3 life isn't correct");
        assertNull(p0.getPowerup()[0], "p0 power up cards are correct");
    }

    @Test
    public void FlameThrowerTest(){
        Board b = new Board(4);
        PowerupDeck pd = new PowerupDeck();
        Player p0 = new Player(0, pd);
        p0.setAction(2);
        Player p1 = new Player(1, pd);
        Player p2 = new Player(2, pd);
        Player p3 = new Player(3, pd);
        p0.setFirstPosition(b.getBoard()[0][2]);
        p1.setFirstPosition(b.getBoard()[0][1]);
        p2.setFirstPosition(b.getBoard()[0][0]);
        p3.setFirstPosition(b.getBoard()[1][2]);
        Position pos = b.getBoard()[0][2];
        WeaponCard weapon = new WCFlamethrower();
        pos.chooseArm(0);
        pos.chooseArm(1);
        pos.chooseArm(2);
        pos.giveWeapon(weapon);
        char[] ammoSel = new char[] {};
        Position[] squares = new Position[]{b.getBoard()[0][1], b.getBoard()[0][0]};
        Player[] players = {p1, p2};
        PowerupCard[] payment = new PowerupCard[]{new PowerupCard("Power", 'b')};
        p0.setPowerup(payment);
        p0.grabWeaponCard(weapon, ammoSel);

        //mode1 = 0 with two squares
        p0.shot(weapon, players, 0, null, squares, null);

        assertEquals(10, p1.getLife(), "p1 life isn't correct");
        assertEquals(10, p2.getLife(), "p2 life isn't correct");
        assertEquals(11, p3.getLife(), "p3 life isn't correct");

        //To control that a player on p0 position can't be attacked
        p2.setPosition(p0.getPosition());
        p0.setAmmo('r', 1);
        p0.setAction(2);
        char[]selAmmo = new char[]{'r'};
        squares = new Position[]{b.getBoard()[0][1], b.getBoard()[0][2]};
        p0.reload(weapon, selAmmo);
        p0.shot(weapon, players, 0, null, squares, null);

        assertEquals(10, p1.getLife(), "p1 life isn't correct");
        assertEquals(10, p2.getLife(), "p2 life isn't correct");
        assertEquals(11, p3.getLife(), "p3 life isn't correct");

        //mode1 = 0 with one square
        p2.setPosition(b.getBoard()[0][0]);
        p0.setAmmo('r', 1);
        p0.setAction(2);
        selAmmo = new char[]{'r'};
        squares = new Position[]{b.getBoard()[0][1]};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        p0.shot(weapon, players, 0, null, squares, null);

        assertEquals(9, p1.getLife(), "p1 life isn't correct");
        assertEquals(10, p2.getLife(), "p2 life isn't correct");
        assertEquals(11, p3.getLife(), "p3 life isn't correct");

        //mode1 = 1, with 2 squares
        p1.setPosition(b.getBoard()[1][2]);
        p2.setPosition(b.getBoard()[2][2]);
        p0.setAmmo('r', 1);
        p0.setAmmo('y', 2);
        p0.setAction(2);
        selAmmo = new char[]{'r'};
        squares = new Position[]{b.getBoard()[1][2], b.getBoard()[2][2]};
        p0.reload(weapon, selAmmo);
        players = new Player[]{};
        p0.shot(weapon, players, 1, null, squares, null);

        assertEquals(7, p1.getLife(), "p1 life isn't correct");
        assertEquals(9, p2.getLife(), "p2 life isn't correct");
        assertEquals(9, p3.getLife(), "p3 life isn't correct");

        //mode1 = 1, with one squares
        p1.setPosition(b.getBoard()[1][2]);
        p2.setPosition(b.getBoard()[2][2]);
        p0.setAmmo('r', 1);
        p0.setAmmo('y', 2);
        p0.setAction(2);
        selAmmo = new char[]{'r'};
        squares = new Position[]{b.getBoard()[1][2]};
        p0.reload(weapon, selAmmo);
        players = new Player[]{};
        p0.shot(weapon, players, 1, null, squares, null);

        assertEquals(5, p1.getLife(), "p1 life isn't correct");
        assertEquals(9, p2.getLife(), "p2 life isn't correct");
        assertEquals(7, p3.getLife(), "p3 life isn't correct");
    }

    @Test
    public void FurnaceTest(){
        Board b = new Board(2);
        PowerupDeck pd = new PowerupDeck();
        Player p0 = new Player(0, pd);
        p0.setAction(2);
        Player p1 = new Player(1, pd);
        p1.setAction(2);
        Player p2 = new Player(2, pd);
        p2.setAction(2);
        Player p3 = new Player(3, pd);
        p0.setFirstPosition(b.getBoard()[0][2]);
        p1.setFirstPosition(b.getBoard()[1][2]);
        p2.setFirstPosition(b.getBoard()[1][3]);
        p3.setFirstPosition(b.getBoard()[2][2]);
        Position pos = b.getBoard()[0][2];
        WeaponCard weapon = new WCFurnace();
        pos.chooseArm(0);
        pos.chooseArm(1);
        pos.chooseArm(2);
        pos.giveWeapon(weapon);
        char[] ammoSel = new char[] {};
        Position[] roomSquares = new Position[]{b.getBoard()[1][2], b.getBoard()[1][3], b.getBoard()[2][2], b.getBoard()[2][3]};
        Player[] players = {};
        PowerupCard[] payment = new PowerupCard[]{new PowerupCard("Power", 'b')};
        p0.setPowerup(payment);
        p0.grabWeaponCard(weapon, ammoSel, payment);

        //mode1 = 0
        p0.shot(weapon, players, 0, null, roomSquares, null);

        assertEquals(10, p1.getLife(), "p1 life isn't correct");
        assertEquals(10, p2.getLife(), "p2 life isn't correct");
        assertEquals(10, p3.getLife(), "p3 life isn't correct");
        assertNull(p0.getPowerup()[0], "p0 power up cards are correct");

        //Mode1 = 1
        p0.setAmmo('r', 1);
        p0.setAmmo('b', 1);
        p0.setAction(2);
        char[]selAmmo = new char[]{'b', 'r'};
        p0.reload(weapon, selAmmo);
        Position[] movePosition = new Position[]{b.getBoard()[1][2]};
        p2.move(movePosition);
        roomSquares = new Position[]{b.getBoard()[1][2]};
        p0.shot(weapon, players, 1, null, roomSquares, null);

        assertEquals(9, p1.getLife(), "p1 life isn't correct");
        assertEquals(9, p2.getLife(), "p2 life isn't correct");
        assertEquals(10, p3.getLife(), "p3 life isn't correct");
    }

    @Test
    public void GrenadeLauncherTest(){
        Board b = new Board(2);
        PowerupDeck pd = new PowerupDeck();
        Player p0 = new Player(0, pd);
        p0.setAction(2);
        Player p1 = new Player(1, pd);
        p1.setAction(2);
        Player p2 = new Player(2, pd);
        p2.setAction(2);
        Player p3 = new Player(3, pd);
        p0.setFirstPosition(b.getBoard()[0][2]);
        p1.setFirstPosition(b.getBoard()[2][3]);
        p2.setFirstPosition(b.getBoard()[1][3]);
        p3.setFirstPosition(b.getBoard()[1][3]);
        Position pos = b.getBoard()[0][2];
        WeaponCard weapon = new WCGrenadeLauncher();
        pos.chooseArm(0);
        pos.chooseArm(1);
        pos.chooseArm(2);
        pos.giveWeapon(weapon);
        char[] ammoSel = new char[] {};
        Position[] square = new Position[]{b.getBoard()[2][2]};
        Player[] players = {p1};
        PowerupCard[] payment = new PowerupCard[]{new PowerupCard("Power", 'r')};
        p0.setPowerup(payment);
        p0.grabWeaponCard(weapon, ammoSel);
        int[] mode2 = new int[]{0};

        //mode2 = {0}, no movement for p1
        p0.shot(weapon, players, -1, mode2, null, null);

        assertEquals(10, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[2][3], p1.getPosition(), "p1 position isn't correct");

        //mode2 = {0},  p1 is moved
        p0.setAmmo('r', 1);
        p0.setAction(2);
        char[]selAmmo = new char[]{'r'};
        p0.reload(weapon, selAmmo);
        p0.shot(weapon, players, -1, mode2, square, null);

        assertEquals(9, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[2][2], p1.getPosition(), "p1 position isn't correct");

        //mode2 = {0,1},  p1 is not moved
        p0.setAmmo('r', 1);
        p0.setAction(2);
        selAmmo = new char[]{'r'};
        p0.reload(weapon, selAmmo);
        mode2 = new int[]{0, 1};
        square = new Position[]{null, b.getBoard()[1][3]};
        p0.shot(weapon, players, -1, mode2, square, payment);

        assertEquals(8, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[2][2], p1.getPosition(), "p1 position isn't correct");
        assertEquals(10, p2.getLife(), "p2 life isn't correct");
        assertEquals(10, p3.getLife(), "p3 life isn't correct");

        //mode2 = {0,1},  p1 is moved
        p0.setAmmo('r', 2);
        p0.setAction(2);
        selAmmo = new char[]{'r'};
        p0.reload(weapon, selAmmo);
        mode2 = new int[]{0, 1};
        square = new Position[]{b.getBoard()[2][3], b.getBoard()[1][3]};
        p0.shot(weapon, players, -1, mode2, square, null);

        assertEquals(7, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[2][3], p1.getPosition(), "p1 position isn't correct");
        assertEquals(9, p2.getLife(), "p2 life isn't correct");
        assertEquals(9, p3.getLife(), "p3 life isn't correct");

        //mode2 = {1, 0},  p1 is moved
        p0.setAmmo('r', 2);
        p0.setAction(2);
        selAmmo = new char[]{'r'};
        p0.reload(weapon, selAmmo);
        mode2 = new int[]{1, 0};
        square = new Position[]{b.getBoard()[1][3], b.getBoard()[1][3]};
        p0.shot(weapon, players, -1, mode2, square, null);

        assertEquals(6, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[1][3], p1.getPosition(), "p1 position isn't correct");
        assertEquals(8, p2.getLife(), "p2 life isn't correct");
        assertEquals(8, p3.getLife(), "p3 life isn't correct");

        //mode2 = {1, 0},  p1 is not moved
        p0.setAmmo('r', 2);
        p0.setAction(2);
        selAmmo = new char[]{'r'};
        p0.reload(weapon, selAmmo);
        mode2 = new int[]{1, 0};
        square = new Position[]{null, b.getBoard()[1][3]};
        p0.shot(weapon, players, -1, mode2, square, null);

        assertEquals(4, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[1][3], p1.getPosition(), "p1 position isn't correct");
        assertEquals(7, p2.getLife(), "p2 life isn't correct");
        assertEquals(7, p3.getLife(), "p3 life isn't correct");
    }

    @Test
    public void HeatseekerTest(){
        Board b = new Board(3);
        PowerupDeck pd = new PowerupDeck();
        Player p0 = new Player(0, pd);
        p0.setAction(2);
        Player p1 = new Player(1, pd);
        p1.setAction(2);
        p0.setFirstPosition(b.getBoard()[0][2]);
        p1.setFirstPosition(b.getBoard()[2][0]);
        Position pos = b.getBoard()[0][2];
        WeaponCard weapon = new WCHeatseeker();
        pos.chooseArm(0);
        pos.chooseArm(1);
        pos.chooseArm(2);
        pos.giveWeapon(weapon);
        char[] ammoSel = new char[] {'y'};
        Player[] players = {p1};
        PowerupCard[] payment = new PowerupCard[]{new PowerupCard("Power", 'r')};
        p0.setPowerup(payment);
        p0.grabWeaponCard(weapon, ammoSel, payment);

        p0.shot(weapon, players, -1, null, null, null);

        assertEquals(8, p1.getLife(), "p1 life isn't correct");
    }

    @Test
    public void HellionTest(){
        Board b = new Board(2);
        PowerupDeck pd = new PowerupDeck();
        Player p0 = new Player(0, pd);
        p0.setAction(2);
        Player p1 = new Player(1, pd);
        Player p2 = new Player(2, pd);
        Player p3 = new Player(3, pd);
        p0.setFirstPosition(b.getBoard()[0][2]);
        p1.setFirstPosition(b.getBoard()[0][3]);
        p2.setFirstPosition(b.getBoard()[0][3]);
        p3.setFirstPosition(b.getBoard()[1][3]);
        Position pos = b.getBoard()[0][2];
        WeaponCard weapon = new WCHellion();
        pos.chooseArm(0);
        pos.chooseArm(1);
        pos.chooseArm(2);
        pos.giveWeapon(weapon);
        char[] ammoSel = new char[] {'y'};
        Player[] players = {p1};
        PowerupCard[] payment = new PowerupCard[]{new PowerupCard("Power", 'r')};
        p0.setPowerup(payment);
        p0.grabWeaponCard(weapon,ammoSel);

        //mode1 = 0
        p0.shot(weapon, players, 0, null, null, payment);

        assertEquals(10, p1.getLife(), "p1 life isn't correct");
        assertEquals(1, p1.getMarksReceived()[0], "p1 marks aren't correct");
        assertEquals(1, p2.getMarksReceived()[0], "p2 marks aren't correct");
        assertEquals(0, p3.getMarksReceived()[0], "p3 marks aren't correct");
        int[] marks = new int[]{0, 1, 1, 0, 0};
        assertArrayEquals(marks, p0.getMarksGiven(), "p0 marks given are not correct");

        //Mode1 = 1
        p0.setAmmo('r', 1);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        char[]selAmmo = new char[]{'r', 'y'};
        p0.reload(weapon, selAmmo);
        p0.shot(weapon, players, 1, null, null, payment);

        assertEquals(8, p1.getLife(), "p1 life isn't correct");
        assertEquals(2, p1.getMarksReceived()[0], "p1 marks aren't correct");
        assertEquals(3, p2.getMarksReceived()[0], "p2 marks aren't correct");
        assertEquals(0, p3.getMarksReceived()[0], "p3 marks aren't correct");
        marks = new int[]{0, 2, 3, 0, 0};
        assertArrayEquals(marks, p0.getMarksGiven(), "p0 marks given are not correct");
        assertNull(p0.getPowerup()[0], "p0 power up cards are correct");
    }

    @Test
    public void LockRifleTest(){
        Board b = new Board(2);
        PowerupDeck pd = new PowerupDeck();
        Player p0 = new Player(0, pd);
        p0.setAction(2);
        Player p1 = new Player(1, pd);
        Player p2 = new Player(2, pd);
        p0.setFirstPosition(b.getBoard()[0][2]);
        p1.setFirstPosition(b.getBoard()[2][3]);
        p2.setFirstPosition(b.getBoard()[1][3]);
        Position pos = b.getBoard()[0][2];
        WeaponCard weapon = new WCLockRifle();
        pos.chooseArm(0);
        pos.chooseArm(1);
        pos.chooseArm(2);
        pos.giveWeapon(weapon);
        char[] ammoSel = new char[] {'b'};
        Player[] players = {p1};
        PowerupCard[] payment = new PowerupCard[]{new PowerupCard("Power", 'r')};
        p0.setPowerup(payment);
        p0.grabWeaponCard(weapon, ammoSel);
        int[] mode2 = new int[]{0};

        //mode2 = {0}
        p0.shot(weapon, players, -1, mode2, null, payment);

        assertEquals(9, p1.getLife(), "p1 life isn't correct");
        assertEquals(1, p0.getMarksGiven()[1], "p0's given marks aren't correct");

        //mode2 = {0, 1}
        p0.setAmmo('b', 2);
        p0.setAction(2);
        char[]selAmmo = new char[]{'b', 'b'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1, p2};
        mode2 = new int[]{0, 1};
        p0.shot(weapon, players, -1, mode2, null, payment);

        assertEquals(6, p1.getLife(), "p1 life isn't correct");
        assertEquals(1, p0.getMarksGiven()[1], "p0's given marks aren't correct");
        assertEquals(1, p0.getMarksGiven()[2], "p0's given marks aren't correct");
    }

    @Test
    public void MachineGunTest(){
        Board b = new Board(4);
        PowerupDeck pd = new PowerupDeck();
        Player p0 = new Player(0, pd);
        p0.setAction(2);
        Player p1 = new Player(1, pd);
        Player p2 = new Player(2, pd);
        Player p3 = new Player(3, pd);
        p0.setFirstPosition(b.getBoard()[0][2]);
        p1.setFirstPosition(b.getBoard()[2][3]);
        p2.setFirstPosition(b.getBoard()[1][3]);
        p3.setFirstPosition(b.getBoard()[1][3]);
        Position pos = b.getBoard()[0][2];
        WeaponCard weapon = new WCMachineGun();
        pos.chooseArm(0);
        pos.chooseArm(1);
        pos.chooseArm(2);
        pos.giveWeapon(weapon);
        char[] ammoSel = new char[] {'r'};
        Player[] players = {p1};
        PowerupCard[] payment = new PowerupCard[]{new PowerupCard("Power", 'y')};
        p0.setPowerup(payment);
        p0.grabWeaponCard(weapon, ammoSel);
        int[] mode2 = new int[]{0};

        //mode2 = {0}, with only one target
        p0.shot(weapon, players, -1, mode2, null, null);

        assertEquals(10, p1.getLife(), "p1 life isn't correct");
        assertEquals(11, p2.getLife(), "p1 life isn't correct");
        assertEquals(11, p3.getLife(), "p1 life isn't correct");

        //mode2 = {0}, with two target
        p0.setAmmo('b', 1);
        p0.setAmmo('r', 1);
        p0.setAction(2);
        char[]selAmmo = new char[]{'b', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1, p2};
        mode2 = new int[]{0};
        p0.shot(weapon, players, -1, mode2, null, null);

        assertEquals(9, p1.getLife(), "p1 life isn't correct");
        assertEquals(10, p2.getLife(), "p1 life isn't correct");
        assertEquals(11, p3.getLife(), "p1 life isn't correct");

        //mode2 = {0, 1}, with one target
        p0.setAmmo('b', 1);
        p0.setAmmo('r', 1);
        p0.setAction(2);
        selAmmo = new char[]{'b', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{0, 1};
        p0.shot(weapon, players, -1, mode2, null, null);

        assertEquals(7, p1.getLife(), "p1 life isn't correct");
        assertEquals(10, p2.getLife(), "p1 life isn't correct");
        assertEquals(11, p3.getLife(), "p1 life isn't correct");

        //mode2 = {0, 1}, with two target
        p0.setAmmo('b', 1);
        p0.setAmmo('r', 1);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        selAmmo = new char[]{'b', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1, p2};
        mode2 = new int[]{0, 1};
        p0.shot(weapon, players, -1, mode2, null, null);

        assertEquals(5, p1.getLife(), "p1 life isn't correct");
        assertEquals(9, p2.getLife(), "p1 life isn't correct");
        assertEquals(11, p3.getLife(), "p1 life isn't correct");

        //mode2 = {0, 2}, with one target
        p0.setAmmo('b', 2);
        p0.setAmmo('r', 1);
        p0.setAction(2);
        selAmmo = new char[]{'b', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{0, 2};
        p0.shot(weapon, players, -1, mode2, null, null);

        assertEquals(3, p1.getLife(), "p1 life isn't correct");
        assertEquals(9, p2.getLife(), "p2 life isn't correct");
        assertEquals(11, p3.getLife(), "p3 life isn't correct");

        //mode2 = {0, 2}, with two target
        p0.setAmmo('b', 2);
        p0.setAmmo('r', 1);
        p0.setAction(2);
        selAmmo = new char[]{'b', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1, p2};
        mode2 = new int[]{0, 2};
        p0.shot(weapon, players, -1, mode2, null, null);

        assertEquals(2, p1.getLife(), "p1 life isn't correct");
        assertEquals(7, p2.getLife(), "p1 life isn't correct");
        assertEquals(11, p3.getLife(), "p1 life isn't correct");

        //mode2 = {0, 2}, with three target
        p0.setAmmo('b', 2);
        p0.setAmmo('r', 1);
        p0.setAction(2);
        selAmmo = new char[]{'b', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1, p2, p3};
        mode2 = new int[]{0, 2};
        p0.shot(weapon, players, -1, mode2, null, null);

        assertEquals(1, p1.getLife(), "p1 life isn't correct");
        assertEquals(5, p2.getLife(), "p1 life isn't correct");
        assertEquals(10, p3.getLife(), "p1 life isn't correct");

        //mode2 = {0, 1, 2}, with one target
        p0.setAmmo('b', 2);
        p0.setAmmo('r', 1);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        p0.setLife(11);
        p1.setLife(11);
        p2.setLife(11);
        p3.setLife(11);
        selAmmo = new char[]{'b', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{0, 2, 1};
        p0.shot(weapon, players, -1, mode2, null, null);

        assertEquals(8, p1.getLife(), "p1 life isn't correct");
        assertEquals(11, p2.getLife(), "p2 life isn't correct");
        assertEquals(11, p3.getLife(), "p3 life isn't correct");

        //mode2 = {0, 1, 2}, with two target
        p0.setAmmo('b', 2);
        p0.setAmmo('r', 1);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        selAmmo = new char[]{'b', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1, p2};
        mode2 = new int[]{0, 1, 2};
        p0.shot(weapon, players, -1, mode2, null, null);

        assertEquals(6, p1.getLife(), "p1 life isn't correct");
        assertEquals(9, p2.getLife(), "p1 life isn't correct");
        assertEquals(11, p3.getLife(), "p1 life isn't correct");

        //mode2 = {0, 1, 2}, with three target
        p0.setAmmo('b', 2);
        p0.setAmmo('r', 1);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        selAmmo = new char[]{'b', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1, p2, p3};
        mode2 = new int[]{1, 0, 2};
        p0.shot(weapon, players, -1, mode2, null, null);

        assertEquals(4, p1.getLife(), "p1 life isn't correct");
        assertEquals(7, p2.getLife(), "p1 life isn't correct");
        assertEquals(10, p3.getLife(), "p1 life isn't correct");
    }

    @Test
    public void RocketLauncherTest(){
        Board b = new Board(4);
        PowerupDeck pd = new PowerupDeck();
        Player p0 = new Player(0, pd);
        p0.setAction(2);
        Player p1 = new Player(1, pd);
        Player p2 = new Player(2, pd);
        Player p3 = new Player(3, pd);
        p0.setFirstPosition(b.getBoard()[0][2]);
        p1.setFirstPosition(b.getBoard()[0][2]);
        p2.setFirstPosition(b.getBoard()[1][3]);
        p3.setFirstPosition(b.getBoard()[2][2]);
        Position pos = b.getBoard()[0][2];
        WeaponCard weapon = new WCRocketLauncher();
        pos.chooseArm(0);
        pos.chooseArm(1);
        pos.chooseArm(2);
        pos.giveWeapon(weapon);
        char[] ammoSel = new char[] {'r'};
        Player[] players = {p1};
        Position[] move = new Position[]{b.getBoard()[1][2], b.getBoard()[0][1]};
        PowerupCard[] payment = new PowerupCard[]{new PowerupCard("Power", 'b')};
        p0.setPowerup(payment);
        p0.grabWeaponCard(weapon, ammoSel);
        int[] mode2 = new int[]{1, 0};

        //mode2 = {1, 0}, p0 one movement, p1 is moved
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(9, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[1][2], p1.getPosition(), "p1 position isn't correct");
        assertEquals(b.getBoard()[0][1], p0.getPosition(), "p0 position isn't correct");

        //mode2 = {0}, p1 is not moved
        p0.setAmmo('r', 2);
        p0.setAction(2);
        p1.setPosition(b.getBoard()[0][2]);
        char[] selAmmo = new char[]{'r', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{0};
        p0.shot(weapon, players, -1, mode2, null, null);

        assertEquals(7, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[0][2], p1.getPosition(), "p1 position isn't correct");

        //mode2 = {0}, p1 is moved
        p0.setAmmo('r', 2);
        p0.setAction(2);
        p1.setPosition(b.getBoard()[0][2]);
        selAmmo = new char[]{'r', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{0};
        move = new Position[]{b.getBoard()[1][2]};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(5, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[1][2], p1.getPosition(), "p1 position isn't correct");

        //mode2 = {0, 1}, p0 one movement, p1 is not moved
        p0.setAmmo('r', 2);
        p0.setAmmo('b', 1);
        p0.setAction(2);
        p1.setPosition(b.getBoard()[0][2]);
        selAmmo = new char[]{'r', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{0, 1};
        move = new Position[]{null, b.getBoard()[0][2]};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(3, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[0][2], p0.getPosition(), "p0 position isn't correct");
        assertEquals(b.getBoard()[0][2], p1.getPosition(), "p1 position isn't correct");

        //mode2 = {0, 1}, p0 2 movements, p1 is moved
        p0.setAmmo('r', 2);
        p0.setAmmo('b', 1);
        p0.setAction(2);
        p1.setPosition(b.getBoard()[0][1]);
        selAmmo = new char[]{'r', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{0, 1};
        move = new Position[]{b.getBoard()[0][0], b.getBoard()[0][3], b.getBoard()[1][3]};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(1, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[1][3], p0.getPosition(), "p0 position isn't correct");
        assertEquals(b.getBoard()[0][0], p1.getPosition(), "p1 position isn't correct");

        //mode2 = {1, 0}, p0 2 movements, p1 is moved
        p0.setAmmo('r', 2);
        p0.setAmmo('b', 1);
        p0.setAction(2);
        p0.setPosition(b.getBoard()[1][3]);
        p1.setPosition(b.getBoard()[0][1]);
        p1.setLife(11);
        selAmmo = new char[]{'r', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{1, 0};
        move = new Position[]{b.getBoard()[0][0], b.getBoard()[0][3], b.getBoard()[0][2]};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(9, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[0][2], p0.getPosition(), "p0 position isn't correct");
        assertEquals(b.getBoard()[0][0], p1.getPosition(), "p1 position isn't correct");

        //mode2 = {0, 2} (or {2, 0}), p1 is moved
        p0.setAmmo('r', 2);
        p0.setAmmo('b', 1);
        p0.setAction(2);
        p0.setPosition(b.getBoard()[0][2]);
        p1.setPosition(b.getBoard()[0][1]);
        p2.setPosition(b.getBoard()[0][1]);
        p3.setPosition(b.getBoard()[0][0]);
        selAmmo = new char[]{'r', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{0, 2};
        move = new Position[]{b.getBoard()[0][0], b.getBoard()[0][3], b.getBoard()[0][2]};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(6, p1.getLife(), "p1 life isn't correct");
        assertEquals(10, p2.getLife(), "p2 life isn't correct");
        assertEquals(11, p3.getLife(), "p3 life isn't correct");
        assertEquals(b.getBoard()[0][0], p1.getPosition(), "p1 position isn't correct");

        //mode2 = {0, 2} (or {2, 0}), p1 is not moved
        p0.setAmmo('r', 2);
        p0.setAmmo('b', 1);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        p0.setPosition(b.getBoard()[0][2]);
        p1.setPosition(b.getBoard()[0][1]);
        p2.setPosition(b.getBoard()[0][1]);
        p3.setPosition(b.getBoard()[0][1]);
        selAmmo = new char[]{'r', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{0, 2};
        move = new Position[]{null, b.getBoard()[0][3], b.getBoard()[0][2]};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(3, p1.getLife(), "p1 life isn't correct");
        assertEquals(9, p2.getLife(), "p2 life isn't correct");
        assertEquals(10, p3.getLife(), "p3 life isn't correct");
        assertEquals(b.getBoard()[0][1], p1.getPosition(), "p1 position isn't correct");

        //mode2 = {0, 1, 2} (or {2, 0, 1} or {0, 2, 1}), p1 is not moved
        p0.setAmmo('r', 2);
        p0.setAmmo('b', 1);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        p0.setPosition(b.getBoard()[0][2]);
        p1.setPosition(b.getBoard()[0][1]);
        p2.setPosition(b.getBoard()[0][1]);
        p3.setPosition(b.getBoard()[0][1]);
        selAmmo = new char[]{'r', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{0, 1, 2};
        move = new Position[]{null, b.getBoard()[1][2], b.getBoard()[1][3]};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(0, p1.getLife(), "p1 life isn't correct");
        assertEquals(8, p2.getLife(), "p2 life isn't correct");
        assertEquals(9, p3.getLife(), "p3 life isn't correct");
        assertEquals(b.getBoard()[1][3], p0.getPosition(), "p0 position isn't correct");
        assertEquals(b.getBoard()[0][1], p1.getPosition(), "p1 position isn't correct");

        //mode2 = {0, 1, 2} (or {2, 0, 1} or {0, 2, 1}), p1 is moved, p0 2 movements
        p0.setAmmo('r', 2);
        p0.setAmmo('b', 1);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        p1.setLife(11);
        p0.setPosition(b.getBoard()[0][2]);
        p1.setPosition(b.getBoard()[0][1]);
        p2.setPosition(b.getBoard()[0][1]);
        p3.setPosition(b.getBoard()[0][1]);
        selAmmo = new char[]{'r', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{0, 1, 2};
        move = new Position[]{b.getBoard()[0][0], b.getBoard()[1][2], b.getBoard()[1][3]};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(8, p1.getLife(), "p1 life isn't correct");
        assertEquals(7, p2.getLife(), "p2 life isn't correct");
        assertEquals(8, p3.getLife(), "p3 life isn't correct");
        assertEquals(b.getBoard()[1][3], p0.getPosition(), "p0 position isn't correct");
        assertEquals(b.getBoard()[0][0], p1.getPosition(), "p1 position isn't correct");

        //mode2 = {0, 1, 2} (or {2, 0, 1} or {0, 2, 1}), p1 is moved, p0 one movement
        p0.setAmmo('r', 2);
        p0.setAmmo('b', 1);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        p1.setLife(11);
        p2.setLife(8);
        p3.setLife(9);
        p0.setPosition(b.getBoard()[0][2]);
        p1.setPosition(b.getBoard()[0][1]);
        p2.setPosition(b.getBoard()[0][1]);
        p3.setPosition(b.getBoard()[0][1]);
        selAmmo = new char[]{'r', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{0, 1, 2};
        move = new Position[]{b.getBoard()[0][0], b.getBoard()[1][2], null};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(8, p1.getLife(), "p1 life isn't correct");
        assertEquals(7, p2.getLife(), "p2 life isn't correct");
        assertEquals(8, p3.getLife(), "p3 life isn't correct");
        assertEquals(b.getBoard()[1][2], p0.getPosition(), "p0 position isn't correct");
        assertEquals(b.getBoard()[0][0], p1.getPosition(), "p1 position isn't correct");

        //mode2 = {1, 0, 2} (or {2, 1, 0} or {1, 2, 0}), p1 is moved, p0 2 movements
        p0.setAmmo('r', 2);
        p0.setAmmo('b', 1);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        p0.setPosition(b.getBoard()[2][3]);
        p1.setPosition(b.getBoard()[0][1]);
        p2.setPosition(b.getBoard()[0][1]);
        p3.setPosition(b.getBoard()[0][1]);
        selAmmo = new char[]{'r', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{1, 0, 2};
        move = new Position[]{b.getBoard()[0][0], b.getBoard()[1][3], b.getBoard()[1][2]};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(5, p1.getLife(), "p1 life isn't correct");
        assertEquals(6, p2.getLife(), "p2 life isn't correct");
        assertEquals(7, p3.getLife(), "p3 life isn't correct");
        assertEquals(b.getBoard()[1][2], p0.getPosition(), "p0 position isn't correct");
        assertEquals(b.getBoard()[0][0], p1.getPosition(), "p1 position isn't correct");

        //mode2 = {1, 0, 2} (or {2, 1, 0} or {1, 2, 0}), p1 is not moved, p0 one movements
        p0.setAmmo('r', 2);
        p0.setAmmo('b', 1);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        p0.setPosition(b.getBoard()[1][3]);
        p1.setPosition(b.getBoard()[0][1]);
        p2.setPosition(b.getBoard()[0][1]);
        p3.setPosition(b.getBoard()[0][1]);
        selAmmo = new char[]{'r', 'r'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{1, 0, 2};
        move = new Position[]{null, b.getBoard()[1][2], null};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(2, p1.getLife(), "p1 life isn't correct");
        assertEquals(5, p2.getLife(), "p2 life isn't correct");
        assertEquals(6, p3.getLife(), "p3 life isn't correct");
        assertEquals(b.getBoard()[1][2], p0.getPosition(), "p0 position isn't correct");
        assertEquals(b.getBoard()[0][1], p1.getPosition(), "p1 position isn't correct");
    }

    @Test
    public void PlasmaGunTest(){
        Board b = new Board(4);
        PowerupDeck pd = new PowerupDeck();
        Player p0 = new Player(0, pd);
        p0.setAction(2);
        Player p1 = new Player(1, pd);
        p0.setFirstPosition(b.getBoard()[0][2]);
        p1.setFirstPosition(b.getBoard()[1][2]);
        Position pos = b.getBoard()[0][2];
        WeaponCard weapon = new WCPlasmaGun();
        pos.chooseArm(0);
        pos.chooseArm(1);
        pos.chooseArm(2);
        pos.giveWeapon(weapon);
        char[] ammoSel = new char[] {'y'};
        Player[] players = {p1};
        Position[] move = new Position[]{};
        PowerupCard[] payment = new PowerupCard[]{new PowerupCard("Power", 'b')};
        p0.setPowerup(payment);
        p0.grabWeaponCard(weapon, ammoSel);
        int[] mode2 = new int[]{0};

        //mode2 = {0}
        p0.shot(weapon, players, -1, mode2, null, null);

        assertEquals(9, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[0][2], p0.getPosition(), "p0 position isn't correct");

        //mode2 = {0, 1}, p0 one movement
        p0.setAmmo('b', 1);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        char[] selAmmo = new char[]{'b', 'y'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{0, 1};
        move = new Position[]{b.getBoard()[1][2]};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(7, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[1][2], p0.getPosition(), "p0 position isn't correct");

        //mode2 = {0, 1}, p0 two movements
        p0.setAmmo('b', 1);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        selAmmo = new char[]{'b', 'y'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{0, 1};
        move = new Position[]{b.getBoard()[1][3], b.getBoard()[0][3]};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(5, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[0][3], p0.getPosition(), "p0 position isn't correct");

        //mode2 = {1, 0}, p0 one movement
        p0.setAmmo('b', 1);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        p0.setPosition(b.getBoard()[0][2]);
        selAmmo = new char[]{'b', 'y'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{1, 0};
        move = new Position[]{b.getBoard()[0][3]};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(3, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[0][3], p0.getPosition(), "p0 position isn't correct");

        //mode2 = {1, 0}, p0 two movements
        p0.setAmmo('b', 1);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        p0.setPosition(b.getBoard()[0][2]);
        selAmmo = new char[]{'b', 'y'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{1, 0};
        move = new Position[]{b.getBoard()[0][3], b.getBoard()[1][3]};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(1, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[1][3], p0.getPosition(), "p0 position isn't correct");

        //mode2 = {0, 2} (or {2, 0})
        p0.setAmmo('b', 1);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        p0.setPosition(b.getBoard()[0][2]);
        p1.setLife(11);
        selAmmo = new char[]{'b', 'y'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{0, 2};
        move = new Position[]{b.getBoard()[0][3], b.getBoard()[1][3]};
        p0.shot(weapon, players, -1, mode2, move, payment);

        assertEquals(8, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[0][2], p0.getPosition(), "p0 position isn't correct");

        //mode2 = {0, 1, 2} (or {0, 2, 1}), p0 one movement
        p0.setAmmo('b', 2);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        p0.setPosition(b.getBoard()[0][2]);
        selAmmo = new char[]{'b', 'y'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{0, 1, 2};
        move = new Position[]{b.getBoard()[0][1]};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(5, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[0][1], p0.getPosition(), "p0 position isn't correct");

        //mode2 = {0, 1, 2} (or {0, 2, 1}), p0 two movements
        p0.setAmmo('b', 2);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        p0.setPosition(b.getBoard()[0][2]);
        selAmmo = new char[]{'b', 'y'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{0, 2, 1};
        move = new Position[]{b.getBoard()[0][1], b.getBoard()[0][0]};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(2, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[0][0], p0.getPosition(), "p0 position isn't correct");

        //mode2 = {1, 0, 2}, p0 one movement
        p0.setAmmo('b', 2);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        p0.setPosition(b.getBoard()[0][2]);
        selAmmo = new char[]{'b', 'y'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{1, 0, 2};
        move = new Position[]{b.getBoard()[1][2]};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(-1, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[1][2], p0.getPosition(), "p0 position isn't correct");

        //mode2 = {1, 0, 2}, p0 two movement
        p0.setAmmo('b', 2);
        p0.setAmmo('y', 1);
        p0.setAction(2);
        p0.setPosition(b.getBoard()[0][1]);
        p1.setLife(11);
        selAmmo = new char[]{'b', 'y'};
        p0.reload(weapon, selAmmo);
        players = new Player[]{p1};
        mode2 = new int[]{1, 0, 2};
        move = new Position[]{b.getBoard()[1][1], b.getBoard()[2][1]};
        p0.shot(weapon, players, -1, mode2, move, null);

        assertEquals(8, p1.getLife(), "p1 life isn't correct");
        assertEquals(b.getBoard()[2][1], p0.getPosition(), "p0 position isn't correct");
    }
}
