package se.chalmers.get_rect.game.scenes.test;

import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.scenes.AbstractBackgroundView;

public class TestSceneView extends AbstractBackgroundView {
    public TestSceneView(ICamera camera) {
        super(camera, "img/backgrounds/background.png");
    }
}