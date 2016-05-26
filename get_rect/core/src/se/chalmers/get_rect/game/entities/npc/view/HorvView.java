package se.chalmers.get_rect.game.entities.npc.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.npc.model.Horv;

public class HorvView extends AbstractAnimatedView{
    private static final int DEFAULT = 1;

    public HorvView(Horv model) {
        super(model, DEFAULT);
        addAnimationFrame(DEFAULT, "img/entities/horv/horv.png");
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        super.draw(graphics);

    }
}
