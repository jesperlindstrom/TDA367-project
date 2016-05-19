package se.chalmers.get_rect.game.quests;

import se.chalmers.get_rect.event.Event;
import se.chalmers.get_rect.event.IEventListener;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.QuestState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuestManager implements IEventListener {
    private QuestRepository repository;
    private List<IQuest> quests;

    public QuestManager(QuestRepository repository) {
        this.repository = repository;
        System.out.println("Init quest");
        quests = new ArrayList<>();
        System.out.println(quests);
        System.out.println(repository);
        registerQuest(0);
    }

    private void registerQuest(int id) {
        quests.add(repository.get(id));
    }

    public IQuest get(int id) {
        for (IQuest quest : quests) {
            if (quest.getId() == id) {
                return quest;
            }
        }

        return null;
    }

    public List<IQuest> getAllActive() {
        return quests.stream()
            .filter((q) -> q.getState().equals(QuestState.IN_PROGRESS) || q.getState().equals(QuestState.COMPLETABLE))
            .collect(Collectors.toList());
    }

    @Override
    public void handleEvent(Event e) {
        for (IQuest quest : quests) {
            quest.handleEvent(e);
        }
    }
}
