package se.chalmers.get_rect.game.window.model;

import se.chalmers.get_rect.utilities.Point;

public class InGameMenu extends AbstractGridModel {
    private boolean didSave = false;

    public InGameMenu(IGameControl game) {
        super();
        addToMap(0, 0, game::resume);
        addToMap(0, 1, () -> {
            didSave = true;
            game.save();
            moveMarkUp();
        });
        addToMap(0, 2, game::exit);
    }

    @Override
    public void reset() {
        didSave = false;
        setIndex(new Point(0, 0));
    }

    @Override
    public boolean isAllowingRegularInput() {
        return true;
    }

    public boolean isSaved() {
        return didSave;
    }

    @Override
    public void setIndex(Point point) {
        super.setIndex(point);
    }
}
