package se.chalmers.get_rect.game.scenes.menu.menuEntities;

import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.scenes.menu.menuEntities.buttons.ContinueButton;
import se.chalmers.get_rect.game.scenes.menu.menuEntities.buttons.ExitButton;
import se.chalmers.get_rect.utilities.Point;

public class ButtonFactory {

    private CameraManager camera;

    public ButtonFactory(CameraManager camera) {
        this.camera = camera;
    }

    public ButtonEntity make(ButtonDataStore data) {
        return make(data.getType(), data.getPosition());
    }

    public ButtonEntity make(String type, Point position) {
        if (type.equals("continue")) {
            return makeContinue(position);
        }
        if (type.equals("exit")) {
            return makeExit(position);
        }
        return null;
    }

    private ButtonEntity makeContinue(Point position) {
        IButton tmpButton = new ContinueButton(position);
        IView tmpView = new ButtonView("continue_button.png", tmpButton, camera);
        return new ButtonEntity(tmpButton, tmpView);

    }

    private ButtonEntity makeExit(Point position) {
        IButton tmpButton = new ExitButton(position);
        IView tmpView = new ButtonView("exit_button.png", tmpButton, camera);
        return new ButtonEntity(tmpButton, tmpView);

    }
}
