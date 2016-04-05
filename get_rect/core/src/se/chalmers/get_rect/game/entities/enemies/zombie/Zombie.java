package se.chalmers.get_rect.game.entities.enemies.zombie;


import se.chalmers.get_rect.game.entities.IModel;

public class Zombie implements IModel {
    private int x;
    private int y;
    private int health;
    private int damage;
    private int currentHealth;

    public Zombie(){
        this(0,0);
    }

    public Zombie(int x, int y){
        this.x = x;
        this.y = y;
        damage = 2;
        health = 5; //temp values
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
