package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.worldObjects.model.SandCastle;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.Objective;
import se.chalmers.get_rect.game.quests.data.QuestState;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SandcastleTest {

    private Player player;

    @Before
    public void setup(){
        this.player = Mockito.mock(Player.class);
    }

    @Test
    public void testInteractWithNull(){
        QuestManager questManager = Mockito.mock(QuestManager.class);
        Mockito.when(questManager.get(0)).thenReturn(null);
        SandCastle sandCastle = new SandCastle(new Point(), new RectangleFactoryAdapterStub(), questManager);
        assertFalse("Should be fasle, no one interacted", sandCastle.getInteractedWith());
        sandCastle.onInteract(player);
        assertTrue("Should be true, player interacted", sandCastle.getInteractedWith());
    }

    @Test
    public void testInteractWithCompleted(){
        IQuest quest = Mockito.mock(IQuest.class);
        Mockito.when(quest.getState()).thenReturn(QuestState.IN_PROGRESS);
        Objective objective = new Objective("sandCastle", "interacted", 1,1,"Done");
        List<Objective> list = new ArrayList<>();
        list.add(objective);
        Mockito.when(quest.getObjectives()).thenReturn(list);
        QuestManager questManager = Mockito.mock(QuestManager.class);
        Mockito.when(questManager.get(0)).thenReturn(quest);
        SandCastle sandCastle = new SandCastle(new Point(), new RectangleFactoryAdapterStub(), questManager);

    }
}
