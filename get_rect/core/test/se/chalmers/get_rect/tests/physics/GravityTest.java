package se.chalmers.get_rect.tests.physics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import org.mockito.Mockito;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.physics.IPhysicsEngine;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.frostbite.GravityHandler;
import se.chalmers.get_rect.physics.frostbite.PhysicsEngine;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;

public class GravityTest {
    private GravityHandler gravity;
    private IPhysicsObject object;

    @Before
    public void setup() {
        gravity = new GravityHandler();
        object = mock(IPhysicsObject.class);
        when(object.getVelocity()).thenReturn(new Point(0, 0));
    }

    @Test
    public void testFalling() {
        CollisionData collisionData = new CollisionData();
        gravity.apply(object, collisionData);

        verify(object).setVelocity(new Point(0, -3));
    }

    @Test
    public void testLandingOnGround() {
        CollisionData collisionData = new CollisionData();
        collisionData.set(Side.BOTTOM);

        gravity.apply(object, collisionData);

        verify(object).setVelocity(new Point(0, 0));
    }
}
