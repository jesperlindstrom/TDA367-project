package se.chalmers.get_rect.game.entities.enemies.zombie;


import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.utilities.Point;

public class Zombie implements IModel {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private Point position;
    private IRectangleAdapter boundingBox;
    private int health;
    private int damage;
    private int currentHealth;
    private boolean walking;

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

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }


    public void setX(int x) {
        position = position.setX(x);
    }

    public void setY(int y) {
        position = position.setY(y);
    }

    public void setPosition(int x, int y) {
        position = position.setPosition(x, y);
    }

    @Override
    public void setPosition(Point point) {
        position = new Point(point);
    }

    public Point getPosition() {
        return position;
    }

    public int getDamage(){
        return damage;
    }

    public int getHealth(){
        return health;
    }

    public int getCurrentHealth(){
        return currentHealth;
    }

    public boolean isWalking() {
        return walking;
    }
}
