package se.chalmers.get_rect.tests.physics;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.physics.IPhysicsEngine;
import se.chalmers.get_rect.physics.IRectangleAdapter;
import se.chalmers.get_rect.physics.frostbite.PhysicsEngine;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovementTest {
    IPhysicsEngine physics;
    PhysicsObjectMock player;
    PhysicsObjectMock solidObject;

    @Before
    public void setup() {
        physics = new PhysicsEngine();

        player = Mockito.mock(PhysicsObjectMock.class);
        solidObject = mock(PhysicsObjectMock.class);
        when(solidObject.isSolid()).thenReturn(true);

        Mockito.doCallRealMethod().when(player).getVelocity();
        Mockito.doCallRealMethod().when(player).setVelocity(Mockito.any(Point.class));
        Mockito.doCallRealMethod().when(player).getPosition();
        Mockito.doCallRealMethod().when(player).setPosition(Mockito.any(Point.class));
        Mockito.doCallRealMethod().when(player).setBoundingBox(Mockito.any(IRectangleAdapter.class));

        solidObject.setBoundingBox(new RectangleAdapterStub(0, 0, 0, 0));
        solidObject.setVelocity(new Point(0, 0));
        solidObject.setPosition(new Point(0, 0));

        player.setBoundingBox(new RectangleAdapterStub(0, 0, 0, 0));
        player.setVelocity(new Point(0, 0));
        player.setPosition(new Point(0, 0));

        physics.add(player);
        physics.add(solidObject);
    }

    @Test
    public void testPathObstructed() {
        // Inject rectangles that intersect on the right side of the player.
        RectangleAdapterStub rect1 = mock(RectangleAdapterStub.class);
        RectangleAdapterStub rect2 = mock(RectangleAdapterStub.class);
        CollisionData rectCollision1 = new CollisionData();
        CollisionData rectCollision2 = new CollisionData();
        rectCollision1.set(Side.RIGHT);
        rectCollision2.set(Side.LEFT);
        when(rect1.intersects(rect2)).thenReturn(rectCollision1);
        when(rect2.intersects(rect1)).thenReturn(rectCollision2);
        when(player.getBoundingBox()).thenReturn(rect1);
        when(solidObject.getBoundingBox()).thenReturn(rect2);

        Point oldPos = player.getPosition();

        // Simulate move right
        player.setVelocity(new Point(50, 0));

        // Simulate a game tick
        physics.update(1);

        assertEquals("Entity shouldn't move when something is in the way", oldPos, player.getPosition());
    }

    @Test
    public void testPathFree() {
        // Inject rectangles that intersect on the right side of the player.
        RectangleAdapterStub rect1 = mock(RectangleAdapterStub.class);
        RectangleAdapterStub rect2 = mock(RectangleAdapterStub.class);
        when(rect1.intersects(rect2)).thenReturn(null);
        when(rect2.intersects(rect1)).thenReturn(null);
        when(player.getBoundingBox()).thenReturn(rect1);
        when(solidObject.getBoundingBox()).thenReturn(rect2);

        // Simulate move right
        player.setVelocity(new Point(50, 0));

        // Simulate a game tick at 0.1s delta
        physics.update(1);

        assertEquals("Entity should move when nothing is in the way", player.getPosition(), new Point(50, 0));
    }
}
