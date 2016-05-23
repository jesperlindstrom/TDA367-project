package se.chalmers.get_rect.event;

public class Event {
    private final String type;
    private final String action;

    public Event(String type, String action) {
        this.type = type;
        this.action = action;
    }

    public String getType() {
        return type;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "Event:" + type + " -> " + action;
    }
}
