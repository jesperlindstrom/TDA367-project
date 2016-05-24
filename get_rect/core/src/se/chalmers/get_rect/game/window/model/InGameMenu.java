package se.chalmers.get_rect.game.window.model;

import se.chalmers.get_rect.utilities.Point;

public class InGameMenu extends AbstractGridModel {

    public InGameMenu(IGameControl game) {
        super();
        addToMap(0, 0, game::resume);
        addToMap(0, 1, () -> {
            game.save();
            moveMarkUp();
            disableButton(new Point(0, 1));
        });
        addToMap(0, 2, game::exit);
    }

    @Override
    public void reset() {
        activateAll();
        setIndex(new Point(0, 0));
    }

    @Override
    public boolean isAllowingRegularInput() {
        return true;
    }

}
