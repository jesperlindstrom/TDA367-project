package se.chalmers.get_rect.game.scenes.menu.menuEntities;

import se.chalmers.get_rect.utilities.Point;

public class ButtonDataStore {

    private String type;
    private int x;
    private int y;

    public Point getPosition() {
        return new Point(x, y);
    }

    public String getType() {
        return type;
    }
}
