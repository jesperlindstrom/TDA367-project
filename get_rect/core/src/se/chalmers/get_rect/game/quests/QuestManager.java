package se.chalmers.get_rect.game.quests;

import se.chalmers.get_rect.event.Event;
import se.chalmers.get_rect.event.IEventListener;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.QuestState;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuestManager implements IEventListener {
    private List<IQuest> quests;

    public QuestManager(QuestRepository repository) {
        quests = new ArrayList<>();

        try {
            quests.addAll(repository.getAll());
        } catch(FileNotFoundException e) {
            // todo: handle this :-)
            System.out.println("Quests: " + e.getMessage());
        }
    }

    public IQuest get(int id) {
        for (IQuest quest : quests) {
            if (quest.getId() == id) {
                return quest;
            }
        }

        return null;
    }

    public List<IQuest> getAll() {
        return quests;
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
