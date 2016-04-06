package se.chalmers.get_rect.game.entities.enemies.zombie;


import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.utilities.Point;

public class Zombie implements IModel {
    private Point position;
    private int health;
    private int damage;
    private int currentHealth;

    public Zombie(){
        this(new Point(0 ,0));
    }

    public Zombie(Point point){
        this.position = point;
        damage = 2;
        health = 5; //temp values
    }

    public int getX() {
        return position.getxCoodrinate();
    }

    public int getY() {
        return position.getyCoordinate();
    }

    public void setX(int x) {
        position.setxCoodrinate(x);
    }

    public void setY(int y) {
        position.setyCoordinate(y);
    }

    public void setPosition(int x, int y) {
        position.setPosition(x, y);
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

}
