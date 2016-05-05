package se.chalmers.get_rect.game.window.model;

import se.chalmers.get_rect.game.IGame;
import se.chalmers.get_rect.game.window.AbstractGridModel;
import se.chalmers.get_rect.utilities.Point;

public class mainMenu extends AbstractGridModel {
    private boolean continueAvailable;

    public mainMenu(IGame game) {
        if (game.loadAvailable()) {
            addToMap(0, 0, game::load);
            continueAvailable = true;
        }

        addToMap(0, 1, game::startNew);
        addToMap(0, 2, game::exit);
    }

    public boolean isContinueAvailable() {
        return continueAvailable;
    }

    public void setup() {
        if (continueAvailable) {
            setIndex(new Point(0, 0));
        } else {
            setIndex(new Point(0, 1));
        }
    }
}
