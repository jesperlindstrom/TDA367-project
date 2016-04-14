package se.chalmers.get_rect.game.entities.player;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.scenes.IScene;


public class PlayerController implements IController {
    private Player player;
    private IView view;
    private IInputAdapter input;
    private IScene scene;

    public PlayerController(Player player, IView view, IInputAdapter input) {
        this.player = player;
        this.view = view;
        this.input = input;
    }

    @Override
    public void update() {
        //Section for player walking function
        player.move(input);
    }


    public void setScene(IScene scene) {
        this.scene = scene;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

}