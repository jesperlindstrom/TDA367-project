package se.chalmers.get_rect.game.entities.item.swing;

import com.badlogic.gdx.assets.AssetManager;
import com.google.inject.Inject;
import se.chalmers.get_rect.game.entities.Entity;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;

public class SwingFactory {
    @Inject private IRectangleFactoryAdapter rectangleFactory;

    public IEntity make(int damage, int width, int height, int swingFrames, IPhysicsModel owner, ISwinger weapon) {
        Swing swing = new Swing(damage, width, height, swingFrames, rectangleFactory, owner, weapon);
        return new Entity(swing, null);
    }
}
