package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.entities.IView;

class PlayerView implements IView {

    private Player player;
    private String oneLegImagePath;
    private String twoLegImagePath;
    private String currentImagePath;
    private int imageCount;
    private IInputAdapter input;

    public PlayerView(Player player, IInputAdapter input){

        this.player = player;
        this.oneLegImagePath = "data/playerOneLeg.png";
        this.twoLegImagePath = "data/playerTwoLeg.png";
        this.currentImagePath = twoLegImagePath;
        this.imageCount = 0;
        this.input = input;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(currentImagePath, player.getxCoordinate(), player.getyCoordinate());
        if(input.isKeyPressed(IInputAdapter.Keys.A) || input.isKeyPressed(IInputAdapter.Keys.D)) {
            imageCount++;
            setImagePath();
        }

    }
    private void setImagePath() {
        if (imageCount <= 10) {

            currentImagePath = twoLegImagePath;
        } else {
            if (imageCount == 20) {
                imageCount = 0;
            }
            currentImagePath = oneLegImagePath;
        }
    }
}