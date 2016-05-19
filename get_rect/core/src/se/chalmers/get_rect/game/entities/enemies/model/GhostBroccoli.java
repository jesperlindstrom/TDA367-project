package se.chalmers.get_rect.game.entities.enemies.model;

import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public class GhostBroccoli extends Zombie {
    private Player player;
    private int health;

    public GhostBroccoli(Point point, IRectangleFactoryAdapter rectangleFactory, IModel player) {
        super(point, rectangleFactory, player, 100, 100);
        this.player = (Player)player;
        health = getMaxHealth();
    }

    @Override
    public int getMaxHealth() {
        return 1000;
    }

    @Override
    public int getCurrentHealth() {
        return health;
    }

    @Override
    public void takeDamage(int dmg) {
        health = health - dmg;
        if (health < 1) {
            die();
            triggerEvent("broccolighost", "died");
        }
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, CollisionData side, boolean isSolid) {
        if (otherObject.equals(player)) {
            player.takeDamage(100);
        }
    }
}
