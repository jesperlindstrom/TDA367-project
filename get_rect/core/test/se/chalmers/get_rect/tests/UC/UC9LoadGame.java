package se.chalmers.get_rect.tests.UC;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.item.model.FireMagic;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.game.entities.item.model.MeleeWeapon;
import se.chalmers.get_rect.game.entities.item.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.player.PlayerDataStore;
import se.chalmers.get_rect.game.entities.player.PlayerRepository;


import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;


public class UC9LoadGame extends IOSetup {

    private Player player;
    private PlayerRepository playerRepository;
    private PlayerDataStore dataStore;
    private IWeapon newMelee;
    private IWeapon newRanged;
    private String rangedName = "firemagic";
    private String meleeName = "opsword";

    @Before
    public void setup(){
        super.setup();
        this.player = getPlayer();
        this.playerRepository = getPlayerRepository();
        this.dataStore = getDataStore();
        newRanged= new FireMagic(player, new ProjectileFactory(),10,10,10);
        newMelee = new MeleeWeapon(player,meleeName,new SwingFactory(),10,10,10,10,10,false);
    }

    @Test
    public void testLoadGame(){
        assertTrue("Should be true because file needs to exists" , playerRepository.hasFilePath() && playerRepository.hasFile());
        try {
            playerRepository.reset();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        int oldHelath = player.getCurrentHealth();
        boolean oldHasFoundHunch = player.hasFoundHunch();
        IWeapon oldMelee = getMelee();
        IWeapon oldRanged = getRanged();
        player.addNewWeapon(newMelee);
        player.addNewWeapon(newRanged);
        player.setHasFoundHunch(true);
        player.takeDamage(1);
        try {
            playerRepository.save();
            playerRepository.load();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        assertTrue("Oldhealth should be more then new", oldHelath > player.getCurrentHealth());
        assertTrue("Player should have found hunchen and old value is false", !oldHasFoundHunch && player.hasFoundHunch());
        assertTrue("Should be true because player got new melee weapon", player.getMeleeWeapon().getType().equals(meleeName));
        assertFalse("Should be false, new weapon not equals old", oldMelee.equals(newMelee));
        assertFalse("Should be false, new weapon not equals old", oldRanged.equals(newRanged));
        assertTrue("Should be true because player got new melee weapon", newRanged.getType().equals(rangedName));
    }





}
