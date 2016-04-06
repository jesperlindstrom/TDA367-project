package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.IPhysicsController;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.physics.ISolidObject;

public class PlayerController implements IPhysicsController {
    private Player player;
    private IView view;
    private IInputAdapter input;
    private int t0;
    private int xCoord;
    private int yCoord;
    private int speed0Y;
    private int speed;


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
            player.setX(player.getX() - 3);
            player.setWalking(true);
        }else if(input.isKeyPressed(IInputAdapter.Keys.D)){
            player.setX(player.getX() + 3);
            player.setWalking(true);
        }else{
            player.setWalking(false);
        }
        //Section for player jump function
        if(input.isKeyPressed(IInputAdapter.Keys.SPACE) && !player.getJumping()){
            player.setJumping(true);
            t0=currentTime();
            xCoord = player.getX();
            yCoord = player.getY();
           // speed0X = speed;
          //  speed0Y += speedJump;
        }
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        view.draw(graphics);
    }

    @Override
    public void getBoundingBox() {

    }

    private int currentTime(){
        return 1;
    }

    public void setPosition(int x, int y){
        player.setPosition(x, y);
    }

    public int getxCoord() {
        return player.getX();
    }
}