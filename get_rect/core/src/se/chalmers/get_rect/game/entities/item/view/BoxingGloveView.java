package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public class BoxingGloveView extends AbstractMeleeWeaponView {

    private static final String imgPath = "img/items/";
    private IAudioManagerAdapter audioManager;

    public BoxingGloveView(IWeapon model, IAudioManagerAdapter audioManager) {
        super(model, imgPath + "boxingGlove_icon.png");
        this.audioManager = audioManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(imgPath + "boxingGlove.png", getModel().getHandPos(), new Point(0, 0), getXScale(), 1, -getModel().getTilt());
    }

    @Override
    public int getDrawPriority() {
        return 7;
    }
}