package se.chalmers.get_rect.states;

import se.chalmers.get_rect.IGame;

public class StartMenuState implements IState {
    @Override
    public void initialize(IGame game) {
        System.out.println("StartMenuState is initialized");
    }

    @Override
    public void show() {
        System.out.println("StartMenuState is displayed");
    }

    @Override
    public void draw() {

    }

    @Override
    public void update(long delta) {

    }
}