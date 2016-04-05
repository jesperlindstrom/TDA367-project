package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IView;

class PlayerView implements IView {

    private Player player;

    public PlayerView(Player player){
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("data/player.png", player.getxCoordinate(), player.getyCoordinate());
    }
}