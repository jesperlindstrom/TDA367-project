package se.chalmers.get_rect.game.entities.item;

import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.utilities.Point;

public interface IWeapon extends IModel {
    void use(Point direction, IEntityHolder entityHolder);
    void remove();
    Point getHandPos();
    int getUsedFrames();
    int getFacing();
    void setActive();
}
