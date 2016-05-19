package se.chalmers.get_rect.game.entities.npc.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.npc.model.Horv;
import se.chalmers.get_rect.utilities.Point;

public class HorvView extends AbstractAnimatedView{
    private static final int DEFAULT = 1;
    private static final int SHOW_BEER = 2;


    public HorvView(Horv model) {
        super(model, DEFAULT);
        super.setDrawOffset(new Point(0, -20));
        addAnimationFrame(DEFAULT, "img/entities/horv/horv1.png");
        addAnimationFrame(SHOW_BEER, "img/entities/horv/horv2.png");
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (true) {
            addAnimationFrame(0, "img/interact/exclamation.png");
            playSequence(SHOW_BEER);
        } else {
            playSequence(DEFAULT);
        }
        super.draw(graphics);
    }
}
