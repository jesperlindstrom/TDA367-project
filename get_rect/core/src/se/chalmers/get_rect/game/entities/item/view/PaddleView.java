package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public class PaddleView extends AbstractMeleeWeaponView {

    private static final int tilt = 70;
    private static final String imgPath = "img/items/";

    public PaddleView(IWeapon model) {
        super(model, tilt, imgPath+"opaxe_icon.png");
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(imgPath+"paddle_hands.png", getModel().getHandPos().add(getModel().getFacing() < 0 ? 20 : 0, 30), new Point(-10, 5), getXScale(1), 1, getRotation());
        super.draw(graphics);
    }

    @Override
    public int getDrawPriority() {
        return 7;
    }
}
