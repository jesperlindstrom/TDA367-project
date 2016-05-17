package se.chalmers.get_rect.game.entities.npc.model;


import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.SideData;

public class Hunchen extends AbstractNPCModel {

    private Player player;
    private int speed;
    private int oldX;
    private boolean init = false;

    public Hunchen(Point position, IRectangleFactoryAdapter rectangleFactory, Player player){
        super(position, rectangleFactory);
        this.player = player;
        setBoundingBox(81, 51);
        speed = 30;
        oldX = getPosition().getX();

    }

    @Override
    public void onInteract(IModel model) {
        player.setHasFoundHunch(true);
        if (isRiding()){
            player.setRiding(false);
            setVelocity(getVelocity().setY(0));
        } else {
            player.setRiding(true);
        }

    }

    @Override
    public void update(double delta) {
        if (!init && player.hasFoundHunch()){
            init = true;
            setPosition(player.getPosition());
        }

        if (player.hasFoundHunch() && !isRiding()) {

            int playerX = player.getPosition().getX();
            int zombieX = getPosition().getX();


            if (getVelocity().getX() != 0 && Math.abs(getVelocity().getX()) != speed || getPosition().distanceTo(player.getPosition()) < 200)
                return;

            int velX = 0;

            if (playerX > zombieX) {
                velX = speed;
            } else if (playerX < zombieX) {
                velX = -speed;
            }

            setVelocity(getVelocity().setX(velX));
        }
        if (isRiding()){
            super.setPosition(player.getPosition().addY(100));
        }
    }

    public boolean isRiding() {
        return player.isRiding();
    }


    @Override
    public void onCollision(IPhysicsObject otherObject, SideData data, boolean isSolid) {
        super.onCollision(otherObject, data, isSolid);

        if (isSolid && (data.left())) {
            setVelocity(getVelocity().setY(150));
            setPosition(getPosition().addX(10));
        } else if (isSolid && data.right()){
            setVelocity(getVelocity().setY(150));
            setPosition(getPosition().subtract(10,0));
        }
    }

    @Override
    public void setPosition(Point position) {
        if (!isRiding()) {
            super.setPosition(position);
        }
    }
}
