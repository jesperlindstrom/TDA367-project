package se.chalmers.get_rect.event;

public interface IEventSource {
    void addListener(IEventListener o);
    void removeListener(IEventListener o);
}
