package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    public void reicevedDamageTest(){
        Player p = new Player(1);
        p.receivedDamages(3);
        int life = p.getLife();
        int[] damageOrder = p.getDamageOrder();
        int[] playersDamage = p.getPlayersDamage();

        assertEquals(10, life);
        assertEquals(3, damageOrder[0]);
        assertEquals(1, playersDamage[3]);

     /*   p.receivedDamages(2);
        p.receivedDamages(1);
        life = p.getLife();

        assertEquals(8, life);*/
    }

/*    @Test
    public void givePointsTest(){
        Player p = new Player();
        int[] lifeOfP = new int[12];
        int[] points = new int[5];

        lifeOfP = p.getLife();
        assertEquals(lifeOfP[0], 0);

        assertEquals(0, points[0]);
        assertEquals(0, points[1]);
        assertEquals(0, points[3]);

        p.setDamage(1);
        p.setDamage(4);
        p.setDamage(4);
        p.setDamage(4);
        points = p.givePoints();

        assertEquals(1, points[0]);
        assertEquals(0, points[1]);
        assertEquals(3, points[3]);

        p.setDamage(2);
        p.setDamage(3);
        p.setDamage(5);
        points = p.givePoints();

        assertEquals(1, points[1]);
        assertEquals(1, points[2]);
        assertEquals(1, points[4]);
    }*/
}
