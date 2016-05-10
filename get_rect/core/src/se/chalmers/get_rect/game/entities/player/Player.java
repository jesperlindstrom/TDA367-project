package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.item.IMelee;
import se.chalmers.get_rect.game.entities.item.IWeapon;
import se.chalmers.get_rect.game.entities.item.ItemFactory;
import se.chalmers.get_rect.game.entities.projectile.ProjectileFactory;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.SideData;
import se.chalmers.get_rect.utilities.Point;

import java.util.HashMap;
import java.util.Map;

public class Player extends AbstractCombatModel implements IInteractorModel {
    private static final int WIDTH = 40;
    private static final int HEIGHT = 80;
    private static final int JUMP_SPEED = 90;
    private static final int MOVE_SPEED = 40;
    private static final int MELEE = 1;
    private static final int RANGED = 2;
    private boolean isWalking = false;
    private boolean canJump = true;
    private IInteractableModel interactableNPC;
    private Map<Integer, IEntity> weapons;
    private IEntity activeWeapon;
    private ProjectileFactory projectileFactory;

    /**
     * Initialize a new player with fixed position and 10 hp and level 1.
     * @param rectangleFactory
     */
    public Player(IRectangleFactoryAdapter rectangleFactory, ProjectileFactory projectileFactory, ItemFactory itemFactory) {
        super(new Point(0, 0), new Point(0, 0), false, rectangleFactory, 100);
        setBoundingBox(WIDTH, HEIGHT);
        weapons = new HashMap<>();

        this.projectileFactory = projectileFactory;

        // TODO fulhax fixthisplz
        addNewWeapon(itemFactory.make("pistol", this));
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, SideData side, boolean isSolid) {
        if (isSolid && side.bottom()) {
            canJump = true;
        }

        if (otherObject instanceof IInteractableModel){
            interactableNPC = (IInteractableModel) otherObject;
        }
    }

    public void jump() {
        if (canJump) {
            setVelocity(getVelocity().setY(JUMP_SPEED));
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
    }

    public void shoot(Point direction) {
        ((IWeapon)activeWeapon.getModel()).use(direction, getScene());
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

    @Override
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
    }

    public IInteractableModel getCurrentNpc(){
        return interactableNPC;
    }

    public void switchWeapon() {

        if (activeWeapon instanceof IMelee){
            setWeapon(weapons.get(RANGED));
        } else {
            setWeapon(weapons.get(MELEE));
        }
    }

    public IWeapon getActiveWeapon(){
        return (IWeapon)activeWeapon.getModel();
    }

    public void addNewWeapon(IEntity entity) {
        if (entity.getModel() instanceof IWeapon) {
            weapons.put(entity instanceof IMelee ? MELEE : RANGED, entity);
            setWeapon(entity);
        }
    }

    @Override
    public void setScene(IEntityHolder scene) {
        super.setScene(scene);
        scene.add(activeWeapon);
    }

    private void setWeapon(IEntity weapon) {
        if (activeWeapon != null ) {
            ((IWeapon)activeWeapon.getModel()).remove();
        }
        activeWeapon = weapon;
        if (getScene() != null) {
            getScene().add(weapon);
        }
    }

}