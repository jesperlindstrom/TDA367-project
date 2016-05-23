package se.chalmers.get_rect.game.entities.worldObjects.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.ICamera;

public class HorsalsvagenWorldView extends AbstractBackgroundView {
    private IAudioManagerAdapter audioManager;

    public HorsalsvagenWorldView(ICamera camera, IAudioManagerAdapter audioManager) {
        super(camera, "img/backgrounds/horsalsvagen.png", "img/backgrounds/skybox.png");
        this.audioManager = audioManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        super.draw(graphics);
        audioManager.playMusic("starWars");
    }
}