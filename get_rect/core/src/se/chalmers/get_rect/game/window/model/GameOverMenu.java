package se.chalmers.get_rect.game.window.model;

import se.chalmers.get_rect.utilities.Point;

public class GameOverMenu extends AbstractGridModel {

    public GameOverMenu(IGameControl game) {
        addToMap( 0, 0, game::load);
        addToMap( 0, 1, game::exit);
    }

    @Override
    public void reset() {
        setIndex(new Point());
    }

    @Override
    public boolean isAllowingRegularInput() {
        return false;
    }
}
