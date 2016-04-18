package se.chalmers.get_rect.game.gui;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.IView;

public class AbstractGridOverlay implements IOverlay {
    private IController controller;
    private IView view;

    protected void setController(IController controller) {
        this.controller = controller;
    }

    protected void setView(IView view) {
        this.view = view;
    }

    @Override
    public void update(double delta) {
        if (controller == null) return;

        controller.update();
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (view == null) return;

        view.draw(graphics);
    }

    @Override
    public void enteringState(String previousStateName) {

    }

    @Override
    public void leavingState(String nextStateName) {

    }
}
