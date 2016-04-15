package se.chalmers.get_rect.game;


import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.utilities.Point;

public class CameraManager implements IGameComponent{

    private ICameraAdapter cameraAdapter;
    private IPhysicsModel model;
    private Point playerPos;
    private Point cameraPos;


    public CameraManager(ICameraAdapter cameraAdapter, IPhysicsModel model){
        this.cameraAdapter = cameraAdapter;
        this.model = model;
        cameraPos = new Point(0,300);
        playerPos = model.getPosition();
        //Fix cameras first position
        cameraAdapter.translate(playerPos.add(cameraPos));
    }

    @Override
    public void update(double delta) {
        playerPos = model.getPosition();
        changeCameraPosition(delta);
        cameraAdapter.update(delta);

    }

    private void changeCameraPosition(double delta) {
        if(cameraPos.distanceTo(playerPos) > 300000) { //this distance is just outside the edge of the camera

            focusOnPlayer();

        } else {

            moveX(delta);
            moveY(delta);

        }

    }

    /**
     * this will instantly put the cameras centre at the player
     */
    private void focusOnPlayer() { // TODO: 16-04-13 Find a better name for this
        cameraAdapter.translate(playerPos.subtract(cameraPos).addY(210));
        cameraPos = cameraPos.add(playerPos.subtract(cameraPos).addY(210));
    }

    private void moveX(double delta) {
        move(cameraPos.deltaX(playerPos),350,deltaToVelocityX(delta));
    }

    private void moveY(double delta){
        if(playerPos.getY() != model.getPosition().getY()){
            move(cameraPos.deltaY(playerPos), 100, new Point(0,3));
        }
    }

    private Point deltaToVelocityX(double delta){
        int velocity = (int)(model.getVelocity().getX() * delta);
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

    public Point getCenterPosition() {
        return cameraPos;
    }

    public Point getPosition() {
        return cameraPos.subtract(GameConfig.SCREEN_WIDTH/2, GameConfig.SCREEN_HEIGHT/2);
    }
}
