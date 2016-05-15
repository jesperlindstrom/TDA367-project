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

    public Demon(Point point, IRectangleFactoryAdapter rectangleFactory, IModel player, int width, int height){
        super(point, new Point(0, 0), false, rectangleFactory, 30);
        this.width = width;
        this.height = height;
        setBoundingBox(width, height);

        this.player = player;

        Random rand = new Random();
        speed = rand.nextInt(15) + 10;
    }
    public Demon(Point point, IRectangleFactoryAdapter rectangleFactory, IModel player){
        this(point, rectangleFactory, player, 100, 100);
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, SideData side, boolean isSolid) {
        // Jump, to simulate a lethal broccoli ninja attack.
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

        if (getVelocity().getX() != 0 && Math.abs(getVelocity().getX()) != speed)
            return;

        int velX = 0;

        if (playerX > zombieX) {
            velX = speed;
        } else if (playerX < zombieX) {
            velX = -speed;
        }

        setVelocity(getVelocity().setX(velX));
    }

}
