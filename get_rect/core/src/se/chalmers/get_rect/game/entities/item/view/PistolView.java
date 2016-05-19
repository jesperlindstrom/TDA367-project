package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public class PistolView extends AbstractRangedWeaponView {
    private static final String path = "img/items/";
    private static final int DRAW_PRIORITY = 6;
    private IAudioManagerAdapter audioManager;

    public PistolView(IWeapon model, IAudioManagerAdapter audioManager) {
        super(model, path + "pistol_icon.png");
        this.audioManager = audioManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {

        String imgPath;
        if (getModel().getUsedFrames() == 0) {
            imgPath = path + "pistol.png";
        } else {
            imgPath = path + "pistolPANG.png";
            audioManager.playSound("bulletSound" ,0.5f);
        }
        graphics.draw(imgPath, getModel().getHandPos(), new Point(0, 0), getXScale(), getYScale(), getRotation());

    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }
}
