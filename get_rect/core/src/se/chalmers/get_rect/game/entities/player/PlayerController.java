package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsController;
import se.chalmers.get_rect.game.entities.IView;



public class PlayerController implements IPhysicsController {
    private Player player;
    private IView view;
    private IInputAdapter input;
    private int yCoord;
    private int speedY;
    private int ground;
    private int timeSinceJump = 0;
    private float deltaInSec;
    private int maxJump = 50;

    public PlayerController(Player player, IView view, IInputAdapter input) {
        this.player = player;
        this.view = view;
        this.input = input;
    }

    @Override
    public void update(long delta) {
        //Section for player walking function
        //// TODO: 2016-04-06 Fix walking such as delta is in use.
        if(input.isKeyPressed(IInputAdapter.Keys.A)){
            player.setxCoordinate(player.getxCoordinate() - 3);
            player.setWalking(true);
        }else if(input.isKeyPressed(IInputAdapter.Keys.D)){
            player.setxCoordinate(player.getxCoordinate() + 3);
            player.setWalking(true);
        }else{
            player.setWalking(false);
        }
        //Section for player jump function
        if(input.isKeyPressed(IInputAdapter.Keys.SPACE) && !player.getJumping()){
            player.setJumping(true);
            setData(delta);
            ground = player.getyCoordinate();
        }
        if(player.getJumping()){
            jump();
        }
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        view.draw(graphics);
    }

    @Override
    public void getBoundingBox() {

    }

    private void setData(long delta){
        deltaInSec = (float)(delta / 10000000);
        ground = player.getyCoordinate();
        yCoord = ground + 1;
        speedY = 25;

    }

    private void jump(){
        double g = .04;
        speedY -= 1;
        timeSinceJump += deltaInSec;
        player.setyCoordinate((int)(yCoord + speedY*timeSinceJump - g*timeSinceJump*timeSinceJump));
        // And test that the character is not on the ground again.

        if (player.getyCoordinate() <= ground)
        {
            player.setyCoordinate(ground);
            timeSinceJump = 0;
            player.setJumping(false);
        }
    }


    public void setPosition(int x, int y){
        player.setCoordinates(x,y);
    }

}