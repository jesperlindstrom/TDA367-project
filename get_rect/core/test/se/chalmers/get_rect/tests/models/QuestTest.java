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

public class QuestTest {
    private List<Objective> objectives;
    private Objective obj;

    @Before
    public void setup() {
        objectives = new ArrayList<>();

        obj = mock(Objective.class);

        objectives.add(obj);
        objectives.add(obj);
    }

    @Test
    public void testGetters() {
        IQuest quest = new Quest(123, "title", QuestState.AVAILABLE, objectives, "accept", "completion");

        assertEquals("ID should be correct", quest.getId(), 123);
        assertEquals("Title should be correct", quest.getTitle(), "title");
        assertEquals("State should be available", quest.getState(), QuestState.AVAILABLE);
        assertEquals("Objectives should be correct", quest.getObjectives(), objectives);
        assertEquals("Accept text should be correct", quest.getAcceptText(), "accept");
        assertEquals("Completion text should be correct", quest.getCompletionText(), "completion");
    }

    @Test
    public void testAccept() {
        IQuest quest = new Quest(0, "", QuestState.AVAILABLE, objectives, "", "");
        IQuest.CompleteAction action = mock(Quest.CompleteAction.class);
        quest.interact(action);

        verify(action, never()).complete();
        assertEquals("Quest should be in progress", quest.getState(), QuestState.IN_PROGRESS);
    }

    @Test
    public void testComplete() {
        IQuest quest = new Quest(0, "", QuestState.COMPLETABLE, objectives, "", "");
        IQuest.CompleteAction action = mock(Quest.CompleteAction.class);
        quest.interact(action);

        verify(action, times(1)).complete();
        assertEquals("Quest should be completed", quest.getState(), QuestState.COMPLETED);
    }

    @Test
    public void testNotInProgress() {
        IQuest quest = new Quest(0, "", QuestState.AVAILABLE, objectives, "", "");
        Event e = mock(Event.class);
        quest.handleEvent(e);

        verify(obj, never()).handleEvent(e);
        assertEquals(quest.getState(), QuestState.AVAILABLE);
    }

    @Test
    public void testHandleEvents() {
        IQuest quest = new Quest(0, "", QuestState.IN_PROGRESS, objectives, "", "");

        Event e = mock(Event.class);
        quest.handleEvent(e);

        verify(obj, times(2)).handleEvent(e);
        assertEquals(quest.getState(), QuestState.IN_PROGRESS);

        when(obj.isCompleted()).thenReturn(true);
        quest.handleEvent(e);
        assertEquals(quest.getState(), QuestState.COMPLETABLE);
    }
}
