package se.chalmers.get_rect.game.window.mainMenu;

import se.chalmers.get_rect.game.IGame;
import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.game.window.AbstractGridWindow;
import se.chalmers.get_rect.game.window.GridController;


public class mainMenuWindow extends AbstractGridWindow {
    private mainMenu model;

    public mainMenuWindow(IGame game, ICameraAdapter camera) {
        this.model = new mainMenu(game);
        setView(new mainMenuView(model, camera));
        setController(new GridController(model, game.getInput()));
    }

    @Override
    public void enteringState(Integer previousStateName) {
        model.setup();
    }
}
