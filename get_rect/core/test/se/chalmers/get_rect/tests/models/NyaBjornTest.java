package se.chalmers.get_rect.tests.models;


import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.npc.model.NyaBjorn;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NyaBjornTest {

    private NyaBjorn bjorn;
    private Player player;

    @Before
    public void setup(){
        player = mock(Player.class);
        QuestManager questManager = mock(QuestManager.class);
        List<String> list = new ArrayList<>();
        list.add("Bjorn");
        bjorn = new NyaBjorn(new Point(),new RectangleFactoryAdapterStub(),list,questManager);
    }

    @Test
    public void testonInteract() throws Exception {
        bjorn.onInteract(player);
        assertEquals("Should be equal to Bjorn", "Bjorn\n", bjorn.getDialog());
    }

}
