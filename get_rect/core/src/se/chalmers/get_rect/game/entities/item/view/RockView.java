package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;

public class RockView extends AbstractRangedWeaponView {

    private IAudioManagerAdapter audioManager;
    private boolean isSelected = false;

    public RockView(IWeapon model, IAudioManagerAdapter audioManager) {
        super(model, "img/items/rock_icon.png");
        this.audioManager = audioManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("img/items/rock.png", getModel().getHandPos());
        if (!isSelected) {
            audioManager.playMusic("swosh1");
            isSelected = true;
        }
        if (isAttacking()) {
            audioManager.playMusic("swosh1");
        }
    }
}
