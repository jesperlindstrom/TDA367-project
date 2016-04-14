package se.chalmers.get_rect.game.entities.player;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.scenes.IScene;


public class PlayerController implements IController {
    private Player player;
    private IView view;
    private IInputAdapter input;
    private IScene scene;

    public PlayerController(Player player, IView view, IInputAdapter input) {
        this.player = player;
        this.view = view;
        this.input = input;
    }

    @Override
    public void update() {
        //Section for player walking function
        player.move(input);
        //Section for player jump function
        if(input.isKeyPressed(IInputAdapter.Keys.SPACE) && !player.getJumping()){
            player.setJumping(true);
            setData();
            ground = getPosition().getY();
        }
        if(player.getJumping()){
            jump();

        }
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


    public void setScene(IScene scene) {
        this.scene = scene;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

}