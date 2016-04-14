package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.entities.IController;


public class PlayerController implements IController {
    private Player player;
    private IInputAdapter input;

    public PlayerController(Player player, IInputAdapter input) {
        this.player = player;
        this.input = input;
    }

    @Override
    public void update() {
        if(input.isKeyPressed(IInputAdapter.Keys.A)){
            player.moveLeft();
        } else if (input.isKeyPressed(IInputAdapter.Keys.D)){
            player.moveRight();
        } else {
            player.stopMoving();
        }

        if(input.isKeyPressed(IInputAdapter.Keys.SPACE) && player.canJump()){
            player.jump();
        }
    }

    public void setPlayer(Player player){
        this.player = player;
    }
}