package se.chalmers.get_rect.game.entities.enemies.view;

import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.IModel;

public class GhostBroccoliView extends AbstractAnimatedView {

    private static final int WALKING = 1;

    public GhostBroccoliView(IModel model) {
        super(model, WALKING);

        addAnimationFrame(WALKING, "img/entities/zombies/zombieGhost.png", 5);
        addAnimationFrame(WALKING, "img/entities/zombies/zombieGhostOpen.png", 5);
    }


}
