package se.chalmers.get_rect.game.entities.enemies.model;

import se.chalmers.get_rect.game.entities.AbstractCombatModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.physics.CollisionData;

import java.util.Random;

public class Demon extends AbstractCombatModel {
    private int speed;
    private IModel player;
    private int randHeight;
    private int randFlap;
    private boolean isAttacking = false;

    public Demon(Point point, IRectangleFactoryAdapter rectangleFactory, IModel player, int width, int height){
        super(point, new Point(0, 0), false, true, rectangleFactory, 30);
        setBoundingBox(width, height);
        Random rand = new Random();
        randHeight = rand.nextInt(200) + 500;
        randFlap = rand.nextInt(50) + 30;
        speed = rand.nextInt(10) + 15;
        this.player = player;
    }

    public Demon(Point point, IRectangleFactoryAdapter rectangleFactory, IModel player){
        this(point, rectangleFactory, player, 300, 300);
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, CollisionData side, boolean isSolid) {
        if (otherObject.equals(player)) {
            Player player = (Player) otherObject;
            player.takeDamage(1);
        }
    }

    @Override
    public void update(double delta) {
        if (isKnockbacked())
            return;

        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();
        int demonX = getPosition().getX()+(300/2);
        int demonY = getPosition().getY();

        if (Math.abs(playerX - demonX) > 800 || Math.abs(playerY - demonY) > 400)
            return;

        int velX = 0;

        if (demonX < playerX ) {
            velX = speed;
        } else if (playerX < demonX) {
            velX = -speed;
        }

        if (demonY < randHeight && playerX != demonX) {
            setVelocity(getVelocity().setY(randFlap));
        }

        if ( (demonX - 3) < playerX  && playerX < (demonX + 3) ) {
            setVelocity(getVelocity().setY(-playerY));
            isAttacking = true;
        } else {
            isAttacking = false;
        }

        setVelocity(getVelocity().setX(velX));

    }
    public int getRandFlap() {
        return randFlap;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    @Override
    protected void die() {
        super.die();
        triggerEvent("demon", "died");
    }
}
