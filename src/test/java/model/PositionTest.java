package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    @Test
    public void visibleTest() {
        Position p1 = new Position(1, 2, 5, false, false);
        Position p2 = new Position(1, 1, 5, false, false);
        Position p3 = new Position(3, 4, 2, false, false);
        Position p4 = new Position(3, 2, 2, false, false);

        assertEquals(p1.visible(p2), true);
        assertEquals(p2.visible(p1), true);
        assertEquals(p1.visible(p3), false);
        assertEquals(p3.visible(p1), false);
        assertEquals(p3.visible(p4), true);
        assertEquals(p4.visible(p3), true);
    }
}