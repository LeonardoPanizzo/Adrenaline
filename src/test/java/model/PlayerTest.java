package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void constructorTest(){
        Player p = new Player(3);
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
        Player p = new Player(0);
        p.endOfRound();

        assertEquals(false, p.isRound());
    }

    @Test
    public void receivedDamagesTest(){
        Player p = new Player(1);
        p.receivedDamages(3);
        int life = p.getLife();
        int[][] playersDamage = p.getPlayersDamage();

        assertEquals(10, life, "Life isn't correct");
        assertEquals(1, playersDamage[3][0], "Order of player 4 isn't correct");
        assertEquals(1, playersDamage[3][1], "Total player 4 damage isn't correct");

        p.receivedDamages(1);
        p.receivedDamages(2);
        p.receivedDamages(3);
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

        Player player = new Player(2);
        int [] marksReceived = new int[]{0, 0, 0, 2, 0};
        player.setMarksReceived(marksReceived);
        player.receivedDamages(3);
        life = player.getLife();
        playersDamage = player.getPlayersDamage();

        assertEquals(8, life, "Player's Life isn't correct");
        assertEquals(1, playersDamage[3][0], "Order of player 4 isn't correct");
        assertEquals(3, playersDamage[3][1], "Total player 4 damage isn't correct");
        assertEquals(0, marksReceived[3], "Marks are not updated");

        player.receivedDamages(1);
        player.receivedDamages(3);
        marksReceived = new int[] {0, 3, 0, 0, 0};
        player.setMarksReceived(marksReceived);
        player.receivedDamages(1);
        life = player.getLife();
        playersDamage = player.getPlayersDamage();

        assertEquals(2, life, "Player's Life isn't correct");
        assertEquals(2, playersDamage[1][0], "Order of player 2 isn't correct");
        assertEquals(5, playersDamage[1][1], "Total player 2 damage isn't correct");
        assertEquals(1, playersDamage[3][0], "Order of player 4 isn't correct");
        assertEquals(4, playersDamage[3][1], "Total player 4 damage isn't correct");
        assertEquals(0, marksReceived[3], "Marks are not updated");

        marksReceived = new int[] {0, 5, 0, 0, 0};
        player.setMarksReceived(marksReceived);
        player.receivedDamages(1);
        player.receivedDamages(3);
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
        Player p = new Player(1);
        p.receivedDamages(3);
        p.receivedDamages(1);
        p.receivedDamages(2);
        p.receivedDamages(3);
        int [] sorted = p.sortingPlayers();

        assertEquals(3, sorted[0], "First element incorrect");
        assertEquals(1, sorted[1], "Second element incorrect");
        assertEquals(2, sorted[2], "Third element incorrect");
        assertEquals(0, sorted[3], "Fourth element incorrect");
        assertEquals(4, sorted[4], "Fifth element incorrect");
    }

    @Test
    public void givePointsTest(){
        Player p = new Player(1);
        p.receivedDamages(3);
        p.receivedDamages(1);
        p.receivedDamages(2);
        p.receivedDamages(3);
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
        Player player = new Player(0);
        for(int i=0; i<12; i++)
            player.receivedDamages(4);
        player.receivedDamages(1);
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
        Player play = new Player(1);
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
        Player p = new Player(0);
        Position pos = new Position(1, 0, 'r', true, true);
        Position play = new Position(0, 0, 'r', true, false);
        p.roundBegin();
        p.setPosition(play);
        p.action(pos);

        assertEquals(1, p.getAction(), "Number of action isn't correct");
        assertEquals(1, p.getPosition().getCoordinate()[0], "Action Move doesn't work");

        p.action(play);
        p.action(pos);
    }

    @Test
    public void actionGrabTest (){
        Player p = new Player(0);
        Position pos = new Position(1, 0, 'r', true, true);
        p.setPosition(pos);
        p.roundBegin();
        p.action();

        assertEquals(1, p.getAction(), "First action doesn't work");

        pos = new Position(1, 0, 'r', true, false);
        char[] value = new char[] {'b', 'y', 'r', '_'};
        AmmoCard ammoCard = new AmmoCard();
        ammoCard.setValue(value);
        p.setPosition(pos);
        p.getPosition().setAmmo(ammoCard);
        p.setPosition(pos);
        p.action();
        p.action();

        assertEquals(0, p.getAction(), "First action doesn't work");
        assertEquals(2, p.getAmmo()[0], "Blue ammo aren't correct");
        assertEquals(2, p.getAmmo()[1], "Yellow ammo aren't correct");
        assertEquals(2, p.getAmmo()[2], "Red ammo aren't correct");
    }

    @Test
    public void reloadTest(){
        Player p = new Player(1);
        Board b = new Board(1);
        char[] cost = new char[] {'r','r', 'b', 'b'};
        WeaponCard wp = new WeaponCard("name", cost, b);
        wp.setLoaded(false);

        assertEquals(1, p.getAmmo()[2]);

        p.reload(wp);

        assertEquals(1, p.getAmmo()[2], "Red Ammo aren't correct");
        assertFalse(wp.isLoaded(), "Weapon is not load");
    }

    @Test
    public void setterGetterTest (){
        Player p = new Player(0);
        Board b = new Board(1);
        int[] marks = new int[] {0, 1, 3, 2, 1};
        char[] cost = new char[] {'r','r'};
        WeaponCard wp = new WeaponCard("name", cost, b);
        p.setScore(2);
        p.setFinalRound(true);
        p.setFirstPlayer(true);
        p.setRound(true);
        p.action(wp);
        p.action(wp);

        assertEquals(2, p.getScore(), "SetScore never works");
        assertTrue(p.isFinalRound(), "Final Round is false");
        assertTrue(p.isFirstPlayer(), "First player is false");
        assertTrue(p.isRound(), "Round is false");
        assertEquals(3, p.getMadeDamage());
        assertEquals(2, p.getDamagedPlayers()[0]);
        assertEquals(2, p.getDamagedPlayers()[1]);
        assertEquals(-1, p.getDamagedPlayers()[2]);
        assertEquals(0, p.getNumber());

        p.setMarksGiven(marks);
        p.setScore(3);
        p.setScore(1);

        assertEquals(6, p.getScore(), "SetScore isn't updated");
        assertEquals(0, p.getMarksGiven()[0]);
        assertEquals(3, p.getMarksGiven()[2]);
    }
}
