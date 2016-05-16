package se.chalmers.get_rect.game.entities.npc;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.npc.model.ChessT;
import se.chalmers.get_rect.game.entities.npc.view.ChessTView;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.npc.model.SawmillExpress;
import se.chalmers.get_rect.game.entities.npc.view.SawmillView;
import se.chalmers.get_rect.states.StateManager;
import se.chalmers.get_rect.utilities.Point;

public class NpcFactory {
    @Inject private IRectangleFactoryAdapter rectangleFactory;
    @Inject private DialogRepository dialogRepository;
    @Inject @Named("Window") private StateManager windowManager;

    public IPhysicsEntity make(String type, Point point) {
        if (type.equals("sawmillExpress"))
            return makeSawmillExpress(point);
        if (type.equals("chesst"))
            return makeChessT(point);

        throw new EntityNotFoundException("NPC", type);
    }

    private IPhysicsEntity makeSawmillExpress(Point point) {
        SawmillExpress model = new SawmillExpress(point, rectangleFactory, dialogRepository);
        IView view = new SawmillView(model);

        return new PhysicsEntity(model, view);
    }

    private IPhysicsEntity makeChessT(Point point) {
        ChessT model = new ChessT(point, rectangleFactory, windowManager);
        IView view = new ChessTView(model);

        return new PhysicsEntity(model, view);
    }
}
