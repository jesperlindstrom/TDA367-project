package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.AbstractCombatModel;
import se.chalmers.get_rect.game.entities.AbstractPhysicsModel;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.npc.INpcModel;
import se.chalmers.get_rect.game.entities.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.SideData;
import se.chalmers.get_rect.utilities.Point;

public class Player extends AbstractCombatModel {
    private static final int WIDTH = 40;
    private static final int HEIGHT = 80;
    private static final int JUMP_SPEED = 90;
    private static final int MOVE_SPEED = 30;
    private boolean isWalking = false;
    private boolean canJump = true;
    private ProjectileFactory projectileFactory;
    private INpcModel interactableNPC;

    /**
     * Initialize a new player with fixed position and 10 hp and level 1.
     * @param rectangleFactory
     */
    public Player(IRectangleFactoryAdapter rectangleFactory) {
        super(new Point(0, 0), new Point(0, 0), false, rectangleFactory, 100);
        setBoundingBox(getPosition(), WIDTH, HEIGHT);

        projectileFactory = new ProjectileFactory(rectangleFactory);
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, SideData side, boolean isSolid) {
        if (isSolid && side.bottom()) {
            canJump = true;
        }

        if (otherObject instanceof INpcModel){
            interactableNPC = (INpcModel) otherObject;
        } else {
            interactableNPC = null;
        }
    }

    public void jump() {
        if (canJump) {
            setVelocity(getVelocity().setY(JUMP_SPEED));
            canJump = false;
        }
    }

    public void shoot(Point direction) {
        int BULLET_SPEED = 200; // todo: belongs in a weapon
        IPhysicsEntity projectile = projectileFactory.make("cluster", getPosition().addY(HEIGHT), direction.multiply(BULLET_SPEED));
        getScene().addPhysicsEntity(IScene.layer.FOREGROUND_EFFECTS, projectile);
    }

    public void moveLeft() {
        setVelocity(getVelocity().setX(-MOVE_SPEED));
        isWalking = true;
    }

    public void moveRight() {
        setVelocity(getVelocity().setX(MOVE_SPEED));
        isWalking = true;
    }

    public void stopMoving() {
        setVelocity(getVelocity().addX(-getVelocity().getX()/6));
        isWalking = false;
    }

    public boolean isWalking(){
        return isWalking;
    }

    public boolean canJump() {
        return canJump;
    }

    public void interact(){
        if(interactableNPC != null){
            interactableNPC.onInteract(this);
        }
    }

    public void flyHome() {
        setPosition(new Point(1900, 170));
    }

    @Override
    protected void die(){
        System.out.println("Player died!");
    }

    public INpcModel getCurrentNpc(){
        return interactableNPC;
    }
}