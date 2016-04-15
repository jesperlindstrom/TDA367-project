package se.chalmers.get_rect.game.entities.npc.sawmillExpress;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.SideData;
import se.chalmers.get_rect.utilities.Point;

public class SawmillExpress implements IPhysicsModel {
    private Point position;
    private IRectangleAdapter boundingBox;
    private final float width = 219;
    private final float height = 276;
    private boolean wäääh;
    private int velocity = 3;

    public SawmillExpress(Point point, IRectangleFactoryAdapter rectangleFactory) {
        this.position = point;
        this.boundingBox = rectangleFactory.make(position, width, height);
        wäääh = false;

    }

    @Override
    public void update() {

    }

    public IRectangleAdapter getBoundingBox() {
        return boundingBox;
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, SideData collisionSide, boolean isSolid) {

    }

    @Override
    public void setPosition(Point position) {

    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setVelocity(Point velocity) {
    }

    public void setWäääh(boolean wäääh) {
        this.wäääh = wäääh;
    }

    public boolean isWäääh() {
        return wäääh;
    }

    public Point getVelocity(){
        return new Point(0,velocity);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
