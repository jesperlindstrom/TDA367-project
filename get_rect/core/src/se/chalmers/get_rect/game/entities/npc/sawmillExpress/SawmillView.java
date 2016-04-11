package se.chalmers.get_rect.game.entities.npc.sawmillExpress;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.IView;

/**
 * Created by simsund on 2016-04-11.
 */
public class SawmillView implements IView{
    private IModel model;

    public SawmillView(IModel SawmillExpress) {
        model = SawmillExpress;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("img/entities/sawmill/sawmill-express.png", model.getPosition());
    }
}
