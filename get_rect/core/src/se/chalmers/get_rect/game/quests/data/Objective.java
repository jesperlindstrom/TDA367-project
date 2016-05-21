package se.chalmers.get_rect.game.quests.data;

import se.chalmers.get_rect.event.Event;

public class Objective {
    private String infoText;
    private String type;
    private String action;
    private int count;
    private int requiredCount;

    public Objective(String type, String action, int requiredCount, int count, String infoText) {
        this.type = type;
        this.action = action;
        this.count = count;
        this.requiredCount = requiredCount;
        this.infoText = infoText;
    }

    public void handleEvent(Event e) {
        if (type != null && !e.getType().equals(type))
            return;

        if (action != null && !e.getAction().equals(action))
            return;

        if (count < requiredCount) {
            count++;
        }
    }

    public boolean isCompleted() {
        return count >= requiredCount;
    }

    public String getInfoText() {
        return infoText;
    }

    public int getRequiredCount() {
        return requiredCount;
    }

    public int getCount() {
        return count;
    }

    public String getType() {
        return type;
    }

    public String getAction() {
        return action;
    }
}
