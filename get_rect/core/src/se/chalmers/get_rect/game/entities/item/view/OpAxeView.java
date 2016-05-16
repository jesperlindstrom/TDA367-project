package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.item.model.IMelee;
import se.chalmers.get_rect.utilities.Point;

public class OpAxeView extends AbstractMeleeWeaponView {

    private static final String imgPath = "img/items/opaxe.png";
    private static final float SCALE = 1f;
    private static final float tilt = 30;


    public OpAxeView(IMelee model) {
        super(model, tilt);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(imgPath, getModel().getHandPos(), new Point(35, 5), getXScale(SCALE), SCALE, getRotation());
        super.draw(graphics);
    }

    @Override
    public int getDrawPriority() {
        return 7;
    }
}
