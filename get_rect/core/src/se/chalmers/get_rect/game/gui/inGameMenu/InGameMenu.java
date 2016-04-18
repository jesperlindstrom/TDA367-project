package se.chalmers.get_rect.game.gui.inGameMenu;

import se.chalmers.get_rect.game.gui.AbstractGridModel;
import se.chalmers.get_rect.game.screens.GameScreen;

public class InGameMenu extends AbstractGridModel {

    public InGameMenu(GameScreen game) {
        super();
        super.addToMap(0, 0, () -> game.resume());
        super.addToMap(0, 1, () -> game.exit());
    }

}
