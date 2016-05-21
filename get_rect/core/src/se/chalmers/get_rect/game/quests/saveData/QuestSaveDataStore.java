package se.chalmers.get_rect.game.quests.saveData;

import se.chalmers.get_rect.game.quests.data.QuestState;

import java.util.ArrayList;

public class QuestSaveDataStore {
    private int id;
    private String status;
    private ArrayList<ObjectiveSaveDataStore> objectives;

    public QuestSaveDataStore(int id, QuestState status, ArrayList<ObjectiveSaveDataStore> objectives) {
        this.id = id;
        this.status = status.name();
        this.objectives = objectives;
    }

    public int getId() {
        return id;
    }

    public QuestState getStatus() {
        return QuestState.valueOf(status);
    }

    public ArrayList<ObjectiveSaveDataStore> getObjectives() {
        return objectives;
    }
}
