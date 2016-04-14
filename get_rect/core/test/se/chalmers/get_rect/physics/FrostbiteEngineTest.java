package se.chalmers.get_rect.physics;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.chalmers.get_rect.adapters.RectangleAdapterStub;
import se.chalmers.get_rect.physics.frostbite.PhysicsEngine;

import static org.mockito.Mockito.*;

public class FrostbiteEngineTest {
    private IPhysicsEngine engine;

    @Before
    public void setup() {
        engine = new PhysicsEngine();
    }

    @Test
    public void testSuccessfulCollision() {
        PhysicsObjectMock object1 = mock(PhysicsObjectMock.class);
        PhysicsObjectMock object2 = mock(PhysicsObjectMock.class);

        RectangleAdapterStub rect1 = mock(RectangleAdapterStub.class);
        RectangleAdapterStub rect2 = mock(RectangleAdapterStub.class);
        when(rect1.intersects(rect2)).thenReturn(true);
        when(rect2.intersects(rect1)).thenReturn(true);

        when(object1.getBoundingBox()).thenCallRealMethod();
        when(object2.getBoundingBox()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(object1).setBoundingBox(Mockito.any(RectangleAdapterStub.class));
        Mockito.doCallRealMethod().when(object2).setBoundingBox(Mockito.any(RectangleAdapterStub.class));

        object1.setBoundingBox(rect1);
        object2.setBoundingBox(rect2);

        engine.add(object1);
        engine.add(object2);

        engine.update(0);

        verify(object1, times(1)).onCollision(object2);
        verify(object2, times(1)).onCollision(object1);
    }

    @Test
    public void testFailedCollision() {
        PhysicsObjectMock object1 = mock(PhysicsObjectMock.class);
        PhysicsObjectMock object2 = mock(PhysicsObjectMock.class);

        RectangleAdapterStub rect1 = mock(RectangleAdapterStub.class);
        RectangleAdapterStub rect2 = mock(RectangleAdapterStub.class);
        when(rect1.intersects(rect2)).thenReturn(false);
        when(rect2.intersects(rect1)).thenReturn(false);

        when(object1.getBoundingBox()).thenCallRealMethod();
        when(object2.getBoundingBox()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(object1).setBoundingBox(Mockito.any(RectangleAdapterStub.class));
        Mockito.doCallRealMethod().when(object2).setBoundingBox(Mockito.any(RectangleAdapterStub.class));

        object1.setBoundingBox(rect1);
        object2.setBoundingBox(rect2);

        engine.add(object1);
        engine.add(object2);

        engine.update(0);

        verify(object1, times(0)).onCollision(object2);
        verify(object2, times(0)).onCollision(object1);
    }
}
