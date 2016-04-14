package se.chalmers.get_rect.game.entities.player;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.scenes.IScene;


public class PlayerController implements IController {
    private Player player;
    private IInputAdapter input;

    public PlayerController(IInputAdapter input) {
        this.input = input;
    }

    @Override
    public void update() {
        //Section for player walking function
        player.move(input);
    }

    public void setPlayer(Player player){
        this.player = player;
    }

}