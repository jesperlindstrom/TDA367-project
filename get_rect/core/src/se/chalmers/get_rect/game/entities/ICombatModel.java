package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.event.IEventSource;

public interface ICombatModel extends IPhysicsModel, IEventSource {
    void addHealth(int health);
    int getCurrentHealth();
    void takeDamage(int dmg);
    int getMaxHealth();
}
