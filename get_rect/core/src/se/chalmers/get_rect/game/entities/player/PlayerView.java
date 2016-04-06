package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.entities.IView;

class PlayerView implements IView {

    private Player player;
    private String oneLegImagePath;
    private String twoLegImagePath;
    private String currentImagePath;
    private int imageWalkCount;
    private int imageJumpCount;
    private boolean isJumpDone;

    public PlayerView(Player player, IInputAdapter input){

        this.player = player;
        this.oneLegImagePath = "data/playerOneLeg.png";
        this.twoLegImagePath = "data/playerTwoLeg.png";
        this.currentImagePath = twoLegImagePath;
        this.imageWalkCount = 0;
        this.imageJumpCount = 0;
        this.isJumpDone = true;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(currentImagePath, player.getxCoordinate(), player.getyCoordinate());
        if(player.getWalking()) {
            imageWalkCount++;
            setImagePath();
        }
        if(player.getJumping()){
            setPlayerLatitud();
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
    private void setPlayerLatitud(){
        if(imageJumpCount <= 3){
            player.setyCoordinate(player.getyCoordinate() + 12);
            imageJumpCount++;
        } else if (imageJumpCount <= 6){
            player.setyCoordinate(player.getyCoordinate() - 12);
            imageJumpCount++;
        }
        if(imageJumpCount > 6) {
            player.setJumping(false);
            imageJumpCount = 0;
        }

    }
}