package se.chalmers.get_rect.game.entities.item.swing;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.entities.Entity;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;

public class SwingFactory {
    @Inject private IRectangleFactoryAdapter rectangleFactory;

    public IEntity make(int damage, int reach, int swingFrames, IPhysicsModel owner, ISwinger weapon) {
        Swing swing = new Swing(damage, reach, swingFrames, rectangleFactory, owner, weapon);
        return new Entity(swing, null);
    }

    public IEntity make(int damage, int reach, int cooldown, IPhysicsModel owner, ISwinger weapon, boolean isSolid) {
        Swing swing = new Swing(damage, reach, cooldown, rectangleFactory, owner, weapon, isSolid);
        return new Entity(swing, null);
    }
    public IEntity makeUppercut(int damage, int reach, int cooldown, IPhysicsModel owner, ISwinger weapon) {
        Uppercut uppercut = new Uppercut(damage, reach, cooldown, rectangleFactory, owner, weapon);
        return new Entity(uppercut, null);
    }
}
