package se.chalmers.get_rect.game.entities.window.controller;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.window.model.AbstractGridModel;

public class GridController implements IWindowController {
    private IInputAdapter input;
    private AbstractGridModel model;
    private IView view;

    public GridController(IInputAdapter input, AbstractGridModel model, IView view) {
        this.input = input;
        this.model = model;
        this.view = view;
    }

    @Override
    public void update(double delta) {
        if (input.isKeyJustPressed(IInputAdapter.Keys.ENTER)) {
            model.getCurrentlyMarkedButton().executeAction();
        }

        if (input.isKeyJustPressed(IInputAdapter.Keys.UPKEY)) {
            model.moveMarkUp();
        }

        if (input.isKeyJustPressed(IInputAdapter.Keys.DOWNKEY)) {
            model.moveMarkDown();
        }

        if (input.isKeyJustPressed(IInputAdapter.Keys.LEFTKEY)) {
            model.moveMarkLeft();
        }

        if (input.isKeyJustPressed(IInputAdapter.Keys.RIGHTKEY)) {
            model.moveMarkRight();
        }

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (view == null) return;

        view.draw(graphics);
    }

    @Override
    public void enteringState(Integer previousStateName) {
        model.reset();
    }

    @Override
    public void leavingState(Integer nextStateName) {

    }
}
