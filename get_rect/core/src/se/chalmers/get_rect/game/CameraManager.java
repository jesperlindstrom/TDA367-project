package se.chalmers.get_rect.game;


import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.utilities.Point;

public class CameraManager implements IGameComponent{

    private Point lastPosition;
    private ICameraAdapter cameraAdapter;
    private PlayerController playerController;


    public CameraManager(ICameraAdapter cameraAdapter, PlayerController playerController){
        this.cameraAdapter = cameraAdapter;
        this.playerController = playerController;
        this.lastPosition = playerController.getPosition();
        // Sets the camera position center to 300px above the player;
        cameraAdapter.translate(0, 300);

    }

    @Override
    public void update(long delta) {
        cameraAdapter.translate(calculateDelta());
        lastPosition = playerController.getPosition();
        cameraAdapter.update();

    }


    public void draw(IGraphicsAdapter graphics) {
        cameraAdapter.draw(graphics);
    }

    /**
     * calculates the distance the camera needs to move.
     * @return a point with the distance the player have moved
     */
    private Point calculateDelta() {
        return playerController.getPosition().subtract(lastPosition);
    }

    //method needed for in-game menu
    public Point getCenterPosition() {
        return lastPosition;
    }

    public Point getPosition() {
        return lastPosition.subtract(1920/2, 1080/2 - 300);
    }
}
