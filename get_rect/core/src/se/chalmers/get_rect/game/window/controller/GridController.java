package se.chalmers.get_rect.game.window.controller;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.input.Actions;
import se.chalmers.get_rect.game.input.GameInput;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.window.model.AbstractGridModel;
import se.chalmers.get_rect.game.window.view.IWindowView;

public class GridController implements IWindowController {
    private GameInput gameInput;
    private AbstractGridModel model;
    private IWindowView view;

    public GridController(GameInput gameInput, AbstractGridModel model, IWindowView view) {
        this.gameInput = gameInput;
        this.model = model;
        this.view = view;
    }

    @Override
    public void update(double delta) {
        if (gameInput.isKeyJustPressed(Actions.CONFIRM)) {
            model.getCurrentlyMarkedButton().executeAction();
        }

        if (gameInput.isKeyJustPressed(Actions.MENU_UP)) {
            model.moveMarkUp();
        }

        if (gameInput.isKeyJustPressed(Actions.MENU_DOWN)) {
            model.moveMarkDown();
        }

        if (gameInput.isKeyJustPressed(Actions.MENU_LEFT)) {
            model.moveMarkLeft();
        }

        if (gameInput.isKeyJustPressed(Actions.MENU_RIGHT)) {
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

    public AbstractGridModel getModel() {
        return model;
    }

    @Override
    public boolean allowsRegularInput() {
        return model.isAllowingRegularInput();
    }
}
