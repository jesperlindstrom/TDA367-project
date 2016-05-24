package se.chalmers.get_rect.game.entities.item;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.entities.AbstractRepository;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.io.IOFacade;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeaponRepository extends AbstractRepository<WeaponsDataStore, IWeapon> {

    @Inject private WeaponFactory weaponFactory;
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
        List<IWeapon> weaponList = super.get(folderName);
        Map<String, Boolean> foundMap = getSaveData();
        for (IWeapon weapon : weaponList) {
            Boolean isFound = foundMap.get(weapon.getType());
            if (isFound != null) {
                weapon.setFound(isFound);
            }
        }
        return weaponList;
    }

    @Override
    protected IWeapon makeFromDataStore(WeaponsDataStore data) {
        return weaponFactory.make(data.getType(), user, data.getReach(), data.getDamage(), data.getCooldown(), data.getSpeed(), data.getSwingDegrees(), data.getStartTilt());
    }

    private Map<String, Boolean> getSaveData() {
        Map<String, Boolean> foundMap = new HashMap<>();

        IOFacade<WeaponSaveDataStore> dataLoader = new IOFacade<>("data/savedData/weaponSavedData.json", WeaponSaveDataStore.class);
        List<WeaponSaveDataStore> dataList;
        try {
            dataList = dataLoader.load();
            System.out.println(dataList);
        } catch (FileNotFoundException e) {
            return foundMap;
        }

        for (WeaponSaveDataStore data : dataList) {
            foundMap.put(data.getType(), data.isFound());
        }
        return foundMap;
    }
}
