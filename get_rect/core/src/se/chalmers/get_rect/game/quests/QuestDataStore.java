package se.chalmers.get_rect.game.quests;

import java.util.ArrayList;

public class QuestDataStore {
    private int id;
    private String title;
    private ArrayList<ObjectiveDataStore> objectives;
    private String acceptText;
    private String completionText;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<ObjectiveDataStore> getObjectives() {
        return objectives;
    }

    public String getAcceptText() {
        return acceptText;
    }

    public String getCompletionText() {
        return completionText;
    }
}
