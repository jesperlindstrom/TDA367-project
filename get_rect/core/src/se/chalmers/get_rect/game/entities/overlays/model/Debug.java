package se.chalmers.get_rect.game.entities.overlays.model;

import se.chalmers.get_rect.game.camera.CameraManager;
import se.chalmers.get_rect.game.entities.AbstractModel;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.physics.IPhysicsEngine;
import se.chalmers.get_rect.utilities.Point;

public class Debug extends AbstractModel {
    private CameraManager camera;
    private FrameRate fps;
    private IPhysicsModel player;
    private IPhysicsEngine physics;

    public Debug(IPhysicsModel player, CameraManager camera, IPhysicsEngine physics) {
        super(new Point(0, 0));
        this.fps = new FrameRate();
        this.camera = camera;
        this.player = player;
        this.physics = physics;
    }

    public FrameRate getFrameRate() {
        return fps;
    }

    public IPhysicsModel getPlayer() {
        return player;
    }

    public IPhysicsEngine getPhysics() {
        return physics;
    }

    @Override
    public void update(double delta) {
        fps.update(delta);
    }

    public Point getCameraPosition() {
        return camera.getPosition();
    }
}
