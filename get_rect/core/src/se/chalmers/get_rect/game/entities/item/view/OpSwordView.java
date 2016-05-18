package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.ISoundAdapter;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

import java.util.Random;

public class OpSwordView extends AbstractMeleeWeaponView {

    private static final String imgPath = "img/items/opsword";
    private static final float SCALE = 1;
    private static final float tilt = 30;
    private ISoundAdapter swoshSound1;
    private ISoundAdapter swoshSound2;
    private IAssetManagerAdapter assetManager;
    private boolean isPlaying = false;


    public OpSwordView(IWeapon model, IAssetManagerAdapter assetManager) {
        super(model, tilt, imgPath + "_icon.png");
        this.assetManager = assetManager;
    }

    /**
     *
     * randomizes to choose which of the sounds to play
     * @param graphics
     */

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(imgPath + ".png", getModel().getHandPos(), new Point(0, 0), getXScale(SCALE), SCALE, getRotation());
        super.draw(graphics);
        if (swoshSound1 == null ){
            swoshSound1 = assetManager.getSound("sounds/swosh1.mp3");
        }
        if (swoshSound2 == null) {
            swoshSound2 = assetManager.getSound("sounds/swosh2.mp3");
        }
        if (getModel().getCooldownFrames() == 0) {
            swoshSound1.pause();
            swoshSound2.pause();
            isPlaying = false;
        } else {
            if (!isPlaying) {
                    if (randomNumber() == 1) {
                        swoshSound1.play();
                    } else {
                        swoshSound2.play();
                    }
                isPlaying = true;
            }
        }
    }

    @Override
    public int getDrawPriority() {
        return 7;
    }
    private int randomNumber() {
        Random random = new Random();
        return random.nextInt(2)+1;

    }
}
