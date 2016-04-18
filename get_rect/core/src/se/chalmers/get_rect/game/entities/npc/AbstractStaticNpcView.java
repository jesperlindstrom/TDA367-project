package se.chalmers.get_rect.game.entities.npc;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractStaticView;
import se.chalmers.get_rect.game.entities.IView;

public abstract class AbstractStaticNpcView extends AbstractStaticView {
    private IView subView;

    protected AbstractStaticNpcView(INpcModel model, String image) {
        super(model, image);
        subView = new NpcSubView(model);
    }

    @Override
    public void draw(IGraphicsAdapter graphics){
        super.draw(graphics);
        subView.draw(graphics);
    }
}