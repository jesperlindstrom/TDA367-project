package se.chalmers.get_rect.game.entities.worldObjects.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.worldObjects.model.Trampoline;

public class TrampolineView extends AbstractView {
    private Trampoline model;

    private static final int DRAW_PRIORITY = 5;
    public TrampolineView(IModel model) {
        super(model);
        this.model = (Trampoline)model;
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("img/entities/trampoline/trampoline.png", model.getPosition());
        if (model.isGotHit()) {
            graphics.drawText("WÄÄÄÄH", model.getPosition().addY(-100));
        }
    }
}