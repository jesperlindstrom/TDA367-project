package se.chalmers.get_rect.utilities.debug;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.IGameComponent;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.physics.IPhysicsEngine;
import se.chalmers.get_rect.utilities.Point;

public class Debugger implements IGameComponent {
    private CameraManager camera;
    private FpsHandler fps;
    private PlayerHandler player;
    private PhysicsHandler physics;

    public Debugger(IPhysicsModel player, CameraManager camera, IPhysicsEngine physics) {
        this.camera = camera;
        this.fps = new FpsHandler();
        this.player = new PlayerHandler(player);
        this.physics = new PhysicsHandler(physics);
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
}
