package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public class BoxingGloveView extends AbstractMeleeWeaponView {

    private static final String imgPath = "img/items/";
    private IAudioManagerAdapter audioManager;
    private boolean isSelected = false;

    public BoxingGloveView(IWeapon model, IAudioManagerAdapter audioManager) {
        super(model, imgPath + "boxingGlove_icon.png");
        this.audioManager = audioManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(imgPath + "boxingGlove.png", getModel().getHandPos().add(getModel().getFacing() < 0 ? 50 : -50, 50), new Point(0, 0), getXScale(), 1, - getModel().getTilt());
        if (!isSelected) {
            audioManager.playMusic("punch");
            isSelected = true;
        }
        if (isAttacking()) {
            audioManager.playMusic("punch");
        }
    }

    @Override
    public int getDrawPriority() {
        return 7;
    }
}
