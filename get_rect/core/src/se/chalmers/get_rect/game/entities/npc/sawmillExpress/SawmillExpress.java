package se.chalmers.get_rect.game.entities.npc.sawmillExpress;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.SideData;
import se.chalmers.get_rect.utilities.Point;

public class SawmillExpress implements IPhysicsModel {
    private static final int SPEED = 50;
    private Point position;
    private Point velocity;
    private IRectangleAdapter boundingBox;
    private final float width = 219;
    private final float height = 276;
    private boolean wäääh;

    public SawmillExpress(Point point, IRectangleFactoryAdapter rectangleFactory) {
        this.position = point;
        this.velocity = new Point(0, 0);
        this.boundingBox = rectangleFactory.make(position, width, height);
        wäääh = false;
    }

    @Override
    public void update() {
        if (wäääh) {
            velocity = new Point(0, SPEED);
        }
    }

    @Override
    public void setScene(IScene scene) {

    }

    public IRectangleAdapter getBoundingBox() {
        return boundingBox;
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, SideData collisionSide, boolean isSolid) {
        if (otherObject.getClass().equals(Player.class)) {
            wäääh = true;
        }
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
        this.boundingBox.setPosition(position);
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setVelocity(Point velocity) {
        this.velocity = velocity;
    }

    public void setWäääh(boolean wäääh) {
        this.wäääh = wäääh;
    }

    public boolean isWäääh() {
        return wäääh;
    }

    public Point getVelocity(){
        return velocity;
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
