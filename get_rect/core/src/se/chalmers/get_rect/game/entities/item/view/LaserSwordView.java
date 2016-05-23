package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

import java.util.Random;

public class LaserSwordView extends AbstractMeleeWeaponView {
    private static final String imgPath = "img/items/";
    private IAudioManagerAdapter audioManager;
    private boolean isSelected = false;

    public LaserSwordView(IWeapon model, IAudioManagerAdapter audioManager) {
        super(model, imgPath + "laser_icon.png");
        this.audioManager = audioManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(imgPath + "laser_hand.png", getModel().getHandPos().add(getModel().getFacing() < 0 ? 20 : 0, 0), new Point(-10, 5), getXScale(), 1, -getModel().getTilt());
        if (!isSelected) {
            audioManager.playSound("laserSword1");
            isSelected = true;
        }

        if (isAttacking()) {
            audioManager.playSound("laserSword" + getRandomNumber());
        }
    }

    @Override
    public int getDrawPriority() {
        return 7;
    }

    private int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(4) + 1;

    }
}
