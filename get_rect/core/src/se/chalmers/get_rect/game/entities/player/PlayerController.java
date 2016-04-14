package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.utilities.Point;


public class PlayerController implements IController {
    private Player player;
    private IInputAdapter input;

    public PlayerController(IInputAdapter input) {
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

        Point direction = new Point(0, 0);
        if (input.isKeyJustPressed(IInputAdapter.Keys.LEFTKEY)) {
            direction.addX(-1);
        }
        if (input.isKeyJustPressed(IInputAdapter.Keys.UPKEY)) {
            direction.addY(1);
        }
        if (input.isKeyJustPressed(IInputAdapter.Keys.RIGHTKEY)) {
            direction.addX(1);
        }
        if (input.isKeyJustPressed(IInputAdapter.Keys.DOWNKEY)) {
            direction.addY(-1);
        }
        if (!direction.equals(new Point(0, 0))) {
            player.shoot(direction);
        }

        if(input.isKeyPressed(IInputAdapter.Keys.SPACE) && player.canJump()){
            player.jump();
        }
    }

    public void setPlayer(Player player){
        this.player = player;
    }
}