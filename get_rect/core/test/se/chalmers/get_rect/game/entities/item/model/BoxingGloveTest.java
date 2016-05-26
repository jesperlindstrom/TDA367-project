package se.chalmers.get_rect.game.entities.item.model;

import org.junit.Test;
import se.chalmers.get_rect.game.entities.Entity;
import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.swing.Swing;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;
import se.chalmers.get_rect.utilities.Point;

import static org.mockito.Mockito.*;

public class BoxingGloveTest {

    @Test
    public void testUse() throws Exception {
        IEntityHolder holder = mock(IEntityHolder.class);
        IPhysicsModel model = mock(IPhysicsModel.class);
        SwingFactory factory = mock(SwingFactory.class);
        Swing swing = mock(Swing.class);
        Entity entity = new Entity(swing, null);
        BoxingGlove box = new BoxingGlove(model, "box", factory, 1, 1, 3, 10, 1, false);
        when(factory.makeUppercut(1, 1, 3, model, box)).thenReturn(entity);
        box.use(new Point(), holder);
        verify(holder, times(1)).add(entity);
    }
}