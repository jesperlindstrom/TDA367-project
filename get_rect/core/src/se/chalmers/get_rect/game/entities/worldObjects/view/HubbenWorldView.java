package se.chalmers.get_rect.game.entities.worldObjects.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.ICamera;

public class HubbenWorldView extends AbstractBackgroundView {

    private IAudioManagerAdapter audioManager;

    public HubbenWorldView(ICamera camera, IAudioManagerAdapter audioManager) {
        super(camera, "img/backgrounds/hubben2.png");
        this.audioManager = audioManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        super.draw(graphics);
        audioManager.playMusic("lotr");
    }
}
