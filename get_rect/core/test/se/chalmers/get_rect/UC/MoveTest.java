package se.chalmers.get_rect.UC;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.adapters.RectangleAdapterStub;
import se.chalmers.get_rect.adapters.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.IPhysicsEngine;
import se.chalmers.get_rect.physics.PhysicsObjectMock;
import se.chalmers.get_rect.physics.frostbite.PhysicsEngine;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;
import se.chalmers.get_rect.utilities.SideData;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// Use case 1: Move
public class MoveTest {
    private Player player;

    @Before
    public void setup() {
        IRectangleFactoryAdapter rectangleFactory = new RectangleFactoryAdapterStub();
        player = new Player(rectangleFactory);
    }

    // Normal flow of events: no input
    @Test
    public void testStandStill() {
        assertEquals("Should stand still before any input", player.getVelocity(), new Point(0, 0));
    }

    // Normal flow of events: left
    @Test
    public void testMoveLeft() {
        player.moveLeft();
        assertTrue("Should have a negative X velocity", player.getVelocity().getX() < 0);
    }

    // Normal flow of events: left
    @Test
    public void testMoveRight() {
        player.moveRight();
        assertTrue("Should have a positive X velocity", player.getVelocity().getX() > 0);
    }

    // Alternate flow of events: path obstructed
    @Test
    public void testPathObstructed() {

    }

    // Alternate flow of events: enemy in the way
    @Test
    public void testEnemyInTheWay() {

    }
}
