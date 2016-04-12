package se.chalmers.get_rect.game;


import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.utilities.Point;

public class CameraManager implements IGameComponent{

    private ICameraAdapter cameraAdapter;
    private PlayerController playerController;
    private Point playerPos;
    private Point cameraPos;


    public CameraManager(ICameraAdapter cameraAdapter, PlayerController playerController){
        this.cameraAdapter = cameraAdapter;
        this.playerController = playerController;
        cameraPos = new Point(0,300);
        playerPos = playerController.getPosition();
        //Fix cameras first position
        cameraAdapter.translate(cameraPos);
    }

    @Override
    public void update(long delta) {
        playerPos = playerController.getPosition();
        moveX(delta);
        moveY();
        cameraAdapter.update();

    }

    private void moveX(long delta) {
        move(cameraPos.deltaX(playerPos),350,deltaToVelocityX(delta));
    }

    private void moveY(){
        //TODO can't implement because we do not have any platforms to jump on
        if(playerPos.getX() != playerController.getPosition().getX() && !playerController.isJumping()){
            move(cameraPos.deltaY(playerPos), 100, new Point(0,3));
        }
    }

    private Point deltaToVelocityX(long delta){
        int velocity = (int)(PlayerController.MOVEMENT_SPEED * (delta/1000000));
        return new Point(velocity,0);
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


    //method needed for in-game menu
    public Point getCenterPosition() {
        return cameraPos;
    }

    public Point getPosition() {
        return cameraPos.subtract(1920/2, 1080/2);
    }
}
