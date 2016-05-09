package se.chalmers.get_rect.game.window;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.IView;

public class GridWindow implements IWindowController {
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
    public void enteringState(Integer previousStateName) {

    }

    @Override
    public void leavingState(Integer nextStateName) {

    }
}
