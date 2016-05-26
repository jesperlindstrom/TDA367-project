package se.chalmers.get_rect.game.entities.npc;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.item.WeaponRepository;
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
    @Inject @Named("Window") private StateManager windowManager;
    @Inject private QuestManager quests;
    @Inject private Player player;
    @Inject private WeaponRepository weaponRepository;
    @Inject private IAudioManagerAdapter audioManager;

    public IEntity make(String type, Point point) throws FileNotFoundException {
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

        if (type.equals("nyaBjorn"))
            return makeNyaBjorn(point);

        throw new EntityNotFoundException("NPC", type);
    }

    private IEntity makeHunchen(Point point) throws FileNotFoundException {
        Hunchen model = new Hunchen(point, rectangleFactory, player);
        IView view = new HunchenView(model);
        return new Entity(model,view);
    }

    private IEntity makeSawmillExpress(Point point) throws FileNotFoundException {
        List<String> phrases = getPhrases("sawmill");
        SawmillExpress model = new SawmillExpress(point, rectangleFactory, phrases, quests, weaponRepository);
        IView view = new SawmillView(model, audioManager);

        return new Entity(model, view);
    }
    private IEntity makeRekoil(Point point) throws FileNotFoundException {
        List<String> phrases = getPhrases("rekoil");
        Rekoil model = new Rekoil(point, rectangleFactory, phrases, quests);
        IView view = new RekoilView(model, audioManager);
        return new Entity(model, view);
    }

    private IEntity makeChessT(Point point) throws FileNotFoundException {
        ChessT model = new ChessT(point, rectangleFactory, windowManager, player);
        IView view = new ChessTView(model);

        return new Entity(model, view);
    }

    private IEntity makeHorv(Point point) throws FileNotFoundException {
        List<String> phrases = getPhrases("horv");
        Horv model = new Horv(point, rectangleFactory, phrases, quests);
        IView view = new HorvView(model);

        return new Entity(model, view);
    }
    private IEntity makeNyaBjorn(Point point) throws FileNotFoundException{
        List<String> phrases = getPhrases("nyaBjorn");
        NyaBjorn model = new NyaBjorn(point, rectangleFactory, phrases, quests);
        IView view = new NyaBjornView(model);
        return new Entity(model, view);
    }

    private List<String> getPhrases(String npc) throws FileNotFoundException {
        return new DialogRepository(npc).get();
    }
}
