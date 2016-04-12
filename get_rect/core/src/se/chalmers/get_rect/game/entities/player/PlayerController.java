package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsController;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.physics.ISolidObject;
import se.chalmers.get_rect.utilities.Point;


public class PlayerController implements IPhysicsController {
    public static final double MOVEMENT_SPEED = 5;
    private Player player;
    private IView view;
    private IInputAdapter input;
    private int yCoord;
    private int speedY;
    private int ground;
    private int timeSinceJump = 0;
    private float deltaInSec;
    private IScene scene;

    public PlayerController(Player player, IView view, IInputAdapter input) {
        this.player = player;
        this.view = view;
        this.input = input;
    }

    @Override
    public void update(double delta) {
        Point velocity = deltaToVelocityX(delta);
        //Section for player walking function
        if(input.isKeyPressed(IInputAdapter.Keys.A)){
            player.setPosition(getPosition().subtract(velocity));
            player.setWalking(true);
        }else if(input.isKeyPressed(IInputAdapter.Keys.D)){
            player.setPosition(getPosition().add(velocity));
            player.setWalking(true);
        }else{
            player.setWalking(false);
        }
        //Section for player jump function
        if(input.isKeyPressed(IInputAdapter.Keys.SPACE) && !player.getJumping()){
            player.setJumping(true);
            setData(delta);
            ground = getPosition().getY();
        }
        if(player.getJumping()){
            jump();

        }
    }

    private Point deltaToVelocityX(double delta){
        double velocity = (MOVEMENT_SPEED * delta);
        System.out.println("Delta in use: " + delta);
        System.out.println("Player velocity: " + velocity);
        return new Point((int)velocity,0);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        view.draw(graphics);
    }

    @Override
    public IRectangleAdapter getBoundingBox() {
        return player.getBoundingBox();
    }

    @Override
    public void onCollision(ISolidObject otherObject) {

    }

    private void setData(double delta){
        deltaInSec = (float)(delta / 10000000);
        ground = getPosition().getY();
        yCoord = ground + 1;
        speedY = 25;

    }

    private void jump(){
        double g = .04;
        speedY -= 1;
        timeSinceJump += deltaInSec;
        player.setY((int)(yCoord + speedY*timeSinceJump - g*timeSinceJump*timeSinceJump));
        // And test that the character is not on the ground again.

        if (getPosition().getY() <= ground)
        {
            player.setY(ground);
            timeSinceJump = 0;
            player.setJumping(false);
        }
    }


    public void setPosition(int x, int y){
        player.setPosition(x, y);
    }

    public void setPosition(Point position){
        player.setPosition(position);
    }

    public Point getPosition(){
        return player.getPosition();
    }

    public boolean isJumping(){
        return player.getJumping();
    }

    public void setScene(IScene scene) {
        this.scene = scene;
    }

}