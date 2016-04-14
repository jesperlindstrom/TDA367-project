package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.physics.ISolidObject;
import se.chalmers.get_rect.utilities.Point;


public class PlayerController implements IController, ISolidObject {
    public static final double MOVEMENT_SPEED = 30;
    private Player player;
    private IView view;
    private IInputAdapter input;
    private int yCoord;
    private int speedY;
    private int ground;
    private int timeSinceJump = 0;
    private IScene scene;

    public PlayerController(Player player, IView view, IInputAdapter input) {
        this.player = player;
        this.view = view;
        this.input = input;
    }

    @Override
    public void update(double delta) {
        //Section for player walking function
        player.move(input);
        //Section for player jump function
        if(input.isKeyPressed(IInputAdapter.Keys.SPACE) && !player.getJumping()){
            player.setJumping(true);
            setData();
            ground = getPosition().getY();
        }
        if(player.getJumping()){
            jump(delta);

        }
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

    private void setData(){
        ground = getPosition().getY();
        yCoord = ground + 1;
        speedY = 50;

    }

    private void jump(double delta){
        double g = .04;
        speedY -= 1*delta*10;
        timeSinceJump += delta * 10; //delta to second
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

    public Point getVelocity(){
        return player.getMoveVelocity();
    }

}