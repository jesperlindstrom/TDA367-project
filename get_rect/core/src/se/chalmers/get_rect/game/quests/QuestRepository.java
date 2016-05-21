package se.chalmers.get_rect.game.quests;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.Objective;
import se.chalmers.get_rect.game.quests.data.QuestState;
import se.chalmers.get_rect.io.IOFacade;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuestRepository {
    @Inject private QuestFactory factory;

    public List<IQuest> getAll() throws FileNotFoundException {
        // todo: read this from JSON
        IOFacade<QuestDataStore> io = new IOFacade<>("data/quests/quests.json", QuestDataStore.class);
        List<QuestDataStore> questData = io.load();

        return questData.stream().map(this::makeFromDataStore).collect(Collectors.toList());
    }

    private IQuest makeFromDataStore(QuestDataStore data) {
        // Make all objective data into Objective objects
        List<Objective> objectives = data.getObjectives().stream()
                .map(obj -> makeObjectiveFromDataStore(obj, 0))
                .collect(Collectors.toList());

        // todo: save state and objective count

        return factory.make(data.getId(), data.getTitle(), QuestState.AVAILABLE, objectives, data.getAcceptText(), data.getCompletionText());
    }

    private Objective makeObjectiveFromDataStore(ObjectiveDataStore data, int current) {
        return factory.makeObjective(data.getType(), data.getAction(), data.getRequiredCount(), current, data.getInfoText());
    }
}
