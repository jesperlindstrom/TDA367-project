package se.chalmers.get_rect.game.entities.worldObjects.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.worldObjects.model.Pitfall;

/**
 * Created by Simon on 16-05-29.
 */
public class PitfallView extends AbstractView {

    private Pitfall model;

    public PitfallView(Pitfall model) {
        this.model = model;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("img/cave/cage.png", model.getPosition());
    }

    @Override
    public int getDrawPriority() {
        return 9;
    }
}
