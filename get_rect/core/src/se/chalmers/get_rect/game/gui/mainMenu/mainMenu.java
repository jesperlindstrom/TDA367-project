package se.chalmers.get_rect.game.gui.mainMenu;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.game.gui.AbstractGridModel;
import se.chalmers.get_rect.utilities.Point;

public class mainMenu extends AbstractGridModel {

    private boolean continueAvailable;

    public mainMenu(IGame game) {
        Point startPoint = new Point(0 ,0);

        addToMap(0, 0, () -> game.load());
        addToMap(0, 1, () -> game.exit());
    }

    public boolean getContinueAvailable() {
        return continueAvailable;
    }
}
