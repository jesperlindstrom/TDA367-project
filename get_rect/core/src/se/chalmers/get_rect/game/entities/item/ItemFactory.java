package se.chalmers.get_rect.game.entities.item;

import com.google.inject.Inject;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.item.damageBoxes.SwingFactory;
import se.chalmers.get_rect.game.entities.item.model.OpSwordNEtt;
import se.chalmers.get_rect.game.entities.item.model.Pistol;
import se.chalmers.get_rect.game.entities.item.view.OpSwordNEttView;
import se.chalmers.get_rect.game.entities.item.view.PistolView;
import se.chalmers.get_rect.game.entities.item.damageBoxes.ProjectileFactory;

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
            default:
                tmpModel = new Pistol(model, projectileFactory);
                tmpView = new PistolView((IRanged) tmpModel, assetManager);
        }
        return new Entity(tmpModel, tmpView);
    }
}
