package se.chalmers.get_rect.game.quests;

import se.chalmers.get_rect.event.Event;
import se.chalmers.get_rect.event.IEventListener;

public class QuestManager implements IEventListener {
    public QuestManager() {
        System.out.println("Init quest");
    }

    @Override
    public void handleEvent(Event e) {
        System.out.println(e);
    }
}
