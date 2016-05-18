package se.chalmers.get_rect.game.entities.player;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.GameInput;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.utilities.Point;

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
        if(gameInput.isKeyJustPressed(GameInput.Actions.INTERACT)){
            player.interact();
        }
    }

    private void handleMovement() {
        if(gameInput.isKeyPressed(GameInput.Actions.MOVE_LEFT)){
            player.moveLeft();
        } else if (gameInput.isKeyPressed(GameInput.Actions.MOVE_RIGHT)){
            player.moveRight();
        } else {
            player.stopMoving();
        }
        if (gameInput.isKeyJustPressed(GameInput.Actions.RESPAWN)) {
            player.flyHome();
        }
    }

    private void handleShooting() {
        if (gameInput.isKeyJustPressed(GameInput.Actions.SHOOT)) player.use(gameInput.getAim());
    }

    private void handleJumping() {
        if(gameInput.isKeyPressed(GameInput.Actions.JUMP)){
            player.jump();
        }
    }
    private void handleWeaponSwitch(){
        if(gameInput.isKeyJustPressed(GameInput.Actions.SWITCH_WEAPON)){
            player.switchWeapon();
        }
    }
}