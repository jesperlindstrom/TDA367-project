package se.chalmers.get_rect.game.entities.npc.sawmillExpress;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.physics.ISolidObject;
import se.chalmers.get_rect.utilities.Point;

/**
 * Created by simsund on 2016-04-11.
 */
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
    public void setX(int x) {

    }

    @Override
    public void setY(int y) {

    }

    @Override
    public void setPosition(int x, int y) {

    }

    @Override
    public void update() {

    }

    public IRectangleAdapter getBoundingBox() {
        return boundingBox;
    }

    @Override
    public void onCollision(ISolidObject otherObject) {

    }

    @Override
    public void setPosition(Point position) {

    }

    @Override
    public Point getPosition() {
        return null;
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
}
