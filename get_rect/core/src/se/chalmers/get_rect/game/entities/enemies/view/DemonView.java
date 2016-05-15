package se.chalmers.get_rect.game.entities.enemies.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.enemies.model.Demon;

public class DemonView extends AbstractAnimatedView {
    private static final int WALKING = 1;

    public DemonView(Demon model){
        super(model, WALKING);

        addAnimationFrame(WALKING, "img/entities/demons/mjolnir1.png", 10);
        addAnimationFrame(WALKING, "img/entities/demons/mjolnir2.png", 20);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        super.draw(graphics);
    }
}
