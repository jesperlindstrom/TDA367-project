package se.chalmers.get_rect.states;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.gameLog.GameLog;
import se.chalmers.get_rect.gameLog.GameLogEvent;

import java.util.Observable;
import java.util.Observer;

public class GameState implements IState {
    @Override
    public void initialize(IGame game) {
        System.out.println("GameState is initialized");

        GameLog gameLog = game.getGameLog();

        gameLog.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                // todo: here we want instant access to the GameLogEvent object
                System.out.println("called");
            }
        });

        gameLog.reportEvent(new GameLogEvent("test1", 123));
        gameLog.reportEvent(new GameLogEvent("test2", 124));
    }

    @Override
    public void show() {
        System.out.println("GameState is displayed");
    }

    @Override
    public void draw() {

    }

    @Override
    public void update(long delta) {

    }
}
