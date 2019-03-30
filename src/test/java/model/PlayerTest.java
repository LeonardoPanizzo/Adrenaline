package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    public void setDamageTest(){
        Player p = new Player();
        int[] life = new int[12];
        life = p.getLife();
        for(int i =0; i<12; i++){
            assertEquals(life[i], 0);
        }

        p.setDamage(3);
        life = p.getLife();
        assertEquals(life[0], 3);

        p.setDamage(2);
        p.setDamage(4);
        life = p.getLife();
        assertEquals(life[1], 2);
        assertEquals(life[2], 4);
    }

    @Test
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
    }
}
