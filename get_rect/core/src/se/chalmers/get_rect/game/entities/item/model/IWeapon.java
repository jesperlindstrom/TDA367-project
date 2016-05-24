package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.item.swing.ISwinger;
import se.chalmers.get_rect.utilities.Point;

public interface IWeapon extends IModel{

    /**
     * @param aimDirection direction aimed.
     * @param entityHolder
     */
    void use(Point aimDirection, IEntityHolder entityHolder);

    boolean isFound();

    void setFound(boolean found);

    void remove();
    /**
     * @return returns the position of the players hand
     */
    Point getHandPos();

    /**
     * @return returns the frames since usage.
     */
    int getCooldownFrames();

    /**
     * Returns an int that represents the players facing.
     * @return returns the values -1 or 1.
     */
    int getFacing();

    String getType();

    int getCooldown();
}
