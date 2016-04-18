package se.chalmers.get_rect.game.gui.mainMenu;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.game.gui.AbstractGridOverlay;
import se.chalmers.get_rect.game.gui.GridController;


public class mainMenuOverlay extends AbstractGridOverlay {
    private mainMenu model;

    public mainMenuOverlay(IGame game, ICameraAdapter camera) {
        this.model = new mainMenu(game);
        setView(new mainMenuView(model, camera));
        setController(new GridController(model, game.getInput()));
    }

    @Override
    public void enteringState(String previousStateName) {
        model.setup();
    }
}
