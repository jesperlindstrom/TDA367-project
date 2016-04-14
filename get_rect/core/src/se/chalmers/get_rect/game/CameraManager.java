package se.chalmers.get_rect.game;


import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.utilities.Point;

public class CameraManager implements IGameComponent{

    private ICameraAdapter cameraAdapter;
    private IModel player;
    private Point playerPos;
    private Point cameraPos;


    public CameraManager(ICameraAdapter cameraAdapter, IModel player){
        this.cameraAdapter = cameraAdapter;
        this.player = player;
        cameraPos = new Point(0,300);
        playerPos = player.getPosition();
        cameraAdapter.translate(cameraPos);
    }

    @Override
    public void update(double delta) {
        playerPos = player.getPosition();
        moveX(delta);
        moveY(delta);
        cameraAdapter.update(delta);

    }

    private void moveX(double delta) {
        move(cameraPos.deltaX(playerPos),350,deltaToVelocityX(delta));
    }

    private void moveY(double delta){
        if(playerPos.getX() != player.getPosition().getX()){
            move(cameraPos.deltaY(playerPos), 100, new Point(0,3));
        }
    }

    private Point deltaToVelocityX(double delta){
        int velocity = (int)(-30 * delta);
        return new Point(velocity,0);
    }

    private void move(double delta, int span, Point velocity){
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
        return cameraPos.subtract(GameConfig.SCREEN_WIDTH/2, GameConfig.SCREEN_HEIGHT/2);
    }
}
