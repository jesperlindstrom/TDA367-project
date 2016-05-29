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
        super.onCollision(otherObject, side, isSolid);

        if (otherObject.equals(player)) {
            Player player = (Player) otherObject;
            player.takeDamage(1);
        }
    }

    @Override
    public void update(double delta) {
        if (isKnockbacked())
            return;


        // Amazing AI
        int playerX = player.getPosition().getX();
        int zombieX = getPosition().getX();

        if (Math.abs(playerX - zombieX) > 820 && Math.abs(player.getPosition().getY() - getPosition().getY()) > 100)
            return;

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
