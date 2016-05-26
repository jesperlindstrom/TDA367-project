package se.chalmers.get_rect.tests.physics;

import org.junit.Before;
import org.junit.Test;

import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.frostbite.PhysicsEngine;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class PhysicsTest {
    private PhysicsEngine physics;
    private IPhysicsObject object;

    @Before
    public void setup() {
        physics = new PhysicsEngine();
        object = mock(IPhysicsObject.class);
    }

    @Test
    public void testRemovals() {
        physics.add(object);

        assertTrue("Object should be in physics list", physics.getEntities().contains(object));
        when(object.shouldBeRemoved()).thenReturn(true);
        physics.update(0);
        assertFalse("Object should not be in physics list", physics.getEntities().contains(object));
    }
}
