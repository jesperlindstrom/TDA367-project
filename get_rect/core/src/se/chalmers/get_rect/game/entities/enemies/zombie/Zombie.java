package se.chalmers.get_rect.game.entities.enemies.zombie;


import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.physics.ISolidObject;
import se.chalmers.get_rect.utilities.Point;

public class Zombie implements IPhysicsModel {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private Point position;
    private IRectangleAdapter boundingBox;
    private int health;
    private int damage;
    private int currentHealth;
    private boolean walking;
    private double velocity;

    public Zombie(IRectangleFactoryAdapter rectangleFactory){
        this(new Point(0 ,0), rectangleFactory);
    }

    public Zombie(Point point, IRectangleFactoryAdapter rectangleFactory){
        this.position = point;
        this.boundingBox = rectangleFactory.make(position.getX(), position.getY(), WIDTH, HEIGHT);
        damage = 2;
        health = 5; //temp values
    }


    public IRectangleAdapter getBoundingBox() {
        return boundingBox;
    }

    @Override
    public void onCollision(ISolidObject otherObject) {

    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }


    public void setX(int x) {
        position = position.setX(x);
        boundingBox.setPosition(position);
    }

    public void setY(int y) {
        position = position.setY(y);
        boundingBox.setPosition(position);
    }


    public void setPosition(int x, int y) {
        position = position.setPosition(x, y);
        boundingBox.setPosition(position);
    }

    @Override
    public void setPosition(Point point) {
        position = new Point(point);
        boundingBox.setPosition(position);
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public void setVelocity(Point velocity) {

    }

    @Override
    public void update() {

    }

    public Point getVelocity(){
        return new Point((int)velocity, 0);
    }

}
