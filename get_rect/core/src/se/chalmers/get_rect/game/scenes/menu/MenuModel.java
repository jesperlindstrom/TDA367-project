package se.chalmers.get_rect.game.scenes.menu;

import se.chalmers.get_rect.Game;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.scenes.menu.menuEntities.*;
import se.chalmers.get_rect.game.screens.GameScreen;
import se.chalmers.get_rect.io.IOFacade;
import se.chalmers.get_rect.utilities.Point;

import java.io.FileNotFoundException;
import java.util.List;


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
        buttons.pressButton(game);
    }

    private void loadButtons() throws FileNotFoundException {

        IOFacade<ButtonDataStore> buttonData  = new IOFacade<>("scenes/menu/buttons.json", ButtonDataStore.class);
        List<ButtonDataStore> buttonDataList = buttonData.load();

        ButtonFactory buttonFactory = new ButtonFactory(camera);

        if (buttonDataList != null) {
            for (ButtonDataStore data : buttonDataList) {
                System.out.println(data.getType() + " to be loaded");
                buttons.add(buttonFactory.make(data));
                buttons.setIndex(0);
            }
        }
    }

    public void draw(IGraphicsAdapter graphics) {
        buttons.draw(graphics);
    }

}
