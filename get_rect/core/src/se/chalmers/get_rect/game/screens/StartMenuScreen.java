package se.chalmers.get_rect.game.screens;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.states.IState;

public class StartMenuScreen implements IScreen {
    public StartMenuScreen(IGame game) {
        System.out.println("StartMenuScreen is initialized");
    }

    @Override
    public void enteringState(String previousStateName) {
        System.out.println("Entering StartMenuScreen");
    }

    @Override
    public void leavingState(String nextStateName) {
        System.out.println("Leaving StartMenuScreen");
    }

    @Override
    public void update(long delta) {

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {

    }
}