package se.chalmers.get_rect.game.entities.worldObjects.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractStaticView;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.worldObjects.model.Trampoline;

public class TrampolineView extends AbstractStaticView {
    private Trampoline model;

    public TrampolineView(IModel model) {
        super(model, "img/entities/trampoline/trampoline.png");
        this.model = (Trampoline)model;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        super.draw(graphics);
        if (model.isGotHit()) {
            graphics.drawText("WÄÄÄÄH", model.getPosition().addY(-100));
        }
    }
}