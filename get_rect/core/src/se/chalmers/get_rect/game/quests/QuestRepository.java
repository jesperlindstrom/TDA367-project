package se.chalmers.get_rect.game.quests;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.quests.data.*;
import se.chalmers.get_rect.game.quests.saveData.ObjectiveSaveDataStore;
import se.chalmers.get_rect.game.quests.saveData.QuestSaveDataStore;
import se.chalmers.get_rect.io.IOFacade;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QuestRepository {
    private static final String LOAD_FILE = "data/quests/quests.json";
    private static final String SAVE_PATH = "data/savedData/";
    private static final String SAVE_FILE = SAVE_PATH + "questsSavedData.json";
    @Inject private QuestFactory factory;
    private boolean noSaveFile = false;

    public void save(List<IQuest> quests) throws IOException {
        IOFacade<QuestSaveDataStore> io = new IOFacade<>(SAVE_FILE, QuestSaveDataStore.class);

        if (!hasSavePath()){
            File theFile = new File(SAVE_PATH);
            boolean tmp = theFile.mkdirs();

            if (!tmp){
                throw new RuntimeException("Failed to create save path");
            }
        }

        List<QuestSaveDataStore> saveData = new ArrayList<>();

        for (IQuest quest : quests) {
            saveData.add(saveToDataStore(quest));
        }

        io.save(saveData);
    }

    private QuestSaveDataStore saveToDataStore(IQuest quest) {
        ArrayList<ObjectiveSaveDataStore> objectives = new ArrayList<>();

        for (Objective o : quest.getObjectives()) {
            objectives.add(new ObjectiveSaveDataStore(o.getType(), o.getAction(), o.getCount()));
        }

        return new QuestSaveDataStore(quest.getId(), quest.getState(), objectives);
    }

    private boolean hasSavePath() {
        return new File(SAVE_PATH).isDirectory();
    }

    public List<IQuest> getAll() throws FileNotFoundException {
        IOFacade<QuestDataStore> io = new IOFacade<>(LOAD_FILE, QuestDataStore.class);
        List<QuestDataStore> questData = io.load();

        Map<Integer, QuestSaveDataStore> saveData = noSaveFile ? new HashMap<>() : getSaveData();

        System.out.println(saveData);

        return questData.stream().map((data) -> makeFromDataStore(data, saveData.get(data.getId()))).collect(Collectors.toList());
    }

    public void reset() {
        noSaveFile = true;
    }

    private Map<Integer, QuestSaveDataStore> getSaveData() {
        Map<Integer, QuestSaveDataStore> saveData = new HashMap<>();

        IOFacade<QuestSaveDataStore> io = new IOFacade<>(SAVE_FILE, QuestSaveDataStore.class);

        try {
            List<QuestSaveDataStore> questData = io.load();

            for (QuestSaveDataStore q : questData) {
                saveData.put(q.getId(), q);
            }
        } catch (FileNotFoundException e) {
            // Do nothing
            // No save file means we start over
        }

        return saveData;
    }

    private IQuest makeFromDataStore(QuestDataStore data, QuestSaveDataStore save) {
        // Make all objective data into Objective objects
        List<Objective> objectives = data.getObjectives().stream()
                .map(obj -> makeObjectiveFromDataStore(obj, save))
                .collect(Collectors.toList());

        QuestState questState = save == null ? QuestState.AVAILABLE : save.getStatus();

        return factory.make(data.getId(), data.getTitle(), questState, objectives, data.getAcceptText(), data.getCompletionText());
    }

    private Objective makeObjectiveFromDataStore(ObjectiveDataStore data, QuestSaveDataStore saveData) {
        int current = getObjectiveCurrentValue(data, saveData);
        return factory.makeObjective(data.getType(), data.getAction(), data.getRequiredCount(), current, data.getInfoText());
    }

    private int getObjectiveCurrentValue(ObjectiveDataStore data, QuestSaveDataStore questSave) {
        if (questSave != null) {
            List<ObjectiveSaveDataStore> saveData = questSave.getObjectives();

            for (ObjectiveSaveDataStore save : saveData) {
                if (data.getType().equals(save.getType()) && data.getAction().equals(save.getAction())) {
                    return save.getCount();
                }
            }
        }

        return 0;
    }
}