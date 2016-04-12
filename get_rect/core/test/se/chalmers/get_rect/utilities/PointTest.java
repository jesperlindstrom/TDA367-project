package se.chalmers.get_rect.utilities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by simsund on 2016-04-12.
 */
public class PointTest {

    private Point p;
    private Point result;

    @Before
    public void setup() {
        p = new Point(1, 2);
        result = null;
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
        result = p.setX(3);
        assertFalse(result.equals(p));
        assertTrue(result.getX() == 3);
    }

    @Test
    public void testSetY() throws Exception {
        result = p.setY(3);
        assertFalse(result.equals(p));
        assertTrue(result.getY() == 3);
    }

    @Test
    public void testAddX() throws Exception {
        result = p.addX(3);
        assertFalse(result.equals(p));
        assertTrue(result.getX() == 4);
    }

    @Test
    public void testAddY() throws Exception {
        result = p.addY(3);
        assertFalse(result.equals(p));
        assertTrue(result.getY() == 5);
    }

    @Test
    public void testAddInts() throws Exception {
        result = p.add(3, 1);
        assertFalse(result.equals(p));
        assertTrue(result.getX() == p.getX() + 3);
        assertTrue(result.getY() == p.getY() + 1);
    }

    @Test
    public void testAddPoints() throws Exception {
        result = p.add(p);
        assertFalse(result.equals(p));
        assertTrue(result.getX() == p.getX() + 1);
        assertTrue(result.getY() == p.getY() + 2);
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
    public void testToString() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {
        result = new Point(p);
        assertTrue(result.equals(p));
        result = result.addY(5);
        assertFalse(result.equals(p));
    }

}