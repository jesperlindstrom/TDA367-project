package se.chalmers.get_rect.states;

import se.chalmers.get_rect.IGame;

public class GameState implements IState {
    @Override
    public void initialize(IGame game) {
        System.out.println("GameState is initialized");
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
