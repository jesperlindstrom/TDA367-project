package se.chalmers.get_rect.game.window.controller;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.window.model.SplashModel;
import se.chalmers.get_rect.game.window.view.IWindowView;

public class SplashController implements IWindowController {
    private IWindowView view;
    private SplashModel model;

    public SplashController(SplashModel model, IWindowView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void update(double delta) {
        model.update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        view.draw(graphics);
    }

    @Override
    public void enteringState(Integer previousStateName) {
        model.preload();
    }

    @Override
    public void leavingState(Integer nextStateName) {

    }
}
