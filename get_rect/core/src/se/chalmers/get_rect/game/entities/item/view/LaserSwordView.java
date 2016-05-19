package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

import java.util.Random;

public class LaserSwordView extends AbstractMeleeWeaponView {
    private static final String imgPath = "img/items/";
    private static final float SCALE = 1f;
    private static final float tilt = 15;
    private IAudioManagerAdapter audioManager;
    private boolean isUsed = false;

    public LaserSwordView(IWeapon model, IAudioManagerAdapter audioManager) {
        super(model, tilt, imgPath + "laser_icon.png");
        this.audioManager = audioManager;

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(imgPath + "laser_hand.png", getModel().getHandPos().add(getModel().getFacing() < 0 ? 20 : 0, 0), new Point(-10, 5), getXScale(SCALE), SCALE, getRotation());
        super.draw(graphics);
        if (isUsed) {
            audioManager.playSound("laserSword1");
            isUsed = true;
        }

        if (isAttacking()) {
            audioManager.playSound("laserSword" + getRandomNumber());
        }
        System.out.println("isAttacking = " + isAttacking());
    }

    @Override
    public int getDrawPriority() {
        return 7;
    }

    private int getRandomNumber() {
        Random random = new Random();
        System.out.println(random.nextInt(4) + 1);
        return random.nextInt(4) + 1;

    }
}
