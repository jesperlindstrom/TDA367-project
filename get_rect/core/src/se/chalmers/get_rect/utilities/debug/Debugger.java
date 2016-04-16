package se.chalmers.get_rect.utilities.debug;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.physics.IPhysicsEngine;
import se.chalmers.get_rect.utilities.Point;

/**
 *  class for debugging stats
 *  set booleans in GameConfig
 *  and it will print values in
 *  the top left corner
 */
public class Debugger {
    private CameraManager camera;
    private FpsHandler fps;
    private PlayerHandler player;
    private PhysicsHandler physics;

    public Debugger(IPhysicsModel player, CameraManager camera) {
        this.camera = camera;
        this.fps = new FpsHandler();
        this.player = new PlayerHandler(player);
        this.physics = new PhysicsHandler();
    }

    public void update(double delta) {
        fps.update(delta);
    }

    public void draw(IGraphicsAdapter graphics) {
        // Draw top left stats
        Point point = camera.getPosition().addY(1095);
        point = fps.draw(graphics, point);
        point = player.draw(graphics, point);

        // Draw physics bounding boxes
        physics.draw(graphics);
    }

    public void setPhysicsEngine(IPhysicsEngine engine) {
        physics.setPhysicsEngine(engine);
    }
}
