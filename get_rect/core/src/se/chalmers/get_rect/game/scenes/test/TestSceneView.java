package se.chalmers.get_rect.game.scenes.test;

import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.scenes.AbstractBackgroundView;

public class TestSceneView extends AbstractBackgroundView {
    public TestSceneView(CameraManager camera) {
        super(camera, "img/backgrounds/background.png");
    }
}