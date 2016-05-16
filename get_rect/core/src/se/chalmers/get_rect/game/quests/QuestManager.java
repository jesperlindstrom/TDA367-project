package se.chalmers.get_rect.game.quests;

import com.google.inject.Inject;
import se.chalmers.get_rect.event.Event;
import se.chalmers.get_rect.event.IEventListener;
import se.chalmers.get_rect.game.quests.data.IQuest;

import java.util.HashMap;
import java.util.Map;

public class QuestManager implements IEventListener {
    @Inject private QuestRepository repository;
    private Map<Integer, IQuest> quests;

    public QuestManager() {
        System.out.println("Init quest");
        quests = new HashMap<>();
        //registerQuest(0);
    }

    private void registerQuest(Integer id) {
        quests.put(id, repository.get(id));
    }

    public IQuest get(Integer id) {
        return quests.get(id);
    }

    @Override
    public void handleEvent(Event e) {
        for (Map.Entry<Integer, IQuest> quest : quests.entrySet()) {
            quest.getValue().handleEvent(e);
        }
    }
}
