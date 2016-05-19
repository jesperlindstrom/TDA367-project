package se.chalmers.get_rect.game.entities;


import com.google.inject.Inject;
import com.google.inject.name.Named;
import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.adapters.ICameraFactoryAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;

import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.game.IGameComponent;
import se.chalmers.get_rect.utilities.Point;

public class EntityCamera implements ICamera {
    private static final int FOLLOW_SPEED = 50;
    private static final int SPAN_X = 350;
    private static final int SPAN_Y = 200;
    private ICameraAdapter cameraAdapter;
    private IPhysicsModel model;
    private Point cameraPos;

    @Inject
    public EntityCamera(ICameraFactoryAdapter cameraFactory, @Named("Player") IPhysicsModel model){
        this.cameraAdapter = cameraFactory.make(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        this.model = model;
        cameraPos = new Point(0, 0);
    }

    public void draw(IGraphicsAdapter graphics) {
        cameraAdapter.draw(graphics);
    }

    @Override
    public Point getPosition() {
        return cameraPos.subtract((int)cameraAdapter.getWidth() / 2, (int)cameraAdapter.getHeight() / 2);
    }

    public void update(double delta) {
        Point entityPosition = model.getPosition().addY(100);
        Point entityVelocity = model.getVelocity();

        if (isOutOfBounds(entityPosition)) {
            snapToPosition(entityPosition);
        } else {
            easeToPosition(entityPosition, entityVelocity, delta);
        }

        cameraAdapter.update(delta);
    }

    /**
     * This will instantly put the camera at the position
     * @param newPosition
     */
    @Override
    public void snapToPosition(Point newPosition) {
        Point difference = newPosition.subtract(cameraPos);
        cameraAdapter.translate(difference);
        cameraPos = cameraPos.add(difference);
    }

    @Override
    public ICameraAdapter getAdapter() {
        return cameraAdapter;
    }

    private boolean isOutOfBounds(Point pos) {
        if (Math.abs(cameraPos.deltaX(pos)) > cameraAdapter.getWidth()/2)
            return true;

        if (Math.abs(cameraPos.deltaY(pos)) > cameraAdapter.getHeight()/2)
            return true;

        return false;
    }

    /**
     * Smoothly follow the position
     * @param pos
     * @param vel
     * @param delta
     */
    private void easeToPosition(Point pos, Point vel, double delta) {
        int cameraVelX = velocityToDelta(vel.getX(), delta);
        move(cameraPos.deltaX(pos), SPAN_X, new Point(cameraVelX, 0));

        int cameraVelY = velocityToDelta(vel.getY(), delta);
        move(cameraPos.deltaY(pos), SPAN_Y, new Point(0, cameraVelY));
    }

    /**
     * Calculate the velocity based on the delta
     * @param velocity
     * @param delta
     * @return
     */
    private int velocityToDelta(int velocity, double delta) {
        return (int)(getFollowSpeed(velocity) * delta);
    }

    /**
     * Use the Entity velocity if any, or fall back to the camera speed
     * @param velocity Entity velocity
     * @return Velocity
     */
    private int getFollowSpeed(int velocity) {
        if (velocity != 0) {
            return Math.abs(velocity);
        }

        return FOLLOW_SPEED;
    }

    /**
     * Move if the the difference is larger than the max difference
     * @param difference
     * @param maxDifference
     * @param velocity
     */
    private void move(double difference, int maxDifference, Point velocity) {
        // Only move if the difference is with in the span
        if (Math.abs(difference) < maxDifference) return;

        // If we're moving in the opposite direction
        if (difference >= -maxDifference) {
            velocity = velocity.multiply(-1);
        }

        cameraAdapter.translate(velocity);
        cameraPos = cameraPos.add(velocity);
    }
}
