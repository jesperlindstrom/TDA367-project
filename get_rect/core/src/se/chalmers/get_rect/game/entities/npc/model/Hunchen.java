package se.chalmers.get_rect.game.entities.npc.model;


import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public class Hunchen extends AbstractNPCModel {

    private Player player;
    private boolean isRiding;
    private int speed;

    public Hunchen(Point position, IRectangleFactoryAdapter rectangleFactory, Player player){
        super(position, rectangleFactory);
        this.player = player;
        setBoundingBox(200, 200);
        speed = player.getVelocity().getX() - 1;
    }

    @Override
    public void onInteract(IModel model) {

    }

    @Override
    public void update(double delta) {
        isRiding = player.isRiding();


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

    public boolean isRiding() {
        return isRiding;
    }
}
