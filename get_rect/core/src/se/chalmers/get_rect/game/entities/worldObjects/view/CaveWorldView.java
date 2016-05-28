package se.chalmers.get_rect.game.entities.worldObjects.view;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.ICamera;

/**
 * Created by Simon on 16-05-27.
 */
public class CaveWorldView extends AbstractBackgroundView {

    private IAudioManagerAdapter audioManager;

    public CaveWorldView(ICamera camera, IAudioManagerAdapter audioManager) {
        super(camera, "img/cave/background.png");
        this.audioManager = audioManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        super.draw(graphics);
        audioManager.playMusic("lotr");
    }
}
