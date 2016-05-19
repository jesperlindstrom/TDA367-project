package se.chalmers.get_rect.game.entities.worldObjects.view;

import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IMusicAdapter;
import se.chalmers.get_rect.game.entities.ICamera;

public class HorsalsvagenSceneView extends AbstractBackgroundView {
    private IMusicAdapter backgroundMusic;

    public HorsalsvagenSceneView(ICamera camera, IAssetManagerAdapter assetManager) {
        super(camera, "img/backgrounds/horsalsvagen.png", "img/backgrounds/skybox.png");
        backgroundMusic = assetManager.getMusic("music/BackgroundMusic.mp3");
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        super.draw(graphics);
        backgroundMusic.play();
    }
}