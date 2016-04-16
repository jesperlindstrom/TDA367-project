package se.chalmers.get_rect.game.entities.worldObjects.trampoline;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.IView;

public class TrampolineView implements IView {
    private IModel model;

    public TrampolineView(IModel model) {
        this.model = model;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("img/entities/trampoline/trampoline.png", model.getPosition());
    }
}
