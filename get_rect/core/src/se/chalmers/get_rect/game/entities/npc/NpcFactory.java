package se.chalmers.get_rect.game.entities.npc;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.npc.sawmillExpress.SawmillExpress;
import se.chalmers.get_rect.game.entities.npc.sawmillExpress.SawmillView;
import se.chalmers.get_rect.utilities.Point;

/**
 * Created by simsund on 2016-04-11.
 */
public class NpcFactory {
    private IRectangleFactoryAdapter rectangleFactory;

    public NpcFactory(IRectangleFactoryAdapter rectangleFactory) {
        this.rectangleFactory = rectangleFactory;

    }

    public SawmillController make(int x, int y) {
        return make(new Point(x, y));
    }

    public SawmillController make(Point point) {
        SawmillExpress model = new SawmillExpress(point, rectangleFactory);
        IView view = new SawmillView(model);

        return new SawmillController(view, model);
    }
}
