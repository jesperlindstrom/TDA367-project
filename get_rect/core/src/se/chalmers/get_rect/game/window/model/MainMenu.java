package se.chalmers.get_rect.game.window.model;

import se.chalmers.get_rect.utilities.Point;

public class MainMenu extends AbstractGridModel {

    public MainMenu(IGameControl game) {

        addToMap(0, 0, game::load);
        addToMap(0, 1, game::startNew);
        addToMap(0, 2, game::exit);

        if (!game.loadAvailable()) {
            disableButton(new Point());
        }
    }

    @Override
    public void reset() {
        setIndex(new Point(0, 0));
    }

    @Override
    public boolean isAllowingRegularInput() {
        return false;
    }
}
