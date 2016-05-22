package se.chalmers.get_rect.event;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class EventSource implements IEventSource {
    private Set<IEventListener> listeners = new CopyOnWriteArraySet<>();

    public void triggerEvent(String type, String message) {
        Event event = new Event(type, message);

        for (IEventListener listener : listeners) {
            listener.handleEvent(event);
        }
    }

    @Override
    public void addListener(IEventListener o) {
        listeners.add(o);
    }

    @Override
    public void removeListener(IEventListener o) {
        listeners.remove(o);
    }
}
