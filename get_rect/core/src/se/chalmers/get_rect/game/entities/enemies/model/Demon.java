package se.chalmers.get_rect.game.entities.enemies.model;

import se.chalmers.get_rect.game.entities.AbstractCombatModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.SideData;

import java.util.Random;

public class Demon extends AbstractCombatModel {
    private final int width;
    private final int height;
    private int speed;
    private IModel player;
    private int randHeight;
    private int randFlap;
    private boolean isAttacking = false;

    /**
     * Creates a demon
     * randHeight is the approximate Y coordinate of the demon set to be reachable for the player
     * randFlap is set to a reasonable amount of flap
     * Both are random and set in the constructor to be different for all demons
     * @param point
     * @param rectangleFactory
     * @param player
     * @param width
     * @param height
     */

    public Demon(Point point, IRectangleFactoryAdapter rectangleFactory, IModel player, int width, int height){
        super(point, new Point(0, 0), false, rectangleFactory, 30);
        this.width = width;
        this.height = height;
        setBoundingBox(width, height);
        Random rand = new Random();
        randHeight = rand.nextInt(200) + 500;
        randFlap = rand.nextInt(50) + 30;
        speed = rand.nextInt(10) + 15;
        this.player = player;

    }

    /**
     * 300, 300 is the size of the hitbox for this image
     * @param point
     * @param rectangleFactory
     * @param player
     */
    public Demon(Point point, IRectangleFactoryAdapter rectangleFactory, IModel player){
        this(point, rectangleFactory, player, 300, 300);
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, SideData side, boolean isSolid) {
        if (otherObject.equals(player)) {
            Player player = (Player) otherObject;
            player.takeDamage(1);
        }
    }

    /**
     * Controls the movement of the demon
     * follows the player in the air until it's above its target within 3 px each way, then attacks it straight down
     * demonX is manually set to X plus half the size of the picture to be in the middle
     * @param delta
     */

    @Override
    public void update(double delta) {
        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();
        int demonX = getPosition().getX()+(300/2);
        int demonY = getPosition().getY();

        if (getVelocity().getX() != 0 && Math.abs(getVelocity().getX()) != speed)
            return;

        int velX = 0;

        if (playerX > demonX) {
            velX = speed;
        } else if (playerX < demonX) {
            velX = -speed;
        }

        if (demonY < randHeight && playerX != demonX) {
            setVelocity(getVelocity().setY(randFlap));


        }
        if (playerX > demonX - 3  && playerX < demonX + 3) {
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

    public boolean isWithinScreen() {
        return player.getPosition()
    }

}
