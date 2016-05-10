package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.utilities.Point;

public class PlayerWeaponSlotsView extends AbstractView {

    private Player player;
    private ICamera camera;
    private final String pathItemSlot= "img/pauseMenu/inventory/item_slot.png";
    private final String pathItemPHPrimary = "img/pauseMenu/inventory/placeholder_primary.png";
    private final String pathItemPHSecondary = "img/pauseMenu/inventory/placeholder_secondary.png";
    private final String pathItemActive = "img/pauseMenu/inventory/item_active.png";
    private static final int DRAW_PRIORITY = 60;


    public PlayerWeaponSlotsView(Player player, ICamera camera){
        this.player = player;
        this.camera = camera;
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        int x = camera.getPosition().getX() + (int)camera.getAdapter().getWidth()/2 - 226;
        int y = camera.getPosition().getY();
        Point position = new Point(x,y);
        Point primaryPos= position.add(40,20);
        Point secondaryPos  = position.add(320,20);

        graphics.draw(pathItemSlot,primaryPos);
        graphics.draw(pathItemPHPrimary, primaryPos.add(15,10));

        graphics.draw(pathItemSlot,secondaryPos);
        graphics.draw(pathItemPHSecondary,secondaryPos.add(15,10));

        if (player.isPrimaryWeapon()){
            graphics.draw(pathItemActive,primaryPos);
        } else {
            graphics.draw(pathItemActive,secondaryPos);

        }
    }
}
