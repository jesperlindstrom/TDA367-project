package se.chalmers.get_rect.game.quests.data;


import se.chalmers.get_rect.event.IEventListener;

public interface IQuest extends IEventListener {
    int getId();
    QuestState getState();
    void interact(CompleteAction action);
    String getAcceptText();
    String getCompletionText();

    interface CompleteAction {
        void complete();
    }
}
