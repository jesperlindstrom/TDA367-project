package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public class WandView extends AbstractRangedWeaponView {
    private static final String path = "img/items/";
    private static final int DRAW_PRIORITY = 6;
    private IAudioManagerAdapter audioManager;

    public WandView(IWeapon model, IAudioManagerAdapter audioManager) {
        super(model, path + "wand_icon.png");
        this.audioManager = audioManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {

        String imgPath;
        if (getModel().getCooldownFrames() != 0 && getModel().getCooldownFrames() > Math.abs(getModel().getCooldown()-6)) {
            imgPath = path + "wand_down_hand.png";
        } else {
            imgPath = path + "wand_up_hand.png";
        }
        if (getModel().getCooldownFrames() == getModel().getCooldown()-1) {
//            audioManager.playSound("bulletSound", 0.5f);

        }
        graphics.draw(imgPath, getModel().getHandPos(), new Point(0, 0), getXScale(), getYScale(), getRotation());

    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }
}
