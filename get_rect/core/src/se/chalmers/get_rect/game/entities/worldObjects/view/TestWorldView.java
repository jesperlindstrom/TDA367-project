package se.chalmers.get_rect.game.entities.worldObjects.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.ICamera;

public class TestWorldView extends AbstractBackgroundView {

    private IAudioManagerAdapter audioManager;

    public TestWorldView(ICamera camera, IAudioManagerAdapter audioManager) {
        super(camera, "img/backgrounds/background.png");
        this.audioManager = audioManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        super.draw(graphics);
        audioManager.playMusic("harryPotter");
    }
}