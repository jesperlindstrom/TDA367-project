package se.chalmers.get_rect.game.entities.enemies.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.enemies.model.Demon;
import se.chalmers.get_rect.utilities.Point;

public class DemonView extends AbstractAnimatedView {
    private static final int WALKING = 1;
    private Demon model;
    private Point offset = new Point(250, 0);

    public DemonView(Demon model){
        super(model, WALKING);
        this.model = model;

        addAnimationFrame(WALKING, "img/entities/demons/mjolnir1.png", 10);
        addAnimationFrame(WALKING, "img/entities/demons/mjolnir2.png", 20);

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (model.getVelocity().getX() > 0) {
            setDrawOffset(offset);
            setFlip(true);

        } else {
            setDrawOffset(new Point(0, 0));
            setFlip(false);
        }

        super.draw(graphics);

    }
}
