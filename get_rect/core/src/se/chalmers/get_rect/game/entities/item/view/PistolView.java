package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.ISoundAdapter;
import se.chalmers.get_rect.game.entities.item.model.IRanged;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public class PistolView extends AbstractRangedWeaponView {

    private static final String path = "img/item/";
    private static final int DRAW_PRIO = 6;
    private ISoundAdapter bulletSound;

    public PistolView(IWeapon model, IAssetManagerAdapter assetManager) {
        super(model, path+"pistol_icon.png");
        //bulletSound = assetManager.getSound("sounds/bulletSound.mp3");

    }



    @Override
    public void draw(IGraphicsAdapter graphics) {
        String imgPath;
        if (getModel().getUsedFrames() == 0) {
            imgPath = path+"pistol.png";
        } else {
            imgPath = path+"pistolPANG.png";
        }
        graphics.draw(imgPath, getModel().getHandPos(), new Point(0, 0), getXScale(), getYScale(), getRotation());
        //bulletSound.play();
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIO;
    }


}
