package se.chalmers.get_rect.physics;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.chalmers.get_rect.adapters.RectangleAdapterStub;
import se.chalmers.get_rect.physics.frostbite.PhysicsEngine;
import se.chalmers.get_rect.utilities.Side;
import se.chalmers.get_rect.utilities.CollisionData;

import static org.mockito.Mockito.*;

public class CollisionTest {
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
        CollisionData rectCollision1 = new CollisionData();
        CollisionData rectCollision2 = new CollisionData();
        rectCollision1.set(Side.TOP);
        rectCollision2.set(Side.BOTTOM);
        when(rect1.intersects(rect2)).thenReturn(rectCollision1);
        when(rect2.intersects(rect1)).thenReturn(rectCollision2);

        when(object1.getBoundingBox()).thenCallRealMethod();
        when(object2.getBoundingBox()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(object1).setBoundingBox(Mockito.any(RectangleAdapterStub.class));
        Mockito.doCallRealMethod().when(object2).setBoundingBox(Mockito.any(RectangleAdapterStub.class));

        object1.setBoundingBox(rect1);
        object2.setBoundingBox(rect2);

        engine.add(object1);
        engine.add(object2);

        engine.update(0);

        verify(object1, times(1)).onCollision(object2, rectCollision1, false);
        verify(object2, times(1)).onCollision(object1, rectCollision2, false);
    }

    @Test
    public void testFailedCollision() {
        PhysicsObjectMock object1 = mock(PhysicsObjectMock.class);
        PhysicsObjectMock object2 = mock(PhysicsObjectMock.class);

        RectangleAdapterStub rect1 = mock(RectangleAdapterStub.class);
        RectangleAdapterStub rect2 = mock(RectangleAdapterStub.class);
        when(rect1.intersects(rect2)).thenReturn(null);
        when(rect2.intersects(rect1)).thenReturn(null);

        when(object1.getBoundingBox()).thenCallRealMethod();
        when(object2.getBoundingBox()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(object1).setBoundingBox(Mockito.any(RectangleAdapterStub.class));
        Mockito.doCallRealMethod().when(object2).setBoundingBox(Mockito.any(RectangleAdapterStub.class));

        object1.setBoundingBox(rect1);
        object2.setBoundingBox(rect2);

        engine.add(object1);
        engine.add(object2);

        engine.update(0);

        verify(object1, times(0)).onCollision(object2, null, false);
        verify(object2, times(0)).onCollision(object1, null, false);
    }
}
