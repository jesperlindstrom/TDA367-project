package se.chalmers.get_rect.game;


import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.utilities.Point;

public class CameraManager {
    private Point playerPos;
    private Point cameraPos;
    private Point velocityX;
    private Point velocityY;

    private ICameraAdapter cameraAdapter;
    private PlayerController playerController;

    public CameraManager(ICameraAdapter cameraAdapter, PlayerController playerController){
        this.cameraAdapter = cameraAdapter;
        this.playerController = playerController;
        cameraPos = new Point(0,300);
        playerPos = playerController.getPosition();
        velocityX = new Point(3,0);
        velocityY = new Point(0,3);
        //Fix cameras first position
        cameraAdapter.translate(cameraPos);


    }

    public void update() {
        playerPos = playerController.getPosition();
        moveX();
        moveY();
        cameraAdapter.update();

    }

    private void moveX() {
        move(cameraPos.deltaX(playerPos),350,velocityX);
    }

    private void moveY(){
        if(playerPos.getX() != playerController.getPosition().getX() && !playerController.isJumping()){
            move(cameraPos.deltaY(playerPos), 100, velocityY);
        }
    }

    private void move(int delta, int span, Point velocity){
        if(delta <= -span){
            cameraAdapter.translate(velocity);
            cameraPos = cameraPos.add(velocity);
        } else if (delta >= span) {
            cameraAdapter.translate(velocity.inverse());
            cameraPos = cameraPos.subtract(velocity);
        }
    }

    public void draw(IGraphicsAdapter graphics) {
        cameraAdapter.draw(graphics);
    }

}
