package se.chalmers.get_rect.game.scenes.horsalsvagen;

import se.chalmers.get_rect.game.camera.CameraManager;
import se.chalmers.get_rect.game.scenes.AbstractBackgroundView;

public class HorsalsvagenSceneView extends AbstractBackgroundView {
    public HorsalsvagenSceneView(CameraManager camera) {
        super(camera, "img/backgrounds/horsalsvagen.png", "img/backgrounds/skybox.png");
    }
}