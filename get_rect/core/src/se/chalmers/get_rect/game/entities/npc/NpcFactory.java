package se.chalmers.get_rect.game.entities.npc;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import se.chalmers.get_rect.game.entities.*;
<<<<<<< HEAD
import se.chalmers.get_rect.game.entities.npc.model.Rekoil;
import se.chalmers.get_rect.game.entities.npc.view.RekoilView;
=======
import se.chalmers.get_rect.game.entities.npc.model.ChessT;
import se.chalmers.get_rect.game.entities.npc.view.ChessTView;
>>>>>>> 6f26fd76f5f0792ffc7b1a1e4ebbd4e7ba445221
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
<<<<<<< HEAD
        if (type.equals("rekoil"))
            return makeRekoil(point);
=======
        if (type.equals("chesst"))
            return makeChessT(point);
>>>>>>> 6f26fd76f5f0792ffc7b1a1e4ebbd4e7ba445221

        throw new EntityNotFoundException("NPC", type);
    }

    private IPhysicsEntity makeSawmillExpress(Point point) {
        SawmillExpress model = new SawmillExpress(point, rectangleFactory, dialogRepository);
        IView view = new SawmillView(model);

        return new PhysicsEntity(model, view);
    }
<<<<<<< HEAD
    private IPhysicsEntity makeRekoil(Point point) {
        Rekoil model = new Rekoil(point, rectangleFactory, dialogRepository);
        IView view = new RekoilView(model);
=======

    private IPhysicsEntity makeChessT(Point point) {
        ChessT model = new ChessT(point, rectangleFactory, windowManager);
        IView view = new ChessTView(model);
>>>>>>> 6f26fd76f5f0792ffc7b1a1e4ebbd4e7ba445221

        return new PhysicsEntity(model, view);
    }
}
