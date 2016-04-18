package se.chalmers.get_rect.game.gui.inGameMenu;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.gui.GridController;
import se.chalmers.get_rect.game.gui.IOverlay;
import se.chalmers.get_rect.game.screens.GameScreen;
import se.chalmers.get_rect.utilities.Point;

public class inGameMenuOverlay implements IOverlay {

    private inGameMenu model;
    private IView view;
    private GridController controller;

    public inGameMenuOverlay(GameScreen game, IInputAdapter input, CameraManager camera) {
        this.model = new inGameMenu(game);
        this.view = new inGameMenuView(camera, model);
        this.controller = new GridController(model, input);
    }



    @Override
    public void update(double delta) {
        controller.update();
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        view.draw(graphics);
    }

    @Override
    public void enteringState(String previousStateName) {
        model.setIndex(new Point(0, 0));
    }

    @Override
    public void leavingState(String nextStateName) {

    }
}
