package se.chalmers.get_rect.game.entities.npc.sawmillExpress;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.scenes.IScene;

public class SawmillController implements IController {
    private IModel model;
    private IView view;


    public SawmillController(IView view, IModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void update(long delta) {

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        view.draw(graphics);
    }

    @Override
    public void setScene(IScene scene) {

    }
}
