package se.chalmers.get_rect.game.quests;

public class ObjectiveDataStore {
    private String type;
    private String action;
    private int requiredCount;
    private String infoText;

    public String getType() {
        return type;
    }

    public String getAction() {
        return action;
    }

    public int getRequiredCount() {
        return requiredCount;
    }

    public String getInfoText() {
        return infoText;
    }
}
