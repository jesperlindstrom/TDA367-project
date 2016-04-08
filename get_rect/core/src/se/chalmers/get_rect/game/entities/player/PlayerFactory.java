package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.utilities.Point;

public class PlayerFactory {
    private IInputAdapter input;
    private IRectangleFactoryAdapter rectangleFactory;
    /**
     * Initialize a new player factory.
     * @param input
     */
    public PlayerFactory(IInputAdapter input, IRectangleFactoryAdapter rectangleFactory){
        this.input = input;
        this.rectangleFactory = rectangleFactory;
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
