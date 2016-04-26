package se.chalmers.get_rect.game.gui.mainMenu;

import se.chalmers.get_rect.game.IGame;
import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.game.gui.AbstractGridWindow;
import se.chalmers.get_rect.game.gui.GridController;


public class mainMenuWindow extends AbstractGridWindow {
    private mainMenu model;

    public mainMenuWindow(IGame game, ICameraAdapter camera) {
        this.model = new mainMenu(game);
        setView(new mainMenuView(model, camera));
        setController(new GridController(model, game.getInput()));
    }

    @Override
    public void enteringState(String previousStateName) {
        model.setup();
    }
}
