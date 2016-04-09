package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IView;

class PlayerView implements IView {

    private Player player;
    private String oneLegImagePath;
    private String twoLegImagePath;
    private String currentImagePath;
    private int imageWalkCount;

    public PlayerView(Player player){

        this.player = player;
        this.oneLegImagePath = "img/entities/player/playerOneLeg.png";
        this.twoLegImagePath = "img/entities/player/playerTwoLeg.png";
        this.currentImagePath = twoLegImagePath;
        this.imageWalkCount = 0;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(currentImagePath, player.getPosition());
        if(player.getWalking()) {
            imageWalkCount++;
            setImagePath();
        }

    }

    /**
     * Sets the player image to get an animation when the player is moving.
     */
    private void setImagePath() {
        //// TODO: 2016-04-06 Might need some better values! this is to hard (coded), even for Lisch...
        if (imageWalkCount <= 10) {

            currentImagePath = twoLegImagePath;
        } else {
            if (imageWalkCount == 20) {
                imageWalkCount = 0;
            }
            currentImagePath = oneLegImagePath;
        }
    }

}