package se.chalmers.get_rect.game.scenes.menu;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.scenes.menu.menuEntities.*;
import se.chalmers.get_rect.game.screens.GameScreen;
import se.chalmers.get_rect.utilities.Point;

import java.io.FileNotFoundException;


public class MenuModel {

    private CameraManager camera;
    private ButtonManager buttons;

    public MenuModel(CameraManager camera) {
        this.camera = camera;
        buttons = new ButtonManager();

        try {
            loadButtons();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }

    public Point getPosition() {
        return camera.getPosition();
    }

    public void moveMenuMarkerUp() {
        buttons.decreaseIndex();
    }

    public void moveMenuMarkerDown() {
        buttons.increaseIndex();
    }

    public void pressCurrentButton(GameScreen game) {
        buttons.getCurrentButton().pressButton(game);
    }

    private void loadButtons() throws FileNotFoundException {
        ButtonFactory buttonFactory = new ButtonFactory(camera);

        buttons.add(buttonFactory.make("continue", 430 , 390));
        buttons.add(buttonFactory.make("exit", 430 , 240));

        buttons.setIndex(0);

    }

    public void draw(IGraphicsAdapter graphics) {
        buttons.draw(graphics);
    }

}
