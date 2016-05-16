package se.chalmers.get_rect.game.entities.item;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.entities.AbstractRepository;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;

import java.io.FileNotFoundException;
import java.util.List;

public class WeaponRepository extends AbstractRepository<WeaponsDataStore, IWeapon> {

    @Inject private ItemFactory itemFactory;
    private IPhysicsModel user;

    public WeaponRepository() {
        super("weapons", WeaponsDataStore.class);
    }

    public IWeapon getSingleWeapon(String type, IPhysicsModel user) throws FileNotFoundException {
        List<IWeapon> list = get("items", user);
        for (IWeapon weapon : list) {
            if (weapon.getType().equals(type)) {
                return weapon;
            }
        }
        return null;
    }

    public List<IWeapon> get(String folderName, IPhysicsModel user) throws FileNotFoundException {
        this.user = user;
        return super.get(folderName);
    }

    @Override
    protected IWeapon makeFromDataStore(WeaponsDataStore data) {
        return itemFactory.make(data.getType(), user, data.getWidth(), data.getHeight(), data.getDamage(), data.getFrames(), data.getSpeed());
    }
}
