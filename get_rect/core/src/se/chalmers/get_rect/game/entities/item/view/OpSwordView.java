package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.ISoundAdapter;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

import java.util.Random;

public class OpSwordView extends AbstractMeleeWeaponView {

    private static final String imgPath = "img/items/opsword";
    private static final float SCALE = 1;
    private static final float tilt = 30;
    private IAudioManagerAdapter audioManager;
    private boolean isPlaying = false;


    public OpSwordView(IWeapon model, IAudioManagerAdapter audioManager) {
        super(model, tilt, imgPath + "_icon.png");
        this.audioManager = audioManager;
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

        if (isAttacking()) {
            if (randomNumber() == 1) {
                audioManager.playMusic("swosh1");
            } else {
                audioManager.playMusic("swosh2");
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
