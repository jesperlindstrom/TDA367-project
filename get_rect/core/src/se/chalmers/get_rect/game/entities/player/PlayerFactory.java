package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.utilities.Point;

public class PlayerFactory {
    private IInputAdapter input;
    private IRectangleFactoryAdapter rectangleFactory;
    /**
     * Initialize a new player factory.
     * @param game
     */
    public PlayerFactory(IGame game){
        this.input = game.getInput();
        this.rectangleFactory = game.getRectangleFactory();

    }

    /**
     * Maker for a new player.
     * @return
     */
    public PlayerController make(int x, int y) {
        Player model = new Player(new Point(x, y), rectangleFactory);
        IView view = new PlayerView(model);


        return new PlayerController(model, view, input);
    }
}
