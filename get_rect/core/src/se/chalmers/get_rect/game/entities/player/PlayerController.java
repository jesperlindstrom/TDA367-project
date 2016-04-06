package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.IPhysicsController;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.physics.ISolidObject;

public class PlayerController implements IPhysicsController {
    private Player player;
    private IView view;
    private IInputAdapter input;

    public PlayerController(Player player, IView view, IInputAdapter input) {
        this.player = player;
        this.view = view;
        this.input = input;
    }

    @Override
    public void update(long delta) {
        if(input.isKeyPressed(IInputAdapter.Keys.A)){
            player.setxCoordinate(player.getxCoordinate() - 3);
            player.setIsWalking(true);
        }else if(input.isKeyPressed(IInputAdapter.Keys.D)){
            player.setxCoordinate(player.getxCoordinate() + 3);
            player.setIsWalking(true);
        }else{
            player.setIsWalking(false);
        }
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        view.draw(graphics);
    }

    @Override
    public void getBoundingBox() {

    }

}