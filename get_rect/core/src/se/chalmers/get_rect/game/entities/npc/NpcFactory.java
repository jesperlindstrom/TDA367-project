package se.chalmers.get_rect.game.entities.npc;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.npc.model.SawmillExpress;
import se.chalmers.get_rect.game.entities.npc.view.SawmillView;
import se.chalmers.get_rect.utilities.Point;

public class NpcFactory {
    @Inject private IRectangleFactoryAdapter rectangleFactory;
    @Inject private DialogRepository dialogRepository;
    public IPhysicsEntity make(String type, Point point) {
        if (type.equals("sawmillExpress"))
            return makeSawmillExpress(point);

        throw new EntityNotFoundException("NPC", type);
    }

    private IPhysicsEntity makeSawmillExpress(Point point) {
        SawmillExpress model = new SawmillExpress(point, rectangleFactory, dialogRepository);
        IView view = new SawmillView(model);

        return new PhysicsEntity(model, view);
    }
}
