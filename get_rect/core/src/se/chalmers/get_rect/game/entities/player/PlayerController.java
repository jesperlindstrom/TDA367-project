package se.chalmers.get_rect.game.entities.player;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.input.Actions;
import se.chalmers.get_rect.game.input.GameInput;
import se.chalmers.get_rect.game.entities.IController;

public class PlayerController implements IController {
    @Inject private GameInput gameInput;
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
        if(gameInput.isKeyJustPressed(Actions.INTERACT)){
            player.interact();
        }
    }

    private void handleMovement() {
        if(gameInput.isKeyPressed(Actions.MOVE_LEFT)){
            player.moveLeft();
        } else if (gameInput.isKeyPressed(Actions.MOVE_RIGHT)){
            player.moveRight();
        } else {
            player.stopMoving();
        }
        if (gameInput.isKeyJustPressed(Actions.RESPAWN)) {
            player.flyHome();
        }
    }

    private void handleShooting() {
        if (gameInput.isKeyJustPressed(Actions.SHOOT))
            player.use(gameInput.getAim());
    }

    private void handleJumping() {
        if(gameInput.isKeyPressed(Actions.JUMP)){
            player.jump();
        }
    }
    private void handleWeaponSwitch(){
        if(gameInput.isKeyJustPressed(Actions.SWITCH_WEAPON)){
            player.switchWeapon();
        }
    }
}