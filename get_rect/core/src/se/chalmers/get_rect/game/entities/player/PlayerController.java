package se.chalmers.get_rect.game.entities.player;

import com.google.inject.Inject;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.utilities.Point;

public class PlayerController implements IController {
    @Inject private IInputAdapter input;
    @Inject private Player player;

    @Override
    public void update() {
        handleMovement();
        handleShooting();
        handleJumping();
        handleInteract();
        handleWeaponSwitch();
    }

    private void handleInteract(){
        if(input.isKeyJustPressed(IInputAdapter.Keys.E)){
            player.interact();
        }
    }

    private void handleMovement() {
        if(input.isKeyPressed(IInputAdapter.Keys.A)){
            player.moveLeft();
        } else if (input.isKeyPressed(IInputAdapter.Keys.D)){
            player.moveRight();
        } else {
            player.stopMoving();
        }
        if (input.isKeyJustPressed(IInputAdapter.Keys.H)) {
            player.flyHome();
        }
    }

    private void handleShooting() {
        Point direction = new Point(0, 0);

        if (input.isKeyJustPressed(IInputAdapter.Keys.LEFTKEY)) {
            direction = direction.addX(-1);
        }
        if (input.isKeyJustPressed(IInputAdapter.Keys.UPKEY)) {
            direction = direction.addY(1);
        }
        if (input.isKeyJustPressed(IInputAdapter.Keys.RIGHTKEY)) {
            direction = direction.addX(1);
        }
        if (input.isKeyJustPressed(IInputAdapter.Keys.DOWNKEY)) {
            direction = direction.addY(-1);
        }


        if (!direction.equals(new Point(0, 0))) {
            player.use(direction);
        }
    }

    private void handleJumping() {
        if(input.isKeyPressed(IInputAdapter.Keys.SPACE)){
            player.jump();
        }
    }
    private void handleWeaponSwitch(){
        if(input.isKeyJustPressed(IInputAdapter.Keys.Q)){
            player.switchWeapon();
        }
    }
}