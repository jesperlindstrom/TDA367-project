package se.chalmers.get_rect.game;


import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.player.PlayerController;

public class CameraManager {
    private int deltaX;
    private int deltaY;

    private ICameraAdapter cameraAdapter;
    private PlayerController playerController;

    public CameraManager(ICameraAdapter cameraAdapter, PlayerController playerController){
        this.cameraAdapter = cameraAdapter;
        this.playerController = playerController;
        deltaX = playerController.getxCoord();
        deltaY = playerController.getyCoord();
        cameraAdapter.translate(0, 300);
    }

    public void update() {
        cameraAdapter.translate(playerController.getxCoord() - deltaX, playerController.getyCoord() - deltaY);
        deltaX = playerController.getxCoord();
        deltaY = playerController.getyCoord();
        cameraAdapter.update();

    }

    public void draw(IGraphicsAdapter graphics) {
        cameraAdapter.draw(graphics);
    }

}
