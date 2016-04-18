package se.chalmers.get_rect.game.gui.mainMenu;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.game.gui.AbstractGridModel;
import se.chalmers.get_rect.utilities.Point;

public class mainMenu extends AbstractGridModel {

    private boolean continueAvailable;

    public mainMenu(IGame game) {
        Point startPoint = new Point(0 ,0);

        if (game.loadAvailable()) {
            startPoint = new Point(startPoint.addY(1));
            addToMap(startPoint, () -> game.load());

            continueAvailable = true;
        }

        addToMap(startPoint, () -> game.startNew());

        addToMap(startPoint.addY(1), () -> game.exit());
    }

    public boolean getContinueAvailable() {
        return continueAvailable;
    }
}
