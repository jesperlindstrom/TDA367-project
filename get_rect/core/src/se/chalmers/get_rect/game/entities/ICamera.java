package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.utilities.Point;

public interface ICamera {
    Point getPosition();
    void snapToPosition(Point pos);
    ICameraAdapter getAdapter();
}
