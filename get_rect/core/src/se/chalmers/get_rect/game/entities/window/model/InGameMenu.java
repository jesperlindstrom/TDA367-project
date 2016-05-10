package se.chalmers.get_rect.game.entities.window.model;

import se.chalmers.get_rect.game.IGame;
import se.chalmers.get_rect.utilities.Point;

public class InGameMenu extends AbstractGridModel {
    public InGameMenu(IGame game) {
        super();
        addToMap(0, 0, game::resume);
        addToMap(0, 1, game::exit);
    }

    @Override
    public void reset() {
        setIndex(new Point(0, 0));
    }
}
