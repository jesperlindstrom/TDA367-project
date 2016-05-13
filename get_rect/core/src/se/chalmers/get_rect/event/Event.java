package se.chalmers.get_rect.event;

public class Event {
    private final String type;
    private final String action;
    private final Object data;

    public Event(String type, String action, Object data) {
        this.type = type;
        this.action = action;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public String getAction() {
        return action;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Event:" + type + " -> " + action + " (" + data + ")";
    }
}
