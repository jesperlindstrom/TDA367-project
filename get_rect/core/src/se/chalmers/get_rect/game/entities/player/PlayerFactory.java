package se.chalmers.get_rect.game.entities.player;

import com.google.inject.Inject;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.game.entities.item.ItemFactory;
import se.chalmers.get_rect.game.entities.item.WeaponRepository;
import se.chalmers.get_rect.game.entities.item.model.IMelee;
import se.chalmers.get_rect.game.entities.item.model.IRanged;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.item.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.entities.PhysicsEntity;

import java.io.FileNotFoundException;

public class PlayerFactory {
    @Inject private IRectangleFactoryAdapter rectangleFactory;
    @Inject private WeaponRepository weaponRepository;
    @Inject private IAssetManagerAdapter assetManager;

    private static final String meleeStarting = "opaxe";
    private static final String rangedStarting = "pistol";


    public PhysicsEntity make() {
        Player model = new Player(rectangleFactory);
        IView view = new PlayerView(model, assetManager);
        try {
            model.addNewWeapon(weaponRepository.getSingleWeapon(rangedStarting, model));
            model.addNewWeapon(weaponRepository.getSingleWeapon(meleeStarting, model));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return new PhysicsEntity(model,view);
    }
}
