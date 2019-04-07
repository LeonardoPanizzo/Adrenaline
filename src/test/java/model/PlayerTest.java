package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
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
    }
}
