package se.chalmers.get_rect.game;


import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.player.PlayerController;

public class CameraManager {


    private int deltaX;
    private int deltaY;

    private ICameraAdapter cameraAdapter;
    private PlayerController playerController;

    //TODO Use ponit
    public CameraManager(ICameraAdapter cameraAdapter, PlayerController playerController){
        this.cameraAdapter = cameraAdapter;
        this.playerController = playerController;
        deltaX = playerController.getPosition().getxCoodrinate();
        deltaY = playerController.getPosition().getyCoordinate();
        cameraAdapter.translate(-1920/2, -1080/6);
    }

    public void update() {
        cameraAdapter.translate(playerController.getPosition().getxCoodrinate() - deltaX, playerController.getPosition().getyCoordinate() - deltaY);
        deltaX = playerController.getPosition().getxCoodrinate();
        deltaY = playerController.getPosition().getyCoordinate();
        cameraAdapter.update();

    }

    public void draw(IGraphicsAdapter graphics) {
        cameraAdapter.draw(graphics);
    }

}
