package se.chalmers.get_rect.game.quests.data;


import se.chalmers.get_rect.event.IEventListener;

import java.util.List;

public interface IQuest extends IEventListener {
    int getId();
    String getTitle();
    QuestState getState();
    void interact(CompleteAction action);
    String getAcceptText();
    String getCompletionText();
    List<Objective> getObjectives();

    interface CompleteAction {
        void complete();
    }
}
