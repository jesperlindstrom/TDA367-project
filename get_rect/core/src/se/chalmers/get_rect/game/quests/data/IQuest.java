package se.chalmers.get_rect.game.quests.data;


import se.chalmers.get_rect.event.IEventListener;

public interface IQuest extends IEventListener {
    QuestState getState();
}
