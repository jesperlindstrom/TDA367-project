package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;

public class RockView extends AbstractRangedWeaponView {

    public RockView(IWeapon model) {
        super(model, "img/items/rock_icon.png");
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("img/items/rock.png", getModel().getHandPos());
    }
}
