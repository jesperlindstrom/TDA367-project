package se.chalmers.get_rect.game.entities;

public interface ICombatModel extends IPhysicsModel {
    void addHealth(int health);
    int getCurrentHealth();
    void takeDamage(int dmg);
    int getMaxHealth();
}
