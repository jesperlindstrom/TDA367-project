package se.chalmers.get_rect.game.window.controller;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.window.model.AbstractGridModel;
import se.chalmers.get_rect.states.IState;

public interface IWindowController extends IState {
    void update(double delta);
    void draw(IGraphicsAdapter graphics);
    AbstractGridModel getModel();
    boolean allowsRegularInput();
}
