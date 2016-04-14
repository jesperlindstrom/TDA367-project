package se.chalmers.get_rect.game.entities.npc;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.PhysicsEntity;
import se.chalmers.get_rect.game.entities.npc.sawmillExpress.SawmillExpress;
import se.chalmers.get_rect.game.entities.npc.sawmillExpress.SawmillView;
import se.chalmers.get_rect.utilities.Point;


public class NpcFactory {
    private IRectangleFactoryAdapter rectangleFactory;

    public NpcFactory(IRectangleFactoryAdapter rectangleFactory) {
        this.rectangleFactory = rectangleFactory;

    }
    public IPhysicsEntity make(Point point) {
        SawmillExpress model = new SawmillExpress(point, rectangleFactory);
        IView view = new SawmillView(model);

        return new PhysicsEntity(model, view);
    }
}
