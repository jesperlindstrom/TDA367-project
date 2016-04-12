package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.IPhysicsController;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.projectile.Projectile;
import se.chalmers.get_rect.game.entities.projectile.ProjectileController;
import se.chalmers.get_rect.game.entities.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.game.scenes.TestScene;
import se.chalmers.get_rect.physics.ISolidObject;
import se.chalmers.get_rect.utilities.Point;


public class PlayerController implements IPhysicsController {
    public static final double MOVEMENT_SPEED = 30;
    private Player player;
    private IView view;
    private IInputAdapter input;
    private int yCoord;
    private int speedY;
    private int ground;
    private int timeSinceJump = 0;
    private IScene scene;
    private ProjectileFactory projectileFactory;
    private boolean isLeft;

    public PlayerController(Player player, IView view, IInputAdapter input, ProjectileFactory projectileFactory) {
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
            setData();
            ground = getPosition().getY();
        }
        if(player.getJumping()){
            jump(delta);
        }
        if(input.isKeyPressed(IInputAdapter.Keys.RIGHTKEY)){
            isLeft = false;
            shootProjectile(player, isLeft);
        }
        if(input.isKeyPressed(IInputAdapter.Keys.LEFTKEY)){
            isLeft = true;
            shootProjectile(player, isLeft);

            jump(delta);


        }
    }

    private Point deltaToVelocityX(double delta){
        double velocity = (MOVEMENT_SPEED * delta);
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

    private void shootProjectile(Player player, boolean isLeft) {
        System.out.println("Player position " + player.getPosition());
        ProjectileController projectile = projectileFactory.make(player.getPosition());
        scene.addEntity(IScene.layer.FOREGROUND_EFFECTS, projectile);
        projectile.setAngle(isLeft);
    }
}