package se.chalmers.get_rect.game.entities.item;

import com.google.inject.Inject;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.item.model.*;
import se.chalmers.get_rect.game.entities.item.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;
import se.chalmers.get_rect.game.entities.item.view.OpAxeView;
import se.chalmers.get_rect.game.entities.item.view.OpSwordNEttView;
import se.chalmers.get_rect.game.entities.item.view.PistolView;

public class ItemFactory {
    @Inject private ProjectileFactory projectileFactory;
    @Inject private IAssetManagerAdapter assetManager;
    @Inject private SwingFactory swingFactory;

    public IEntity make(String type, IPhysicsModel model) {
        IWeapon tmpModel;
        IView tmpView;
        switch (type) {
            case "pistol" :
                tmpModel = new Pistol(model, projectileFactory);
                tmpView = new PistolView((IRanged) tmpModel, assetManager);
                break;
            case "opswordnett" :
                tmpModel = new OpSwordNEtt(model, swingFactory);
                tmpView = new OpSwordNEttView((IMelee)tmpModel);
                break;
            case "opaxe" :
                tmpModel = new OpAxe(model, swingFactory);
                tmpView = new OpAxeView((IMelee)tmpModel);
                break;
            default:
                tmpModel = new Pistol(model, projectileFactory);
                tmpView = new PistolView((IRanged) tmpModel, assetManager);
        }
        return new Entity(tmpModel, tmpView);
    }
}
