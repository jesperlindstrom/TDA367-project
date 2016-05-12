package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.item.IRanged;
import se.chalmers.get_rect.utilities.Point;

public class PistolViewRanged extends AbstractRangedWeaponView {

    private static final float scale = 0.2f;
    private static final int DRAW_PRIO = 6;

    public PistolViewRanged(IRanged model) {
        super(model);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        String imgPath;
        if (getModel().getUsedFrames() == 0) {
            imgPath = "img/items/potistol.png";
        } else {
            imgPath = "img/items/potistolPANG.png";
        }
        graphics.draw(imgPath, getModel().getHandPos(), new Point(0, 0), getXScale(scale), getYScale(scale), getRotation());
        super.draw(graphics);
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIO;
    }
}
