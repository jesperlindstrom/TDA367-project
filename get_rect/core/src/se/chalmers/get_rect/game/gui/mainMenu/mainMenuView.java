package se.chalmers.get_rect.game.gui.mainMenu;

import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.gui.AbstractGridModel;
import se.chalmers.get_rect.utilities.Point;

public class mainMenuView implements IView {

    private static final Point CONTINUE = new Point(0, 0);
    private static final Point EXIT = new Point(0, 1);
    private static final String IMG_PATH = "img/pauseMenu/";

    private ICameraAdapter camera;
    private AbstractGridModel model;

    public mainMenuView(AbstractGridModel model, ICameraAdapter camera) {
        this.camera = camera;
        this.model = model;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        Point cameraPos = camera.getPosition();

        //Backgrounds
        graphics.draw(IMG_PATH + "menuShader.png", cameraPos);
        graphics.draw(IMG_PATH + "buttons/pause_menu_bg.png", cameraPos.add(360, 140));
        graphics.draw(IMG_PATH + "inventory/inventory_bg.png", cameraPos.add(960, 240));

        //Buttons
        graphics.draw(IMG_PATH + "buttons/continue.png", getRealPosition(CONTINUE));
        graphics.draw(IMG_PATH + "buttons/exit.png", getRealPosition(EXIT));


        graphics.draw(IMG_PATH + "buttons/overlay.png", getRealPosition(model.getCurrentButton()));

    }

    public Point getRealPosition(Point gridPosition) {

        if (gridPosition.equals(CONTINUE)) {
            return camera.getPosition().add(430, 390);
        }

        if (gridPosition.equals(EXIT)) {
            return camera.getPosition().add(430, 240);
        }

        return null;
    }

}

