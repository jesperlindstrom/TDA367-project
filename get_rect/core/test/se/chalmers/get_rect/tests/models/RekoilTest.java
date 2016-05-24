package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.npc.model.Rekoil;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class RekoilTest {

    private Rekoil rekoil;
    private Player player;

    @Before
    public void setup(){
        player = mock(Player.class);
        List<String> list = new ArrayList<>();
        QuestManager questManager = mock(QuestManager.class);
        list.add("Rekoil");
        rekoil = new Rekoil(new Point(),new RectangleFactoryAdapterStub(),list, questManager);
    }

    @Test
    public void testonInteract() throws Exception {
        rekoil.onInteract(player);
        assertEquals("Should be equal to Rekoil", "Rekoil\n", rekoil.getDialog());
    }
}