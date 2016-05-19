package se.chalmers.get_rect.game.entities.npc.model;


import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.IRectangleAdapter;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.physics.CollisionData;

public class Hunchen extends AbstractNPCModel {

    private Player player;
    private int speed;
    private boolean init = false;
    private boolean stop;

    public Hunchen(Point position, IRectangleFactoryAdapter rectangleFactory, Player player){
        super(position, true, rectangleFactory);
        this.player = player;
        setBoundingBox(81, 51);
        speed = 30;
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


        IRectangleAdapter rect = getRectangleFactory().make(getPosition().getX()-30, getPosition().getY(), 81+60, 51);
        CollisionData collision = player.getBoundingBox().intersects(rect);

        if (collision == null){
            stop = false;
        } else {
            stop = true;
        }



        if (player.hasFoundHunch() && !isRiding()) {

            int playerX = player.getPosition().getX();
            int hunchX = getPosition().getX();


            if (getVelocity().getX() != 0 && Math.abs(getVelocity().getX()) != speed)
                return;

            int velX = 0;

            if (!stop && playerX > hunchX) {
                velX = speed;
            } else if (!stop && playerX < hunchX) {
                velX = -speed;
            } else if (stop){
                velX = 0;
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
    public void onCollision(IPhysicsObject otherObject, CollisionData data, boolean isSolid) {
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
