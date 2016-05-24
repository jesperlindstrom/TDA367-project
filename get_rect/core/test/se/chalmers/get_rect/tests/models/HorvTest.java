package se.chalmers.get_rect.tests.models;


import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.npc.model.Horv;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class HorvTest {

    private Horv horv;
    private Player player;

    @Before
    public void setup(){
        QuestManager questManager = mock(QuestManager.class);
        List<String> list = new ArrayList<>();
        list.add("Horv");
        horv = new Horv(new Point(),new RectangleFactoryAdapterStub(), list, questManager);
        player = mock(Player.class);
    }

    @Test
    public void testInteract(){
        horv.onInteract(player);
        assertEquals("Should be equal to Horv", "Horv\n", horv.getDialog());
    }


}
