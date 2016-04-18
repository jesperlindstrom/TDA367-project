package se.chalmers.get_rect.game.entities.npc;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.IView;

public abstract class AbstractAnimatedNpcView extends AbstractAnimatedView {
    private IView subView;

    protected AbstractAnimatedNpcView(INpcModel model, int defaultSequence) {
        super(model, defaultSequence);
        subView = new NpcSubView(model);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        super.draw(graphics);
        subView.draw(graphics);
    }
}