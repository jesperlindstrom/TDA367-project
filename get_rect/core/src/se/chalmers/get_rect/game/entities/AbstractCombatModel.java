package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractCombatModel extends AbstractPhysicsModel implements ICombatModel {

    private int currentHealth;
    private int maxHealth;

    protected AbstractCombatModel(Point position, Point velocity, boolean solid, IRectangleFactoryAdapter rectangleFactory, int maxHealth) {
        super(position, velocity, solid, rectangleFactory);
        this.maxHealth = maxHealth;
        this.currentHealth = this.maxHealth;
    }


    @Override
    public void addHealth(int health) {
        currentHealth += health;
        if(currentHealth > maxHealth){
            currentHealth = maxHealth;
        }
    }

    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }

    @Override
    public void takeDamage(int dmg) {
        currentHealth -= dmg;
        if (currentHealth <= 0){
            die();
        }
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    protected void die(){
        setShouldBeRemoved();
    }
}
