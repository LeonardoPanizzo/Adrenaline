package Adrenaline.Server.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeaponsTest {
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

        //mode2 = {0,, 1, 2}
    }
}
