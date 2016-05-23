package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public class FireMagicView extends AbstractRangedWeaponView {
    private static final String path = "img/items/";
    private static final int DRAW_PRIORITY = 6;
    private static final String IMG_1 = path + "fire_magic_1.png";
    private static final String IMG_2 = path + "fire_magic_2.png";
    private String imgPath = IMG_1;
    private int i = 0;
    private boolean otherFrame = false;
    private boolean isSelected = false;

    private IAudioManagerAdapter audioManager;
    public FireMagicView(IWeapon model, IAudioManagerAdapter audioManager) {
        super(model, path + "fire_magic_icon.png");
        this.audioManager = audioManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        i++;

        if (i == 5) {
            i = 0;
            otherFrame = !otherFrame;

            if (otherFrame) {
                imgPath = IMG_1;
            } else {
                imgPath = IMG_2;
            }
            if (!isSelected) {
                audioManager.playMusic("fireball");
                isSelected = true;
            }
            if (getModel().getCooldownFrames() > getModel().getCooldown()-19){
                audioManager.playMusic("fireball");
            }
        }

        graphics.draw(imgPath, getModel().getHandPos(), new Point(0, 0), getXScale(), getYScale(), getRotation());
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }
}
