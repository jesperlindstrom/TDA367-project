package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.AbstractCombatModel;
import se.chalmers.get_rect.game.entities.IInteractableModel;
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
    private static final int MOVE_SPEED = 40;
    private boolean isWalking = false;
    private boolean canJump = true;
    private ProjectileFactory projectileFactory;
    private int bulletSpeed = 200;//should be in projectile/weapon
    private IInteractableModel interactableNPC;
    private String clusterBoolean = "normal";

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

        if (otherObject instanceof IInteractableModel){
            interactableNPC = (IInteractableModel) otherObject;
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
        // todo: bulletSpeed belongs in a weapon
        IPhysicsEntity projectile = projectileFactory.make(clusterBoolean, getPosition().addY(HEIGHT), direction.multiply(bulletSpeed), this);
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
        setPosition(new Point(3420, 600));
    }

    @Override
    protected void die(){
        System.out.println("you died");
        addHealth(getMaxHealth());
    }

    public IInteractableModel getCurrentNpc(){
        return interactableNPC;
    }

    public void switchWeapon() {

        if (bulletSpeed != 200){
            bulletSpeed = 200;
            clusterBoolean = "normal";
        } else {
            bulletSpeed = 20;
            clusterBoolean = "melee";
        }
    }

}