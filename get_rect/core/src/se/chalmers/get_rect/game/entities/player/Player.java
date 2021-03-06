package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.item.model.IMelee;
import se.chalmers.get_rect.game.entities.item.model.IRanged;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.utilities.Point;

public class Player extends AbstractCombatModel implements IInteractorModel {
    private final int width;
    private final int height;
    private final int jumpSpeed;
    private final int moveSpeed;
    private boolean isWalking = false;
    private boolean isRiding = false;
    private boolean canJump = true;
    private boolean hasFoundHunch = false;
    private IInteractableModel interactableNPC;
    private IMelee meleeWeapon;
    private IRanged rangedWeapon;
    private IWeapon activeWeapon;


    public Player(IRectangleFactoryAdapter rectangleFactory, int width, int height, int jumpSpeed, int moveSpeed) {
        super(new Point(0, 0), new Point(0, 0), false, true, rectangleFactory, 100);
        this.width = width;
        this.height = height;
        this.jumpSpeed = jumpSpeed;
        this.moveSpeed = moveSpeed;

        setBoundingBox(this.width, this.height);
    }


    public Player(IRectangleFactoryAdapter rectangleFactory) {
        this(rectangleFactory, 68, 151, 90, 40);
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, CollisionData side, boolean isSolid) {
        if (isSolid && side.bottom()) {
            canJump = true;
        }

        if (otherObject instanceof IInteractableModel){
            interactableNPC = (IInteractableModel) otherObject;
        }
    }

    public void jump() {
        if (canJump) {
            setVelocity(getVelocity().setY(jumpSpeed));
            canJump = false;
        }
    }

    @Override
    public void update(double delta) {
        if (interactableNPC != null) {
            if (getBoundingBox().intersects(interactableNPC.getBoundingBox()) == null) {
                interactableNPC = null;
            }
        }
        activeWeapon.update(delta);

    }

    public void use(Point direction) {
        activeWeapon.use(direction, getScene());
    }

    public void moveLeft() {
        setVelocity(getVelocity().setX(-getSpeed()));
        isWalking = true;
    }

    public void moveRight() {
        setVelocity(getVelocity().setX(getSpeed()));
        isWalking = true;
    }

    public void stopMoving() {
        int velX;

        if (getVelocity().getX() > -10 && getVelocity().getX() < 10) {
            velX = -getVelocity().getX();
        } else {
            velX = -getVelocity().getX()/10;
        }

        setVelocity(getVelocity().addX(velX));
        isWalking = false;
    }

    private int getSpeed(){
        if (isRiding)
            return moveSpeed*2;
        return moveSpeed;
    }

    public boolean isWalking(){
        return isWalking;
    }

    public boolean canJump() {
        return canJump;
    }

    @Override
    public void interact(){
        if(interactableNPC != null){
            interactableNPC.onInteract(this);
        }
    }

    @Override
    protected void die(){
        triggerEvent("player", "died");
    }

    public IInteractableModel getCurrentNpc(){
        return interactableNPC;
    }

    public void switchWeapon() {
        if (activeWeapon instanceof IMelee){
            setActiveWeapon(rangedWeapon);
        } else {
            setActiveWeapon(meleeWeapon);
        }
    }

    public IWeapon getActiveWeapon(){
        return activeWeapon;
    }

    public void addNewWeapon(IWeapon model) {
        if (model instanceof IMelee) {
            meleeWeapon = (IMelee)model;
        } else if (model instanceof IRanged) {
            rangedWeapon = (IRanged)model;
        }
        model.setFound(true);
        setActiveWeapon(model);
    }

    @Override
    public void setScene(IEntityHolder scene) {
        super.setScene(scene);
    }

    public void setActiveWeapon(IWeapon weapon) {
        activeWeapon = weapon;
    }

    public IMelee getMeleeWeapon() {
        return meleeWeapon;
    }

    public IRanged getRangedWeapon() {
        return rangedWeapon;
    }

    public boolean isRiding(){
        return isRiding;
    }

    public void setRiding(boolean riding) {
        isRiding = riding;
    }

    public boolean hasFoundHunch() {
        return hasFoundHunch;
    }

    public void setHasFoundHunch(boolean hasFoundHunch) {
        this.hasFoundHunch = hasFoundHunch;
    }

}