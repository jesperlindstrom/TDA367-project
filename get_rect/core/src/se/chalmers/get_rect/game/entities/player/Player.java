package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.scenes.IEntityHolder;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.SideData;
import se.chalmers.get_rect.utilities.Point;

public class Player implements IPhysicsModel {
    private static final int WIDTH = 40;
    private static final int HEIGHT = 80;
    private static final int JUMPSPEED = 90;
    private static final int MOVMENTSPEED = 30;
    private IRectangleAdapter boundingBox;
    private Point position;
    private Point velocity;
    private boolean isWalking;
    private boolean canJump;
    private boolean secondJump;
    private ProjectileFactory projectileFactory;
    private IEntityHolder scene;

    /**
     * Initialize a new player with fixed position and 10 hp and level 1.
     * @param rectangleFactory
     */
    public Player(IRectangleFactoryAdapter rectangleFactory) {
        position = new Point(20, 30);
        this.boundingBox = rectangleFactory.make(position.getX(), position.getY(), WIDTH, HEIGHT);
        this.isWalking = false;
        this.canJump = true;
        this.secondJump = false;
        this.velocity = new Point(0, 0);
        this.projectileFactory = new ProjectileFactory(rectangleFactory);
    }

    @Override
    public void update() {
    }

    @Override
    public void setScene(IEntityHolder scene) {
        this.scene = scene;
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, SideData side, boolean isSolid) {
        if (isSolid && side.bottom()) {
            canJump = true;
        }
    }

    public void jump() {
        if (canJump) {
            velocity = velocity.setY(JUMPSPEED);
            canJump = false;
            secondJump = true;
        } else if (secondJump) {
            velocity = velocity.setY(JUMPSPEED);
            secondJump = false;
        }
    }

    public void moveLeft() {
        velocity = velocity.setX(-MOVMENTSPEED);
        isWalking = true;
    }

    public void moveRight() {
        velocity = velocity.setX(MOVMENTSPEED);
        isWalking = true;
    }

    public void stopMoving() {
        velocity = velocity.addX(-velocity.getX()/6);
        isWalking = false;
    }

    @Override
    public void setPosition(Point point) {
        position = new Point(point);
        boundingBox.setPosition(position);
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setVelocity(Point velocity) {
        this.velocity = velocity;
    }

    @Override
    public Point getVelocity() {
        return velocity;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean shouldBeRemoved() {
        return false;
    }

    public boolean isWalking(){
        return isWalking;
    }

    public boolean canJump() {
        return canJump;
    }

    public IRectangleAdapter getBoundingBox() {
        return boundingBox;
    }

    public void shoot(Point direction) {
        int BULLET_SPEED = 200;
        IPhysicsEntity projectile = projectileFactory.make("cluster", position.addY(HEIGHT), direction.multiply(BULLET_SPEED));
        scene.addPhysicsEntity(IScene.layer.FOREGROUND_EFFECTS, projectile);
    }
}

