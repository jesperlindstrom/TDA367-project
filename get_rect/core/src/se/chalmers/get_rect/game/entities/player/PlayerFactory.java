package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.utilities.Point;

public class PlayerFactory {
    private IInputAdapter input;

    /**
     * Initialize a new player factory.
     * @param input
     */
    public PlayerFactory(IInputAdapter input){
        this.input = input;
    }

    /**
     * Maker for a new player.
     * @return
     */
    public PlayerController make(int x, int y) {
        Player model = new Player(new Point(x, y));
        IView view = new PlayerView(model);


        return new PlayerController(model, view, input);
    }
}
