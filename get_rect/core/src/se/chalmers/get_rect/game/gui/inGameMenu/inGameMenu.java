package se.chalmers.get_rect.game.gui.inGameMenu;

import se.chalmers.get_rect.game.gui.AbstractGridModel;
import se.chalmers.get_rect.game.screens.GameScreen;

public class inGameMenu extends AbstractGridModel {
    public inGameMenu(GameScreen game) {
        super();
        addToMap(0, 0, game::resume);
        addToMap(0, 1, game::exit);
    }
}
