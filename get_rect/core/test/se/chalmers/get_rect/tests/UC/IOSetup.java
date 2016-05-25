package se.chalmers.get_rect.tests.UC;


import org.junit.Before;
import org.mockito.Mockito;
import se.chalmers.get_rect.game.entities.item.WeaponRepository;
import se.chalmers.get_rect.game.entities.item.model.FireMagic;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.game.entities.item.model.MeleeWeapon;
import se.chalmers.get_rect.game.entities.item.model.Pistol;
import se.chalmers.get_rect.game.entities.item.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.player.PlayerDataStore;
import se.chalmers.get_rect.game.entities.player.PlayerRepository;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class IOSetup {
    private Player player;
    private PlayerRepository playerRepository;
    private PlayerDataStore dataStore;
    private IWeapon melee;
    private IWeapon ranged;

    @Before
    public void setup(){
        this.player = new Player(new RectangleFactoryAdapterStub());
        WeaponRepository weaponRepository = Mockito.mock(WeaponRepository.class);
        SwingFactory swingFactory = new SwingFactory();
        melee = new MeleeWeapon(player,"opsword", swingFactory,10,10,10,5, 10, false);
        ProjectileFactory projectileFactory = new ProjectileFactory();
        ranged = new Pistol(player, projectileFactory,10,10,5);
        player.addNewWeapon(melee);
        player.addNewWeapon(ranged);
        try {
            List<IWeapon> weaponList = new ArrayList<>();
            weaponList.add(melee);
            weaponList.add(ranged);
            Mockito.when(weaponRepository.get("data/items/weapons.json")).thenReturn(weaponList);
            Mockito.when(weaponRepository.getSingleWeapon("opsword")).thenReturn(melee);
            IWeapon firemagic = Mockito.mock(FireMagic.class);
            Mockito.when(weaponRepository.getSingleWeapon("firemagic")).thenReturn(firemagic);
            Mockito.when(weaponRepository.getSingleWeapon("pistol")).thenReturn(ranged);
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        this.playerRepository = new PlayerRepository(player, weaponRepository);
        this.dataStore = new PlayerDataStore(player.getCurrentHealth(),player.hasFoundHunch(), player.getMeleeWeapon().getType(), player.getRangedWeapon().getType());
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerRepository getPlayerRepository() {
        return playerRepository;
    }

    public PlayerDataStore getDataStore() {
        return dataStore;
    }

    public IWeapon getMelee() {
        return melee;
    }

    public IWeapon getRanged() {
        return ranged;
    }
}
