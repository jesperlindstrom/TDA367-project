package se.chalmers.get_rect.tests.models;


import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.npc.model.NyaBjorn;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.Quest;
import se.chalmers.get_rect.game.quests.data.QuestState;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NyaBjornTest {
    private QuestManager questManager;
    private Player player;
    private List<String> list;
    private NyaBjorn bjorn;

    @Before
    public void setup(){
        player = mock(Player.class);
        questManager = mock(QuestManager.class);
        list = new ArrayList<>();
        list.add("nyaBjorn");
        bjorn = new NyaBjorn(new Point(),new RectangleFactoryAdapterStub(), list, questManager);

    }

    @Test
    public void testOnInteract() throws Exception {
        assertFalse(bjorn.isShowing());
        IQuest quest = new Quest(3, "", QuestState.COMPLETABLE, null, "", "");
        when(questManager.get(3)).thenReturn(quest);
        when(player.getMaxHealth()).thenReturn(10);

        bjorn.onInteract(player);
        bjorn.onInteract(player);
        assertTrue(bjorn.isShowing());
    }

    @Test
    public void testQuestCompletion() {
        assertFalse(bjorn.isShowingFull());
        bjorn.onQuestCompletion(mock(IModel.class));
        assertFalse(bjorn.isShowingFull());
        bjorn.onQuestCompletion(player);
        assertTrue(bjorn.isShowingFull());
    }

}
