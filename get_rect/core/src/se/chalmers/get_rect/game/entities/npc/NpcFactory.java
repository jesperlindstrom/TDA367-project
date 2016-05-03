package se.chalmers.get_rect.game.entities.npc;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.EntityNotFoundException;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.PhysicsEntity;
import se.chalmers.get_rect.game.entities.npc.model.SawmillExpress;
import se.chalmers.get_rect.game.entities.npc.view.SawmillView;
import se.chalmers.get_rect.utilities.Point;

public class NpcFactory {
    private IRectangleFactoryAdapter rectangleFactory;

    public NpcFactory(IRectangleFactoryAdapter rectangleFactory) {
        this.rectangleFactory = rectangleFactory;
    }

    public IPhysicsEntity make(NpcDataStore dataStore) {
        return make(dataStore.getType(), dataStore.getPosition());
    }

    public IPhysicsEntity make(String type, Point point) {
        if (type.equals("sawmillExpress"))
            return makeSawmillExpress(point);

        throw new EntityNotFoundException("NPC", type);
    }

    private IPhysicsEntity makeSawmillExpress(Point point) {
        SawmillExpress model = new SawmillExpress(point, rectangleFactory);
        IView view = new SawmillView(model);

        return new PhysicsEntity(model, view);
    }
}
