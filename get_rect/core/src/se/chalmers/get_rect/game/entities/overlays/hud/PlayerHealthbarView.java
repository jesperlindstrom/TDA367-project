package se.chalmers.get_rect.game.entities.overlays.hud;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.utilities.Point;

public class PlayerHealthbarView implements IView{

    private Player player;
    private CameraManager camera;
    private final String pathbackOverlay1 = "img/player/player-health-background.png";
    private final String pathCenterOverlay3 = "img/player/player-center-overlay.png";
    private final String pathHealthOverlay2 = "img/player/player-health-fill.png";

    public PlayerHealthbarView(Player player, CameraManager camera){
        this.player = player;
        this.camera = camera;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        float dmgTaken = (float)(150*((player.getcurrentHealth())/(double)player.getMaxHealth()));
        int x = camera.getPosition().getX() + 540 + 200;
        int y = camera.getPosition().getY();
        Point position = new Point(x,y);
        graphics.draw(pathbackOverlay1,position.addX(120));
        graphics.draw(pathHealthOverlay2,position.add(120, (int)dmgTaken-150));
        graphics.draw(pathCenterOverlay3, position);
    }
}
