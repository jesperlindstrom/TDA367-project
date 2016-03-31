package se.chalmers.get_rect.gameLog;

import java.util.Observable;

public class GameLog extends Observable {
    /**
     * Report that something has happened
     * @param event
     */
    public void reportEvent(GameLogEvent event){
        // Mark value has changed
        setChanged();

        // Send event data to all
        notifyObservers(event);
    }
}
