package se.chalmers.get_rect.tests.physics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import org.mockito.Mockito;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.physics.IPhysicsEngine;
import se.chalmers.get_rect.physics.frostbite.PhysicsEngine;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;

public class GravityTest {
    private IPhysicsEngine engine;

    @Before
    public void setup() {
        engine = new PhysicsEngine();
    }

    @Test
    public void testFalling() {
        PhysicsObjectMock object = new PhysicsObjectMock();

        int oldVelY = object.getVelocity().getY();

        engine.add(object);
        engine.update(0);

        int newVelY = object.getVelocity().getY();

        // New velocity Y should be smaller, because gravity has been applied
        assertTrue(oldVelY > newVelY);
    }

    @Test
    public void testLandingOnGround() {
        // Create an entity and a ground
        PhysicsObjectMock entity = mock(PhysicsObjectMock.class);
        PhysicsObjectMock ground = mock(PhysicsObjectMock.class);
        when(ground.isSolid()).thenReturn(true);

        // Inject rectangles that intersect on the bottom/top side.
        RectangleAdapterStub rect1 = mock(RectangleAdapterStub.class);
        RectangleAdapterStub rect2 = mock(RectangleAdapterStub.class);
        CollisionData rectCollision1 = new CollisionData();
        CollisionData rectCollision2 = new CollisionData();
        rectCollision1.set(Side.BOTTOM);
        rectCollision2.set(Side.TOP);
        rectCollision1.setSolidOverlap(new RectangleAdapterStub(0, 0, 0, 50));
        rectCollision2.setSolidOverlap(new RectangleAdapterStub(0, 0, 0, 50));
        when(rect1.intersects(rect2)).thenReturn(rectCollision1);
        when(rect2.intersects(rect1)).thenReturn(rectCollision2);
        Mockito.doCallRealMethod().when(entity).setBoundingBox(Mockito.any(RectangleAdapterStub.class));
        Mockito.doCallRealMethod().when(ground).setBoundingBox(Mockito.any(RectangleAdapterStub.class));
        entity.setBoundingBox(rect1);
        ground.setBoundingBox(rect2);
        Mockito.doCallRealMethod().when(entity).getBoundingBox();
        Mockito.doCallRealMethod().when(ground).getBoundingBox();

        // Make sure we can set velocity and position
        Mockito.doCallRealMethod().when(entity).getVelocity();
        Mockito.doCallRealMethod().when(entity).getPosition();
        Mockito.doCallRealMethod().when(entity).setVelocity(Mockito.any(Point.class));
        Mockito.doCallRealMethod().when(entity).setPosition(Mockito.any(Point.class));
        Mockito.doCallRealMethod().when(ground).getVelocity();
        Mockito.doCallRealMethod().when(ground).getPosition();
        Mockito.doCallRealMethod().when(ground).setVelocity(Mockito.any(Point.class));
        Mockito.doCallRealMethod().when(ground).setPosition(Mockito.any(Point.class));

        // Set first position
        entity.setPosition(new Point(0, 0));
        ground.setPosition(new Point(0, 0));

        // Simulate falling
        entity.setVelocity(new Point(0, -30));

        engine.add(entity);
        engine.add(ground);

        engine.update(0);

        // Since we collide with a solid object below us, the fall speed should be 0.
        assertEquals(entity.getVelocity().getY(), 0);
    }
}
