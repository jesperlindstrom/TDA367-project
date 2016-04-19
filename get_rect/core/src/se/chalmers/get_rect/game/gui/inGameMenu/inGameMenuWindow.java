package se.chalmers.get_rect.game.gui.inGameMenu;

import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.gui.AbstractGridWindow;
import se.chalmers.get_rect.game.gui.GridController;
import se.chalmers.get_rect.game.screens.GameScreen;
import se.chalmers.get_rect.utilities.Point;

public class inGameMenuWindow extends AbstractGridWindow {
    private inGameMenu model;

    public inGameMenuWindow(GameScreen game, IInputAdapter input, CameraManager camera) {
        this.model = new inGameMenu(game);
        setView(new inGameMenuView(camera, model));
        setController(new GridController(model, input));
    }

    @Override
    public void enteringState(String previousStateName) {
        model.setIndex(new Point(0, 0));
    }

}
