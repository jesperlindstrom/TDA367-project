package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.ISoundAdapter;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public class PistolView extends AbstractRangedWeaponView {
    private static final String path = "img/items/";
    private static final int DRAW_PRIORITY = 6;
    private ISoundAdapter bulletSound;
    private IAssetManagerAdapter assetManager;

    public PistolView(IWeapon model, IAssetManagerAdapter assetManager) {
        super(model, path + "pistol_icon.png");
        this.assetManager = assetManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (bulletSound == null) {
            bulletSound = assetManager.getSound("sounds/bulletSound.mp3");
        }

        String imgPath;
        if (getModel().getCooldownFrames() != 0 && getModel().getCooldownFrames() > Math.abs(getModel().getCooldown()-6)) {
            imgPath = path + "pistolPANG.png";
        } else {
            imgPath = path + "pistol.png";
        }
        if (getModel().getCooldownFrames() == getModel().getCooldown()-1) {
            bulletSound.play(0.5f);
        }
        graphics.draw(imgPath, getModel().getHandPos(), new Point(0, 0), getXScale(), getYScale(), getRotation());

    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }
}
