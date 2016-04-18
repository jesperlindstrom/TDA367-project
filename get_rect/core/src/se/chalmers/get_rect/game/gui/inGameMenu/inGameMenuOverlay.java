package se.chalmers.get_rect.game.gui.inGameMenu;

import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.gui.AbstractGridOverlay;
import se.chalmers.get_rect.game.gui.GridController;
import se.chalmers.get_rect.game.screens.GameScreen;
import se.chalmers.get_rect.utilities.Point;

public class inGameMenuOverlay extends AbstractGridOverlay {

    private inGameMenu model;

    public inGameMenuOverlay(GameScreen game, IInputAdapter input, CameraManager camera) {
        this.model = new inGameMenu(game);
        setView(new inGameMenuView(camera, model));
        setController(new GridController(model, input));
    }

    @Override
    public void enteringState(String previousStateName) {
        model.setIndex(new Point(0, 0));
    }

}
