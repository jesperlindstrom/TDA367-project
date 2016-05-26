package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.chalmers.get_rect.event.Event;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.Objective;
import se.chalmers.get_rect.game.quests.data.Quest;
import se.chalmers.get_rect.game.quests.data.QuestState;
import se.chalmers.get_rect.utilities.Point;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class QuestObjectiveTest {
    @Test
    public void testHandleEvent() {
        Objective objective = new Objective("type", "action", 3, 0, "");
        Event e = new Event("type", "action");

        for (int i = 1; i <= 2; i++) {
            objective.handleEvent(e);
            assertEquals("Should be " + i, objective.getCount(), i);
            assertFalse("Should not be completed", objective.isCompleted());
        }

        objective.handleEvent(e);
        assertEquals("Should be 3", objective.getCount(), 3);
        assertTrue("Should be completed", objective.isCompleted());

        objective.handleEvent(e);
        assertEquals("Should still be 3", objective.getCount(), 3);
        assertTrue("Should be completed", objective.isCompleted());
    }

    @Test
    public void testHandleInvalidEvent() {
        Objective objective = new Objective("type", "action", 3, 0, "");
        Event e = new Event("type", "not action");

        objective.handleEvent(e);
        assertEquals("Should be 0", objective.getCount(), 0);
        assertFalse("Should be completed", objective.isCompleted());

        Event e2 = new Event("not type", "action");

        objective.handleEvent(e2);
        assertEquals("Should be 0", objective.getCount(), 0);
        assertFalse("Should be completed", objective.isCompleted());
    }
}
