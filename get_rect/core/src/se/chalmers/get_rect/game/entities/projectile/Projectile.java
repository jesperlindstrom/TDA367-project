package se.chalmers.get_rect.game.entities.projectile;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.utilities.Point;

public class Projectile implements IModel {
    private Point position;
    private int damage;
    private int speed;
    private IRectangleAdapter boundingBox;
    private static final int WIDTH = 50;//Ändra width och height beroende på projektil?
    private static final int HEIGHT = 50;

    public Projectile(Point position, IRectangleFactoryAdapter rectangleFactory){
        this.position = position;
        //this.damage = damage;
        //this.speed = speed;
        this.boundingBox = rectangleFactory.make(position.getxCoordinate(), position.getyCoordinate(), WIDTH, HEIGHT);

    }
    public IRectangleAdapter getBoundingBox() {return boundingBox;}

    @Override
    public void setX(int x) {

    }

    @Override
    public void setY(int y) {

    }

    @Override
    public void setPosition(int xCoordinate, int yCoordinate) {
        position = position.setPosition(xCoordinate, yCoordinate);
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
}
