package Adrenaline.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    @Test
    public void visibleTest() {
        //b blue r red y yellow w white
        AmmoDeck deck1=new AmmoDeck();
        WeaponDeck deck2=new WeaponDeck();
        Position p11 = new Position(1, 1, 'b', true, true, deck1, deck2);
        Position p12 = new Position(1, 2, 'b', false, false, deck1, deck2);
        Position p13 = new Position(1, 3, 'b', true, true, deck1, deck2);
        Position p21 = new Position(2, 1, 'r', true, true, deck1, deck2);
        Position p22 = new Position(2, 2, 'r', true, false, deck1, deck2);
        Position p23 = new Position(2, 3, 'r', true, false, deck1, deck2);
        Position p24 = new Position(2, 4, 'y', true, false, deck1, deck2);
        Position p34 = new Position(3, 4, 'y', true, true, deck1, deck2);
        Position p32 = new Position(3, 2, 'w', true, false, deck1, deck2);
        Position p33 = new Position(3, 3, 'w', true, true, deck1, deck2);
        p11.setLinks(p21);
        p13.setLinks(p23);
        p21.setLinks(p11);
        p22.setLinks(p32);
        p23.setLinks(p24);
        p23.setLinks(p13);
        p24.setLinks(p23);
        p34.setLinks(p33);
        p32.setLinks(p22);
        p33.setLinks(p34);


        assertEquals(p11.visible(p12), true);
        assertEquals(p11.visible(p21), true);
        assertEquals(p12.visible(p24), false);
        assertEquals(p11.visible(p33), false);
        assertEquals(p23.visible(p24), true);
        assertEquals(p23.visible(p13), true);
        assertEquals(p13.visible(p23), true);
        assertEquals(p24.visible(p23), true);
        assertEquals(p32.visible(p12), false);
        assertEquals(p24.visible(p34), true);
    }

    @Test
    public void reachableTest(){
        //b blue r red y yellow w white
        AmmoDeck deck1=new AmmoDeck();
        WeaponDeck deck2=new WeaponDeck();
        Position p11 = new Position(1, 1, 'b', true, true, deck1, deck2);
        Position p12 = new Position(1, 2, 'b', false, false, deck1, deck2);
        Position p13 = new Position(1, 3, 'b', true, true, deck1, deck2);
        Position p21 = new Position(2, 1, 'r', true, true, deck1, deck2);
        Position p22 = new Position(2, 2, 'r', true, false, deck1, deck2);
        Position p23 = new Position(2, 3, 'r', true, false, deck1, deck2);
        Position p24 = new Position(2, 4, 'y', true, false, deck1, deck2);
        Position p34 = new Position(3, 4, 'y', true, true, deck1, deck2);
        Position p32 = new Position(3, 2, 'w', true, false, deck1, deck2);
        Position p33 = new Position(3, 3, 'w', true, true, deck1, deck2);
        p11.setLinks(p21);
        p13.setLinks(p23);
        p21.setLinks(p11);
        p22.setLinks(p32);
        p23.setLinks(p24);
        p23.setLinks(p13);
        p24.setLinks(p23);
        p34.setLinks(p33);
        p32.setLinks(p22);
        p33.setLinks(p34);

        assertEquals(p11.reachable(p12), true);
        assertEquals(p11.reachable(p21), true);
        assertEquals(p11.reachable(p13), false);
        assertEquals(p12.reachable(p22), false);
        assertEquals(p23.reachable(p24), true);
        assertEquals(p23.reachable(p13), true);
        assertEquals(p24.reachable(p23), true);
        assertEquals(p13.reachable(p23), true);
        assertEquals(p34.reachable(p32), false);
        assertEquals(p11.reachable(p33), false);
    }

    @Test
    public void getCoordinateTest(){
        AmmoDeck deck1=new AmmoDeck();
        WeaponDeck deck2=new WeaponDeck();
        Position p11 = new Position(1, 1, 'b', true, true, deck1, deck2);
        Position p12 = new Position(1, 2, 'b', false, false, deck1, deck2);
        int[] c11= new int[2];
        int[] c12= new int[2];
        c11[0]=1;
        c11[1]=1;
        c12[0]=1;
        c12[1]=2;

        //ho fatto assertEquals fra interi perchè non so come farlo fra array
        assertEquals(p11.getCoordinate()[0],c11[0]);
        assertEquals(p11.getCoordinate()[1],c11[1]);
        assertEquals(p12.getCoordinate()[0],c12[0]);
        assertEquals(p12.getCoordinate()[1],c12[1]);
    }

    @Test
    public void isDoorTest(){
        AmmoDeck deck1=new AmmoDeck();
        WeaponDeck deck2=new WeaponDeck();
        Position p11 = new Position(1, 1, 'b', true, true, deck1, deck2);
        Position p12 = new Position(1, 2, 'b', false, false, deck1, deck2);

        assertEquals(p11.isDoor(), true);
        assertEquals(p12.isDoor(), false);
    }

    @Test void getRoomTest(){
        //b blue r red y yellow w white
        AmmoDeck deck1=new AmmoDeck();
        WeaponDeck deck2=new WeaponDeck();
        Position p11 = new Position(1, 1, 'b', true, true, deck1, deck2);
        Position p21 = new Position(2, 1, 'r', true, true, deck1, deck2);
        Position p34 = new Position(3, 4, 'y', true, true, deck1, deck2);

        assertEquals(p11.getRoom(),'b');
        assertEquals(p21.getRoom(),'r');
        assertEquals(p34.getRoom(),'y');
    }
}