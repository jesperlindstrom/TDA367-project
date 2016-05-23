package se.chalmers.get_rect.game.entities.player;


import com.google.inject.Inject;
import se.chalmers.get_rect.game.entities.item.WeaponRepository;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.io.IOFacade;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PlayerRepository {

    private Player player;
    private WeaponRepository weaponRepository;
    private static final String melee = "opsword";
    private static final String ranged = "pistol";
    private static final String PATH = "data/savedData/";
    private static final String  FILE = "playerSavedData.json";

    private IOFacade<PlayerDataStore> json;

    @Inject
    public PlayerRepository(Player player, WeaponRepository weaponRepository){
        json = new IOFacade<>(PATH + FILE, PlayerDataStore.class);
        this.player = player;
        this.weaponRepository = weaponRepository;

    }


    public void save() throws FileNotFoundException {
        if (!hasFile()){
            File theFile = new File(PATH);
            boolean tmp = theFile.mkdirs();
            if (!tmp){
                System.out.println("Created new file path");
            }
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
        IWeapon melee = weaponRepository.getSingleWeapon(data.getMelee(),player);
        IWeapon ranged = weaponRepository.getSingleWeapon(data.getRanged(),player);
        player.addNewWeapon(melee);
        player.addNewWeapon(ranged);
    }

    public void reset() throws FileNotFoundException{
        player.setHealth(player.getMaxHealth());
        player.setHasFoundHunch(false);
        player.setRiding(false);
        player.addNewWeapon(weaponRepository.getSingleWeapon(melee, player));
        player.addNewWeapon(weaponRepository.getSingleWeapon(ranged, player));
    }
    public boolean hasFile(){
        return new File(PATH + FILE).isFile();
    }

}
