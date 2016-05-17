package se.chalmers.get_rect.game.entities.npc.model;


import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public class Hunchen extends AbstractNPCModel {

    private Player player;
    private boolean isRiding;


    public Hunchen(Point position, IRectangleFactoryAdapter rectangleFactory, Player player){
        super(position, rectangleFactory);
        this.player = player;
        setBoundingBox(200, 200);
    }

    @Override
    public void onInteract(IModel model) {

    }

    @Override
    public void update(double delta) {
        isRiding = player.isRiding();


        int playerX = player.getPosition().getX();
        int huncehnX = getPosition().getX();

        if (player.getPosition().distanceTo(getPosition()) < 100) return;

        Point vel;

        if (playerX > huncehnX){
            vel = player.getVelocity();
        } else {
            vel = player.getVelocity().inverse();
        }
        setVelocity(getVelocity().setPosition(vel));
    }

    public boolean isRiding() {
        return isRiding;
    }
}
