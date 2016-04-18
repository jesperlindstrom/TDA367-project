package se.chalmers.get_rect.game.gui.mainMenu;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.gui.AbstractGridOverlay;
import se.chalmers.get_rect.game.gui.GridController;
import se.chalmers.get_rect.game.gui.inGameMenu.inGameMenuView;
import se.chalmers.get_rect.utilities.Point;


public class mainMenuOverlay extends AbstractGridOverlay {
    private mainMenu model;

    public mainMenuOverlay(IGame game, IInputAdapter input, CameraManager camera) {
        this.model = new mainMenu(game);
        setView(new inGameMenuView(camera, model));
        setController(new GridController(model, input));
    }

    @Override
    public void enteringState(String previousStateName) {
        model.setIndex(new Point(0, 0));
    }
}
