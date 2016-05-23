package se.chalmers.get_rect.tests.UC;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.item.WeaponRepository;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.game.entities.item.model.MeleeWeapon;
import se.chalmers.get_rect.game.entities.item.model.Pistol;
import se.chalmers.get_rect.game.entities.item.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.player.PlayerDataStore;
import se.chalmers.get_rect.game.entities.player.PlayerRepository;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;


public class UC5SaveGame {

    private Player player;
    private PlayerRepository playerRepository;
    private PlayerDataStore dataStore;

    @Before
    public void setup(){
        this.player = new Player(new RectangleFactoryAdapterStub());
        this.playerRepository = new PlayerRepository(player, new WeaponRepository());
        SwingFactory swingFactory = new SwingFactory();
        IWeapon melee = new MeleeWeapon(player,"melee", swingFactory,10,10,10,5);
        ProjectileFactory projectileFactory = new ProjectileFactory();
        IWeapon ranged = new Pistol(player, projectileFactory,10,10,5);
        player.addNewWeapon(melee);
        player.addNewWeapon(ranged);
        this.dataStore = new PlayerDataStore(player.getCurrentHealth(),player.hasFoundHunch(), player.getMeleeWeapon().getType(), player.getRangedWeapon().getType());
    }

    @Test
    public void testSaveGame(){
        try {
            playerRepository.reset();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        assertFalse("Should be false because file not created",playerRepository.hasFile());
        try {
            playerRepository.save();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        assertTrue("Should be true because save function done", playerRepository.hasFile());
    }

    @Test
    public void testFileNotExists(){
        if(playerRepository.hasFilePath()){
            File file = new File("data/savedData/");
            String[]entries = file.list();
            for(String s: entries){
                File currentFile = new File(file.getPath(),s);
                currentFile.delete();
            }

            file.delete();
            assertFalse("Should be false because repo is deleted" , playerRepository.hasFilePath());
        }

        try {
            playerRepository.save();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        assertTrue("Should be true because new save done", playerRepository.hasFile());
        assertTrue("Should be true because new save done", playerRepository.hasFilePath());

    }

}
