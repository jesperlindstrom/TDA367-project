package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.item.swing.ISwinger;

public interface IMelee extends IWeapon, ISwinger {

    int getSwingFrames();
    float getSwingDegrees();
}
