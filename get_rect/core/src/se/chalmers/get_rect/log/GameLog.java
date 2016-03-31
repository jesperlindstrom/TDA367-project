package se.chalmers.get_rect.log;

import java.util.Observable;

public class GameLog extends Observable {
    public static class GameEvent {
        // todo: implement
        // todo: What info do we need to send?
        public GameEvent(String type, int id) {

        }
    }

    /**
     * Report that something has happened
     * @param reportValue
     */
    public void reportEvent(GameEvent event){
        // Mark value has changed
        setChanged();

        // Send event data to all
        notifyObservers(event);
    }
}
