package se.chalmers.get_rect.game.entities.player;


import com.google.inject.Inject;
import se.chalmers.get_rect.game.entities.item.WeaponRepository;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.io.IOFacade;
import se.chalmers.get_rect.utilities.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerRepository {
    private static final String DEFAULT_MELEE = "opsword";
    private static final String DEFAULT_RANGED = "pistol";
    private static final String PATH = "data/savedData/";
    private static final String FILE = "playerSavedData.json";
    private Player player;
    private WeaponRepository weaponRepository;
    private IOFacade<PlayerDataStore> json;

    @Inject
    public PlayerRepository(Player player, WeaponRepository weaponRepository){
        json = new IOFacade<>(PATH + FILE, PlayerDataStore.class);
        this.player = player;
        this.weaponRepository = weaponRepository;

    }

    public void save() throws IOException {
        if (!FileUtils.folderExists(PATH)){
            FileUtils.createFolder(PATH);
        }

        List<PlayerDataStore> list = new ArrayList<>();
        PlayerDataStore dataStore = new PlayerDataStore(player.getCurrentHealth(), player.hasFoundHunch(), player.getMeleeWeapon().getType(), player.getRangedWeapon().getType());
        list.add(dataStore);

        json.save(list);
    }

    public void load() throws FileNotFoundException{
        if (!hasFile()) throw new FileNotFoundException();
        PlayerDataStore data = json.load().get(0);
        player.setHasFoundHunch(data.isHasFoundHunch());
        player.setHealth(data.getHealth());
        IWeapon melee = weaponRepository.getSingleWeapon(data.getMelee());
        IWeapon ranged = weaponRepository.getSingleWeapon(data.getRanged());
        player.addNewWeapon(melee);
        player.addNewWeapon(ranged);

    }

    public void reset() {
        player.setHealth(player.getMaxHealth());
        player.setHasFoundHunch(false);
        player.setRiding(false);
        player.addNewWeapon(weaponRepository.getSingleWeapon(DEFAULT_MELEE));
        player.addNewWeapon(weaponRepository.getSingleWeapon(DEFAULT_RANGED));
    }

    public boolean hasFile(){
        return FileUtils.fileExists(PATH + FILE);
    }
}
