package se.chalmers.get_rect.game.quests.data;

import se.chalmers.get_rect.event.Event;

public class Objective {
    private String type;
    private String action;
    private int count;
    private int requiredCount;

    public Objective(String type, String action, int requiredCount, int count) {
        this.type = type;
        this.action = action;
        this.count = count;
        this.requiredCount = requiredCount;
    }

    public void handleEvent(Event e) {
        if (type != null && !e.getType().equals(type))
            return;

        if (action != null && !e.getAction().equals(action))
            return;

        count++;
    }

    public boolean isCompleted() {
        return count == requiredCount;
    }
}