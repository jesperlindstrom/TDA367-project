package se.chalmers.get_rect.game.window.model;

import se.chalmers.get_rect.game.IGame;
import se.chalmers.get_rect.game.window.AbstractGridModel;
import se.chalmers.get_rect.game.screens.GameScreen;

public class inGameMenu extends AbstractGridModel {
    public inGameMenu(IGame game) {
        super();
        addToMap(0, 0, game::resume);
        addToMap(0, 1, game::exit);
    }
}
