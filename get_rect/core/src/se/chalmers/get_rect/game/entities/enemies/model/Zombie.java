package se.chalmers.get_rect.game.entities.enemies.model;

import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.AbstractCombatModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.utilities.Point;

import java.util.Random;

public class Zombie extends AbstractCombatModel {
    private int speed;
    private IModel player;

    public Zombie(Point point, IRectangleFactoryAdapter rectangleFactory, IModel player, int width, int height){
        super(point, new Point(0, 0), false, true, rectangleFactory, 30);
        setBoundingBox(width, height);

        this.player = player;

        Random rand = new Random();
        speed = rand.nextInt(20) + 5;
    }
    public Zombie(Point point, IRectangleFactoryAdapter rectangleFactory, IModel player){
        this(point, rectangleFactory, player, 100, 100);
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, CollisionData side, boolean isSolid) {
        if (otherObject.equals(player) && getVelocity().getY() == 0) {
            Player player = (Player) otherObject;
            player.takeDamage(1);
        }
    }

    @Override
    public void update(double delta) {
        // Amazing AI
        int playerX = player.getPosition().getX();
        int zombieX = getPosition().getX();

        int velX = 0;

        if (playerX > zombieX) {
            velX = speed;
        } else if (playerX < zombieX) {
            velX = -speed;
        }

        setVelocity(getVelocity().setX(velX));
    }

    @Override
    protected void die() {
        super.die();
        triggerEvent("zombie", "died");
    }
}
