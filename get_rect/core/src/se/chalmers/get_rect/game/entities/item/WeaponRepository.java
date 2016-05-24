package se.chalmers.get_rect.game.entities.item;

import se.chalmers.get_rect.game.entities.AbstractRepository;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.io.IOFacade;
import se.chalmers.get_rect.utilities.FileUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeaponRepository extends AbstractRepository<WeaponsDataStore, IWeapon> {
    private WeaponFactory weaponFactory;
    private IPhysicsModel user;
    private IOFacade<WeaponSaveDataStore> json;
    private static final String PATH = "data/savedData/";
    private static final String FILE = "weaponSavedData.json";
    private List<IWeapon> weaponList;
    private boolean useSave = true;

    public WeaponRepository(WeaponFactory weaponFactory) {
        super("weapons", WeaponsDataStore.class);
        this.weaponFactory = weaponFactory;
        json = new IOFacade<>(PATH+FILE, WeaponSaveDataStore.class);
    }

    public IWeapon getSingleWeapon(String type) {
        if (weaponList == null)
            throw new RuntimeException("WeaponRepository must be preloaded before access");

        for (IWeapon weapon : weaponList) {
            if (weapon.getType().equals(type)) {
                weapon.setFound(true);
                return weapon;
            }
        }

        return null;
    }

    public void preload(IPhysicsModel user) throws FileNotFoundException {
        this.user = user;

        if (weaponList == null) {
            weaponList = super.get("items");
            Map<String, Boolean> foundMap = useSave ? getSaveData() : new HashMap<>();

            for (IWeapon weapon : weaponList) {
                Boolean isFound = foundMap.get(weapon.getType());
                if (isFound != null) {
                    weapon.setFound(isFound);
                }
            }
        }
    }

    public List<IWeapon> get() {
        if (weaponList == null)
            throw new RuntimeException("WeaponRepository must be preloaded before access");

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

     public void saveWeapons() throws IOException {
         if (!FileUtils.folderExists(PATH)) {
             FileUtils.createFolder(PATH);
         }

         List<WeaponSaveDataStore> list = new ArrayList<>();
         for (IWeapon weapon : weaponList) {
             WeaponSaveDataStore dataStore = new WeaponSaveDataStore(weapon.getType(), weapon.isFound());
             list.add(dataStore);
         }

         json.save(list);
     }

    public void reset() {
        useSave = false;

        if (weaponList == null)
            return;

        for (IWeapon weapon : weaponList) {
            weapon.setFound(false);
        }
    }
}
