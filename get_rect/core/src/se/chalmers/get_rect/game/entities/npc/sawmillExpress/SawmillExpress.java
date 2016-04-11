package se.chalmers.get_rect.game.entities.npc.sawmillExpress;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.utilities.Point;

/**
 * Created by simsund on 2016-04-11.
 */
public class SawmillExpress implements IModel {
    private Point position;
    private IRectangleAdapter boundingBox;
    private final float width = 219;
    private final float height = 276;
    private boolean waaah;

    public SawmillExpress(Point point, IRectangleFactoryAdapter rectangleFactory) {
        this.position = point;
        this.boundingBox = rectangleFactory.make(position, width, height);
        waaah = false;

    }


    @Override
    public void setX(int x) {
        position = position.setX(x);
        boundingBox.setPosition(position);
    }

    @Override
    public void setY(int y) {
        position = position.setY(y);
        boundingBox.setPosition(position);
    }

    @Override
    public void setPosition(int x, int y) {
        position.setPosition(x, y);
        boundingBox.setPosition(position);
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

    public IRectangleAdapter getBoundingBox() {
        return boundingBox;
    }

    public void setWaaah(boolean waaah) {
        this.waaah = waaah;
    }

    public boolean isWaaah() {
        return waaah;
    }
}
