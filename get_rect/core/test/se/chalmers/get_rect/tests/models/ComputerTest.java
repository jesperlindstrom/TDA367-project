package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.worldObjects.model.Computer;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.QuestState;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ComputerTest {

    private Computer computer;
    private Player player;
    private IQuest quest;

    @Before
    public void setup() {
        quest = mock(IQuest.class);
        when(quest.getState()).thenReturn(QuestState.AVAILABLE);
        QuestManager questManager = mock(QuestManager.class);
        when(questManager.get(1)).thenReturn(quest);
        computer = new Computer(new Point(), new RectangleFactoryAdapterStub(), questManager);
        player = mock(Player.class);
    }

    @Test
    public void testAllStates(){
        assertEquals("Current state should be 0", 0, computer.getState());
        computer.update(16);
        assertEquals("Current state should be 0", 0, computer.getState());
        computer.onInteract(player);
        assertEquals("Current state should be 0", 0, computer.getState());
        when(quest.getState()).thenReturn(QuestState.IN_PROGRESS);
        computer.onInteract(player);
        assertEquals("Current state should be 1", 1, computer.getState());
        computer.update(16);
        computer.onInteract(player);
        assertEquals("Current state should be 2", 2, computer.getState());
        while (computer.getState() != 3){
            computer.update(16);
        }
        assertEquals("Current state should be 3", 3, computer.getState());



    }
}
