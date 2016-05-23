package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public class OpAxeView extends AbstractMeleeWeaponView {

    private static final String imgPath = "img/items/";
    private IAudioManagerAdapter audioManager;
    private boolean isSelected = false;

    public OpAxeView(IWeapon model, IAudioManagerAdapter audioManager) {
        super(model, imgPath + "opaxe_icon.png");
        this.audioManager = audioManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(imgPath+"opaxewithhand.png", getModel().getHandPos(), new Point(-10, 5), getXScale(), 1, -getModel().getTilt());

        if (!isSelected) {
            audioManager.playMusic("axe");
            isSelected = true;
        }
        if (isAttacking()) {
            audioManager.playMusic("axe");
        }
    }

    @Override
    public int getDrawPriority() {
        return 7;
    }
}
