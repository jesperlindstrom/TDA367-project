package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IView;

class PlayerView implements IView {

    private Player player;
    private String oneLegImagePath;
    private String twoLegImagePath;
    private int modulo;

    public PlayerView(Player player){

        this.player = player;
        this.oneLegImagePath = "data/playerOneLeg.png";
        this.twoLegImagePath = "data/playerTwoLeg.png";
        this.modulo = 0;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(setImagePath(), player.getxCoordinate(), player.getyCoordinate());

    }
    private String setImagePath() {
        if (modulo <= 30) {
            modulo++;
            return twoLegImagePath;
        } else {
            if (modulo == 60) {
                modulo = 0;
            }
            modulo++;
            return oneLegImagePath;
        }
    }
}