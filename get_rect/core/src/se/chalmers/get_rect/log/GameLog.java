package se.chalmers.get_rect.log;


import java.util.Observable;

public class GameLog extends Observable {
    private String observValue;

    /**
     * Initialize a new GameLog
     */
    public GameLog(){
        this.observValue = null;
    }

    /**
     * Setter for obsverable value
     * Notify if value changed
     * @param reportValue
     */
    public void reportEvent(String reportValue){

        if(!observValue.equals(reportValue)){
            //Sets new value to old value
            observValue = reportValue;
            //Mark value has changed
            setChanged();
            //Notify all listeners what has happened.
            notifyObservers(observValue);
        }
    }


}
