package se.chalmers.get_rect.game.entities.item;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.item.model.Pistol;
import se.chalmers.get_rect.game.entities.item.view.PistolView;
import se.chalmers.get_rect.game.entities.projectile.ProjectileFactory;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;

public class ItemFactory {
    @Inject private ProjectileFactory projectileFactory;

    public IEntity make(String type, IPhysicsModel model) {
        IModel tmpModel;
        IView tmpView;
        switch (type) {
            case "pistol" :
                tmpModel = new Pistol(model, projectileFactory);
                tmpView = new PistolView(tmpModel);
                break;
            default:
                tmpModel = new Pistol(model, projectileFactory);
                tmpView = new PistolView(tmpModel);
        }
        return new Entity(tmpModel, tmpView);
    }
}
