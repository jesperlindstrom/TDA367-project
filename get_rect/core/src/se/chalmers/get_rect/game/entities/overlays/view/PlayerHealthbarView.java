package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.utilities.Point;

public class PlayerHealthbarView extends AbstractView {
    private Player player;
    private ICamera camera;
    private static final String pathbackOverlay1 = "img/player/player-health-background.png";
    private static final String pathCenterOverlay3 = "img/player/player-center-overlay.png";
    private static final String pathHealthOverlay2 = "img/player/player-health-fill.png";
    private static final int DRAW_PRIORITY = 30;

    public PlayerHealthbarView(Player player, ICamera camera){
        this.player = player;
        this.camera = camera;
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        float dmgTaken = (float)(150*((player.getCurrentHealth())/(double)player.getMaxHealth()));
        int x = camera.getPosition().getX() + (int)camera.getAdapter().getWidth()/2 - 226;
        int y = camera.getPosition().getY();
        Point position = new Point(x,y);
        graphics.draw(pathbackOverlay1,position.addX(120));
        graphics.draw(pathHealthOverlay2,position.add(120, (int)dmgTaken-150));
        graphics.draw(pathCenterOverlay3, position.addY(-2));
    }
}
