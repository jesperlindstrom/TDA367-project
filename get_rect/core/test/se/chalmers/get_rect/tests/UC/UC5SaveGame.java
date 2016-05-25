package se.chalmers.get_rect.tests.UC;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.player.PlayerDataStore;
import se.chalmers.get_rect.game.entities.player.PlayerRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;


public class UC5SaveGame extends IOSetup {

    private Player player;
    private PlayerRepository playerRepository;
    private PlayerDataStore dataStore;

    @Before
    public void setup(){
        super.setup();
        this.player = getPlayer();
        this.playerRepository = getPlayerRepository();
        this.dataStore = getDataStore();
    }

    @Test
    public void testSaveGame(){
        try {
            playerRepository.save();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        assertTrue("Should be true because save function done", playerRepository.hasFile());
    }

    @Test
    public void testFileNotExists(){
        if(playerRepository.hasFile()){
            File file = new File("data/savedData/");
            String[]entries = file.list();
            for(String s: entries){
                File currentFile = new File(file.getPath(),s);
                currentFile.delete();
            }

            file.delete();
            assertFalse("Should be false because repo is deleted" , playerRepository.hasFile());
        }

        try {
            playerRepository.save();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        assertTrue("Should be true because new save done", playerRepository.hasFile());
    }

}
