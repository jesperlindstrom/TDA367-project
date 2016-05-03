package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.player.Player;

public class PlayerWeaponSlotsView extends AbstractView {

    private Player player;
    private CameraManager cameraManager;
    private final String pathItemSlot1= "img/pauseMenu/inventory/item_slot.png";
    private final String pathItemActive = "img/pauseMenu/inventory/item_active.png";
    private final String pathItemActive2 = "img/pauseMenu/inventory/item_active.png";

    public PlayerWeaponSlotsView(Player player, CameraManager cameraManager){
        this.player = player;
        this.cameraManager = cameraManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {

    }
}
