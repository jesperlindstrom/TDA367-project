package se.chalmers.get_rect.game.window.window;

import se.chalmers.get_rect.game.entities.EntityCamera;
import se.chalmers.get_rect.game.IGame;
import se.chalmers.get_rect.game.window.AbstractGridWindow;
import se.chalmers.get_rect.game.window.GridController;
import se.chalmers.get_rect.game.window.model.mainMenu;
import se.chalmers.get_rect.game.window.view.mainMenuView;


public class mainMenuWindow extends AbstractGridWindow {
    private mainMenu model;

    public mainMenuWindow(IGame game, EntityCamera camera) {
        super();
        this.model = new mainMenu(game);
        setView(new mainMenuView(model, camera));
        setController(new GridController(model, game.getInput()));
    }

    @Override
    public void enteringState(Integer previousStateName) {
        model.setup();
    }
}
