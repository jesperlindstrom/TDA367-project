package se.chalmers.get_rect.game.window.model;

import se.chalmers.get_rect.utilities.Point;

public class ErrorWindow extends AbstractGridModel{

    private String message;

    public ErrorWindow(IGameControl game) {
        addToMap(0, 0, game::exit);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
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
