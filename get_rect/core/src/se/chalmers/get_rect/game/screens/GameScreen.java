package se.chalmers.get_rect.game.screens;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.log.GameLog;
import se.chalmers.get_rect.log.GameLogEvent;
import se.chalmers.get_rect.states.IState;

import java.util.Observable;
import java.util.Observer;

public class GameScreen implements IScreen {
    public GameScreen(IGame game) {
        System.out.println("GameScreen is initialized");
    }

    @Override
    public void enteringState(String previousStateName) {
        System.out.println("Entering GameScreen");
    }

    @Override
    public void leavingState(String nextStateName) {
        System.out.println("Leaving GameScreen");
    }

    @Override
    public void update(long delta) {

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {

    }
}
