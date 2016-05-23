package se.chalmers.get_rect.game.window.model;

import se.chalmers.get_rect.utilities.Point;

public class MainMenu extends AbstractGridModel {
    private boolean continueAvailable;

    public MainMenu(IGameControl game) {
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

    @Override
    public void reset() {
        int y = continueAvailable ? 0 : 1;
        setIndex(new Point(0, y));
    }

    @Override
    public boolean isAllowingRegularInput() {
        return false;
    }
}
