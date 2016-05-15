package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.utilities.Point;

public interface IRanged extends IWeapon {
    /**
     * The direction you are currently aiming
     * @return a point with x and y values -1, 0 or 1
     */
    Point getAimDirection();
}
