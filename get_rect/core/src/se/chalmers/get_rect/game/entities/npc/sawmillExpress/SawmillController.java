package se.chalmers.get_rect.game.entities.npc.sawmillExpress;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsController;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.physics.ISolidObject;
import se.chalmers.get_rect.utilities.Point;

public class SawmillController implements IPhysicsController {
    private SawmillExpress model;
    private IView view;
    private boolean wäääh;


    public SawmillController(IView view, SawmillExpress model) {
        this.view = view;
        this.model = model;
        this.wäääh = false;
    }

    @Override
    public void update(double delta) {
        if(model.isWäääh()) {
            model.setPosition(model.getPosition().add(new Point(0, 3)));
        }
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        view.draw(graphics);
    }

    @Override
    public void setScene(IScene scene) {

    }

    @Override
    public IRectangleAdapter getBoundingBox() {
        return model.getBoundingBox();
    }

    @Override
    public void onCollision(ISolidObject otherObject) {
        if(otherObject.getClass().equals(PlayerController.class)) {
            model.setWäääh(true);
        }

    }

}
