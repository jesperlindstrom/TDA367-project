package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.ISoundAdapter;
import se.chalmers.get_rect.game.entities.item.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public class PistolView extends AbstractWeaponView {

    private ISoundAdapter bulletSound;
    private static final float scale = 0.1f;

    public PistolView(IWeapon model, IAssetManagerAdapter assetManager) {
        super(model);
        //bulletSound = assetManager.getSound("music/bulletSound.mp3");
        }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        String imgPath;
        if (getModel().getUsedFrames() == 0) {
            imgPath = "img/items/potistol.png";
        } else {
            imgPath = "img/items/potistolPANG.png";
        }
        graphics.draw(imgPath, getModel().getSpawnPoint().add(2, -18), new Point(0, 0), getXScale(scale), scale, getRotation());
        //bulletSound.play();
    }

}
