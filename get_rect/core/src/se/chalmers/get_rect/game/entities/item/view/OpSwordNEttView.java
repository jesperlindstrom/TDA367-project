package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.item.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public class OpSwordNEttView extends AbstractMeleeWeaponView {

    private static final String imgPath = "img/items/opswordnett.png";
    private static final float SCALE = 0.4f;
    private static final float tilt = 30;


    public OpSwordNEttView(IWeapon model) {
        super(model, tilt);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(imgPath, getModel().getHandPos(), new Point(0, 0), getXScale(SCALE), SCALE, getRotation());
        super.draw(graphics);
    }

    @Override
    public int getDrawPriority() {
        return 7;
    }
}
