package se.chalmers.get_rect.game.screens;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.states.IState;

public class SplashScreen implements IScreen {
    public SplashScreen(IGame game) {
        System.out.println("SplashScreen is initialized");
    }

    @Override
    public void enteringState(String previousStateName) {
        System.out.println("Entering SplashScreen");
    }

    @Override
    public void leavingState(String nextStateName) {
        System.out.println("Leaving SplashScreen");
    }

    @Override
    public void update(long delta) {

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {

    }
}