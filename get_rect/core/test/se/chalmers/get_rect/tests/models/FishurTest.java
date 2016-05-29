package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.npc.model.Fishur;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class FishurTest {
    private Fishur fishur;
    private Player player;

    @Before
    public void setup(){
        QuestManager questManager = mock(QuestManager.class);
        List<String> list = new ArrayList<>();
        list.add("Fishur");
        fishur = new Fishur(new Point(),new RectangleFactoryAdapterStub(), list, questManager);
        player = mock(Player.class);
    }

    @Test
    public void testInteract(){
        fishur.onInteract(player);
        assertEquals("Should be equal to Fishur", "Fishur\n", fishur.getDialog());
        assertTrue("isInteracted should be true", fishur.isInteractedWith());
    }
}
