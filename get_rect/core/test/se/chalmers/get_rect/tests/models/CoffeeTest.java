package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.worldObjects.model.Coffee;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.Objective;
import se.chalmers.get_rect.game.quests.data.QuestState;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoffeeTest {
    private Player player;
    private Coffee coffee;

    @Before
    public void setup(){
        player = mock(Player.class);
        QuestManager questManager = mock(QuestManager.class);
        when(questManager.get(4)).thenReturn(null);
        coffee = new Coffee(new Point(), new RectangleFactoryAdapterStub(), questManager);
    }

    @Test
    public void testInteractWithNull(){
        assertFalse("Should be false, no one interacted", coffee.getInteractedWith());
        coffee.onInteract(player);
        assertTrue("Should be true, player interacted", coffee.getInteractedWith());
    }

    @Test
    public void testInteractWithCompleted(){
        IQuest quest = mock(IQuest.class);
        when(quest.getState()).thenReturn(QuestState.IN_PROGRESS);
        Objective objective = new Objective("coffee", "interacted", 1, 1, "Done");
        List<Objective> list = new ArrayList<>();
        list.add(objective);
        when(quest.getObjectives()).thenReturn(list);
        QuestManager questManager = mock(QuestManager.class);
        when(questManager.get(4)).thenReturn(quest);
        Coffee coffee = new Coffee(new Point(), new RectangleFactoryAdapterStub(), questManager);
        assertTrue("Coffee was interacted with", coffee.getInteractedWith());
        coffee.onInteract(player);
        assertTrue("Coffee was interacted with", coffee.getInteractedWith());
    }
}
