package se.chalmers.get_rect.game.entities.npc.sawmillExpress;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.AbstractPhysicsModel;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.SideData;
import se.chalmers.get_rect.utilities.Point;

public class SawmillExpress extends AbstractPhysicsModel {
    private static final int SPEED = 50;
    private static final int WIDTH = 219;
    private static final int HEIGHT = 276;
    private boolean wäääh = false;

    public SawmillExpress(Point point, IRectangleFactoryAdapter rectangleFactory) {
        super(point, new Point(0, 0), false, rectangleFactory);
        setBoundingBox(getPosition(), WIDTH, HEIGHT);
    }

    @Override
    public void update() {
        if (wäääh) {
            setVelocity(new Point(0, SPEED));
        }
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, SideData collisionSide, boolean isSolid) {
        if (otherObject.getClass().equals(Player.class)) {
            wäääh = true;
        }
    }

    public boolean isWäääh() {
        return wäääh;
    }
}
