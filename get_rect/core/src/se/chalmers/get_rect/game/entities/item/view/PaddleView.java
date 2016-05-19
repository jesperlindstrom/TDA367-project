package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public class PaddleView extends AbstractMeleeWeaponView {

    private static final int tilt = 70;
    private static final String imgPath = "img/items/";
    private IAudioManagerAdapter audioManager;

    public PaddleView(IWeapon model, IAudioManagerAdapter audioManager) {
        super(model, tilt, imgPath + "paddle_icon.png");
        this.audioManager = audioManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(imgPath + "paddle_hands.png", getModel().getHandPos().add(getModel().getFacing() < 0 ? 20 : 0, 30), new Point(-10, 5), getXScale(1), 1, getRotation());
        super.draw(graphics);
        if(isAttacking()) {
            audioManager.playMusic("paddle");
        }

    }

    @Override
    public int getDrawPriority() {
        return 7;
    }
}
