package se.chalmers.get_rect.game.entities.projectile.model;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.SideData;

import java.util.Random;

public class Projectile extends AbstractPhysicsModel {
    private int width = 50;
    private int height = 50;
    private int dmg = 10;
    private IModel owner;
    private boolean isMelee;

    public Projectile(Point position, Point velocity, IRectangleFactoryAdapter rectangleFactory, IModel owner, boolean isMelee) {
        super(position, velocity, false, rectangleFactory);
        setBoundingBox(getPosition(), width, height);
        this.owner = owner;
        this.isMelee = isMelee;
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, SideData collisionSide, boolean isSolid) {
        if (isSolid && collisionSide.bottom()) {
            int friction = getVelocity().getX() > 0 ? -1 : 1;
            setVelocity(getVelocity().addX(friction));
        }

        if (otherObject instanceof ICombatModel && !(otherObject instanceof Player)) {
            setShouldBeRemoved();
            otherObject.setPosition(otherObject.getPosition().addY(30));
            otherObject.setVelocity(getVelocity().multiply(0.1));
            ((ICombatModel) otherObject).takeDamage(dmg);
        }
    }

    @Override
    public void update(double delta) {
        if (getVelocity().getX() == 0 && getVelocity().getY() == 0) {
            setShouldBeRemoved();
        }
        if (isMelee) {
            if (owner.getPosition().distanceTo(getPosition()) > 90) {
                setShouldBeRemoved();
            }
        }
    }
}