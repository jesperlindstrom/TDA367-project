package se.chalmers.get_rect.log;


import java.util.Observable;

public class GameLog extends Observable {
    private String observedVale;

    public GameLog(String observedVale){
        this.observedVale = observedVale;
    }

    public void setObservedVale(String newObservedValue){

        if(!observedVale.equals(newObservedValue)){
            //Sets new value to old value
            observedVale = newObservedValue;
            //Mark value has changed
            setChanged();
            //Notify all listeners what has happened.
            notifyObservers(observedVale);
        }
    }


}
