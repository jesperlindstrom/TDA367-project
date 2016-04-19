package se.chalmers.get_rect.game.entities.overlays.interactable;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IInteractableModel;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.utilities.Point;

public class InteractionHintsView implements IView {
    private InteractableList interactableList;

    public InteractionHintsView(InteractableList interactableList) {
        this.interactableList = interactableList;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        for (IEntity entity : interactableList.get()) {
            IInteractableModel interactable = (IInteractableModel) entity.getModel();
            drawInteractionHint(graphics, interactable);
        }
    }

    public void drawInteractionHint(IGraphicsAdapter graphics, IInteractableModel model) {
        if (model.showInteractionHint()){
            graphics.draw("img/interact/e.png",new Point(model.getPosition().add(20,300)));
        }
    }
}
