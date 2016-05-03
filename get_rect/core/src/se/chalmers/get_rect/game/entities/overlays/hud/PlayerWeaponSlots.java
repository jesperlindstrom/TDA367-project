package se.chalmers.get_rect.game.entities.overlays.hud;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.player.Player;

public class PlayerWeaponSlots implements IView {

    private Player player;
    private CameraManager cameraManager;
    private final String path

    public PlayerWeaponSlots(Player player, CameraManager cameraManager){
        this.player = player;
        this.cameraManager = cameraManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {

    }
}
