package se.chalmers.get_rect.states;

import se.chalmers.get_rect.IGame;

public class SplashState implements IState {
    @Override
    public void initialize(IGame game) {
        System.out.println("SplashState is initialized");
    }

    @Override
    public void show() {
        System.out.println("SplashState is displayed");
    }

    @Override
    public void draw() {

    }

    @Override
    public void update(long delta) {

    }
}