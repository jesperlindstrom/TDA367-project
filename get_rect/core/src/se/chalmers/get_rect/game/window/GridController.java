package se.chalmers.get_rect.game.window;

import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.entities.IController;

public class GridController implements IController {
    private IInputAdapter input;
    private AbstractGridModel model;

    public GridController(AbstractGridModel model, IInputAdapter input) {
        this.input = input;
        this.model = model;
    }

    @Override
    public void update() {
        if (input.isKeyJustPressed(IInputAdapter.Keys.ENTER)) {
            model.getCurrentlyMarkedButton().executeAction();
        }

        if (input.isKeyJustPressed(IInputAdapter.Keys.UPKEY)) {
            model.moveMarkUp();
        }

        if (input.isKeyJustPressed(IInputAdapter.Keys.DOWNKEY)) {
            model.moveMarkDown();
        }
    }
}
