package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;

/**
 * Created by simsund on 2016-04-06.
 */
public class MenuScene implements IScene {

    private IInputAdapter input;

    public MenuScene(IInputAdapter input) {
        this.input = input;
    }

    @Override
    public void update(long delta) {

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {

    }

    @Override
    public void enteringState(String previousStateName) {

    }

    @Override
    public void leavingState(String nextStateName) {

    }
}
