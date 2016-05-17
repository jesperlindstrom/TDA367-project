package se.chalmers.get_rect.game.entities.item.view;

import com.badlogic.gdx.assets.AssetManager;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.ISoundAdapter;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public class OpSwordView extends AbstractMeleeWeaponView {

    private static final String imgPath = "img/items/opsword";
    private static final float SCALE = 1;
    private static final float tilt = 30;
    private ISoundAdapter swoshSound;
    private IAssetManagerAdapter assetManager;
    private boolean isPlaying = false;


    public OpSwordView(IWeapon model, IAssetManagerAdapter assetManager) {
        super(model, tilt, imgPath + "_icon.png");
        this.assetManager = assetManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(imgPath + ".png", getModel().getHandPos(), new Point(0, 0), getXScale(SCALE), SCALE, getRotation());
        super.draw(graphics);
        if (swoshSound == null){
            swoshSound = assetManager.getSound("sounds/swosh1.mp3");
        }
        if (getModel().getUsedFrames() < 2) {
            swoshSound.pause();
            isPlaying = false;
        } else {
            if (!isPlaying) {
                swoshSound.loop(0.5f);
                isPlaying = true;
            }
        }
    }

    @Override
    public int getDrawPriority() {
        return 7;
    }
}
