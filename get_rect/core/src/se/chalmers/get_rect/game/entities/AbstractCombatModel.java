package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.event.EventSource;
import se.chalmers.get_rect.event.IEventListener;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractCombatModel extends AbstractPhysicsModel implements ICombatModel {
    private EventSource eventSource;
    private int currentHealth;
    private int maxHealth;
    private boolean isDead = false;
    private boolean knockback = false;

    protected AbstractCombatModel(Point position, Point velocity, boolean solid, boolean affectedByGravity, IRectangleFactoryAdapter rectangleFactory, int maxHealth) {
        super(position, velocity, solid, affectedByGravity, rectangleFactory);
        eventSource = new EventSource();
        this.maxHealth = maxHealth;
        this.currentHealth = this.maxHealth;
    }

    @Override
    public void setKnockback() {
        knockback = true;
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, CollisionData collisionSide, boolean isSolid) {
        super.onCollision(otherObject, collisionSide, isSolid);

        if (isSolid)
            knockback = false;
    }

    protected boolean isKnockbacked() {
        return knockback;
    }

    @Override
    public void addHealth(int health) {
        currentHealth += health;

        if(currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }

        if (currentHealth > 0) {
            isDead = false;
        }
    }

    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }

    @Override
    public void takeDamage(int dmg) {
        currentHealth -= dmg;

        if (currentHealth <= 0 && !isDead) {
            isDead = true;
            die();
        }
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    protected void die() {
        setShouldBeRemoved();
    }

    @Override
    public void removeListener(IEventListener o) {
        eventSource.removeListener(o);
    }

    @Override
    public void addListener(IEventListener o) {
        eventSource.addListener(o);
    }

    protected void triggerEvent(String type, String action) {
        eventSource.triggerEvent(type, action);
    }

    public void refillHealth(){
     this.currentHealth = maxHealth;
    }

    public void setHealth(int health){
        currentHealth = health;
        isDead = false;
        if (currentHealth > maxHealth){
            currentHealth = maxHealth;
        }
    }
}
