package se.chalmers.get_rect.game.quests.saveData;

public class ObjectiveSaveDataStore {
    private String type;
    private String action;
    private int count;

    public ObjectiveSaveDataStore(String type, String action, int count) {
        this.type = type;
        this.action = action;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public String getAction() {
        return action;
    }

    public int getCount() {
        return count;
    }
}
