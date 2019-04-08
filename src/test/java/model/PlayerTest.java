package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void costructorTest(){
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
        assertEquals(8, points[3],"Player 4 points aren't correct");
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
        assertEquals(8, points[4],"Player 5 points aren't correct");

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
}
