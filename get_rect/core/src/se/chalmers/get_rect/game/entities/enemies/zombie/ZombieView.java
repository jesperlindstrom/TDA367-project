package se.chalmers.get_rect.game.entities.enemies.zombie;


import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IView;

public class ZombieView implements IView{
    private Zombie model;
    private String oneLegImagePath;
    private String twoLegImagePath;
    private String currentImagePath;
    private int imageWalkCount;

    public ZombieView(Zombie model){
        this.model = model;
        this.oneLegImagePath = "img/entities/zombies/zombie.png";
        this.twoLegImagePath = "img/entities/zombies/zombieOpen.png";
        this.currentImagePath = twoLegImagePath;
        this.imageWalkCount = 0;
}

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(currentImagePath, model.getPosition());
        setImagePath();
    }

    /**
     * Sets the player image to get an animation when the player is moving.
     */
    private void setImagePath() {
        //// TODO: 2016-04-06 Might need some better values! this is to hard (coded), even for Lisch...
        imageWalkCount++;
        if (imageWalkCount <= 15) {

            currentImagePath = twoLegImagePath;
        } else {
            if (imageWalkCount == 20) {
                imageWalkCount = 0;
            }
            currentImagePath = oneLegImagePath;
        }
    }
}
