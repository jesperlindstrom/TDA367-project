package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.ISoundAdapter;
import se.chalmers.get_rect.game.entities.item.model.IRanged;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public class PistolView extends AbstractRangedWeaponView {

    private static final int DRAW_PRIO = 6;
    private ISoundAdapter bulletSound;

    public PistolView(IWeapon model, IAssetManagerAdapter assetManager) {
        super(model);
        //bulletSound = assetManager.getSound("sounds/bulletSound.mp3");

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        String imgPath;
        if (getModel().getUsedFrames() == 0) {
            imgPath = "img/items/pistol.png";
        } else {
            imgPath = "img/items/pistolPANG.png";
        }
        graphics.draw(imgPath, getModel().getHandPos(), new Point(0, 0), getXScale(1), getYScale(1), getRotation());
        //bulletSound.play();
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIO;
    }
}
