package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    @Test
    public void visibleTest() {
        //1 blu 2 rossa 3 gialla 4 bianca
        Position p11 = new Position(1, 1, 1, true, true);
        Position p12 = new Position(1, 2, 1, false, false);
        Position p13 = new Position(1, 3, 1, true, true);
        Position p21 = new Position(2, 1, 2, true, true);
        Position p22 = new Position(2, 2, 2, true, false);
        Position p23 = new Position(2, 3, 2, true, false);
        Position p24 = new Position(2, 4, 3, true, false);
        Position p34 = new Position(3, 4, 3, true, true);
        Position p32 = new Position(3, 2, 4, true, false);
        Position p33 = new Position(3, 3, 4, true, true);
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
        //1 blu 2 rossa 3 gialla 4 bianca
        Position p11 = new Position(1, 1, 1, true, true);
        Position p12 = new Position(1, 2, 1, false, false);
        Position p13 = new Position(1, 3, 1, true, true);
        Position p21 = new Position(2, 1, 2, true, true);
        Position p22 = new Position(2, 2, 2, true, false);
        Position p23 = new Position(2, 3, 2, true, false);
        Position p24 = new Position(2, 4, 3, true, false);
        Position p34 = new Position(3, 4, 3, true, true);
        Position p32 = new Position(3, 2, 4, true, false);
        Position p33 = new Position(3, 3, 4, true, true);
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
}