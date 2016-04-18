package se.chalmers.get_rect.game.gui.inGameMenu;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.IGameComponent;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.gui.GridController;
import se.chalmers.get_rect.game.screens.GameScreen;

public class InGameMenuOverlay implements IGameComponent  {

    private InGameMenu model;
    private IView view;
    private GridController controller;

    public InGameMenuOverlay(GameScreen game, IInputAdapter input, CameraManager camera) {
        this.model = new InGameMenu(game);
        this.view = new InGameMenuView(camera, model);
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
}
