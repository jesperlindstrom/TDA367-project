package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.SideData;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;

class Player implements IPhysicsModel {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private static final int JUMPSPEED = 60;
    private static final int MOVMENTSPEED = 30;
    private IRectangleAdapter boundingBox;
    private Point position;
    private Point velocity;
    private boolean isWalking;
    private boolean canJump;

    /**
     * Initialize a new player with fixed position and 10 hp and level 1.
     * @param rectangleFactory
     */
    public Player(IRectangleFactoryAdapter rectangleFactory) {
        position = new Point(20, 30);
        this.boundingBox = rectangleFactory.make(position.getX(), position.getY(), WIDTH, HEIGHT);
        this.isWalking = false;
        this.canJump = true;
        this.velocity = new Point(0, 0);
    }

    @Override
    public void update() {
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, SideData side, boolean isSolid) {
        if (side.bottom() && side.getSolidEdge().contains(Side.BOTTOM)) {
            canJump = true;
        } else {
            canJump = false;
        }
    }

    public void jump() {
        if (canJump) {
            velocity = velocity.setY(JUMPSPEED);
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

    }
}

