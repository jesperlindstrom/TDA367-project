package se.chalmers.get_rect.game.entities;

public interface ICombatModel extends IPhysicsModel {
    void addHealth(int health);
    int getcurrentHealth();
    void takeDamage(int dmg);
    int getMaxHealth();
}
