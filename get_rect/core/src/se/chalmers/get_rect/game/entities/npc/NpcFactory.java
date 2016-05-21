package se.chalmers.get_rect.game.entities.npc;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.npc.model.*;
import se.chalmers.get_rect.game.entities.npc.view.*;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.states.StateManager;
import se.chalmers.get_rect.utilities.Point;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class NpcFactory {
    @Inject private IRectangleFactoryAdapter rectangleFactory;
    @Inject private DialogRepository dialogRepository;
    @Inject @Named("Window") private StateManager windowManager;
    @Inject private QuestManager quests;
    @Inject private Player player;

    public IPhysicsEntity make(String type, Point point) {
        if (type.equals("sawmillExpress"))
            return makeSawmillExpress(point);

        if (type.equals("rekoil"))
            return makeRekoil(point);

        if (type.equals("chesst"))
            return makeChessT(point);

        if (type.equals("hunchen"))
            return makeHunchen(point);

        if (type.equals("horv"))
            return makeHorv(point);

        throw new EntityNotFoundException("NPC", type);
    }

    private IPhysicsEntity makeHunchen(Point point){
        Hunchen model = new Hunchen(point, rectangleFactory, player);
        IView view = new HunchenView(model);
        return new PhysicsEntity(model,view);
    }

    private IPhysicsEntity makeSawmillExpress(Point point) {
        List<String> phrases = getPhrases("sawmill");
        SawmillExpress model = new SawmillExpress(point, rectangleFactory, phrases, quests);
        IView view = new SawmillView(model);

        return new PhysicsEntity(model, view);
    }
    private IPhysicsEntity makeRekoil(Point point) {
        List<String> phrases = getPhrases("rekoil");
        Rekoil model = new Rekoil(point, rectangleFactory, phrases, quests);
        IView view = new RekoilView(model);
        return new PhysicsEntity(model, view);
    }

    private IPhysicsEntity makeChessT(Point point) {
        ChessT model = new ChessT(point, rectangleFactory, windowManager, player);
        IView view = new ChessTView(model);

        return new PhysicsEntity(model, view);
    }

    private IPhysicsEntity makeHorv(Point point) {
        List<String> phrases = getPhrases("horv");
        Horv model = new Horv(point, rectangleFactory, phrases);
        IView view = new HorvView(model);

        return new PhysicsEntity(model, view);
    }

    private List<String> getPhrases(String npc) {
        List<String> list = new ArrayList<>();

        try {
            list = dialogRepository.get(npc);
        } catch (FileNotFoundException e){
            // todo: this should probably be thrown further up
            System.out.println(e.getMessage());
        }

        return list;
    }
}
