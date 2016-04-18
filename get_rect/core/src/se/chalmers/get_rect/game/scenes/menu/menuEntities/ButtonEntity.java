package se.chalmers.get_rect.game.scenes.menu.menuEntities;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IView;

public class ButtonEntity {
    private IButton button;
    private IView view;

    public ButtonEntity(IButton button, IView view) {
        this.button = button;
        this.view = view;
    }

    public IButton getButton() {
        return button;
    }

    public void draw(IGraphicsAdapter graphics) {
        view.draw(graphics);
    }
}
