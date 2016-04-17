package se.chalmers.get_rect.utilities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {

    private Point p;
    private Point p2;

    @Before
    public void setup() {
        p = new Point(1, 2);
        p2 = null;
    }

    @Test
    public void testGetX() throws Exception {
        assertTrue(p.getX() == 1);
    }

    @Test
    public void testGetY() throws Exception {
        assertTrue(p.getY() == 2);
    }

    @Test
    public void testSetX() throws Exception {
        p2 = p.setX(3);
        assertFalse(p2.equals(p));
        assertTrue(p2.getX() == 3);
    }

    @Test
    public void testSetY() throws Exception {
        p2 = p.setY(3);
        assertFalse(p2.equals(p));
        assertTrue(p2.getY() == 3);
    }

    @Test
    public void testAddX() throws Exception {
        p2 = p.addX(3);
        assertFalse(p2.equals(p));
        assertTrue(p2.getX() == 4);
    }

    @Test
    public void testAddY() throws Exception {
        p2 = p.addY(3);
        assertFalse(p2.equals(p));
        assertTrue(p2.getY() == 5);
    }

    @Test
    public void testAddInts() throws Exception {
        p2 = p.add(3, 1);
        assertFalse(p2.equals(p));
        assertTrue(p2.getX() == p.getX() + 3);
        assertTrue(p2.getY() == p.getY() + 1);

    }

    @Test
    public void testAddPoints() throws Exception {
        p2 = p.add(p);
        assertFalse(p2.equals(p));
        assertEquals(p2.getX(), p.getX() + 1);
        assertEquals(p2.getY(), p.getY() + 2);
    }

    @Test
    public void testSubtract() throws Exception {

    }

    @Test
    public void testSubtract1() throws Exception {

    }

    @Test
    public void testSetPosition() throws Exception {

    }

    @Test
    public void testSetPosition1() throws Exception {

    }

    @Test
    public void testDeltaX() throws Exception {

    }

    @Test
    public void testDeltaY() throws Exception {

    }

    @Test
    public void testInverse() throws Exception {

    }

    @Test
    public void testDistanceTo() throws Exception {
        p2 = new Point(1, 2);
        assertEquals(p.distanceTo(p2), 0);

        p2 = new Point(3, 4);

        assertEquals(p.distanceTo(p2), 2);
        assertEquals(p.distanceTo(p2), p2.distanceTo(p));

        // (1,2) to (-4, 6) should be sqrt(5^2+4^2), but we round off to Integers.
        p2 = new Point(-4, 6);
        assertEquals(p.distanceTo(p2), (int)Math.sqrt(41));
        assertEquals(p2.distanceTo(p), (int)Math.sqrt(41));
    }

    @Test
    public void testToString() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {
        p2 = new Point(p);
        assertTrue(p2.equals(p));
        p2 = p2.addY(5);
        assertFalse(p2.equals(p));
    }

}