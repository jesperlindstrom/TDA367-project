package se.chalmers.get_rect.game.entities.window.controller;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.GameInput;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.window.model.AbstractGridModel;

public class GridController implements IWindowController {
    private GameInput gameInput;
    private AbstractGridModel model;
    private IView view;

    public GridController(GameInput gameInput, AbstractGridModel model, IView view) {
        this.gameInput = gameInput;
        this.model = model;
        this.view = view;
    }

    @Override
    public void update(double delta) {
        if (gameInput.isKeyJustPressed(GameInput.Actions.CONFIRM)) {
            model.getCurrentlyMarkedButton().executeAction();
        }

        if (gameInput.isKeyJustPressed(GameInput.Actions.MENU_UP)) {
            model.moveMarkUp();
        }

        if (gameInput.isKeyJustPressed(GameInput.Actions.MENU_DOWN)) {
            model.moveMarkDown();
        }

        if (gameInput.isKeyJustPressed(GameInput.Actions.MENU_LEFT)) {
            model.moveMarkLeft();
        }

        if (gameInput.isKeyJustPressed(GameInput.Actions.MENU_RIGHT)) {
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
