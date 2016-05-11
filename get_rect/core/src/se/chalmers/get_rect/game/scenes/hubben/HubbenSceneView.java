package se.chalmers.get_rect.game.scenes.hubben;

import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.scenes.AbstractBackgroundView;

public class HubbenSceneView extends AbstractBackgroundView{
    protected HubbenSceneView(ICamera camera) {
        super(camera, "img/backgrounds/hubben.png");
    }
}
