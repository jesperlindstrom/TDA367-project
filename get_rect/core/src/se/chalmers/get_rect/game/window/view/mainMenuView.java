package se.chalmers.get_rect.game.window.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.window.AbstractGridModel;
import se.chalmers.get_rect.game.window.model.mainMenu;
import se.chalmers.get_rect.utilities.Point;

public class mainMenuView extends AbstractView {
    private static final Point CONTINUE = new Point(0, 0);
    private static final Point NEW_GAME = new Point(0, 1);
    private static final Point EXIT = new Point(0, 2);
    private static final String IMG_PATH = "img/pauseMenu/";
    private static final int DRAW_PRIORITY = 7;

    private CameraManager camera;
    private AbstractGridModel model;

    public mainMenuView(mainMenu model, CameraManager camera) {
        this.camera = camera;
        this.model = model;
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }

    @Override
    public boolean shouldBeRemoved() {
        return false;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {

        Point cameraPos = camera.getPosition();

        //Backgrounds
        graphics.draw(IMG_PATH + "main_menu.png", cameraPos);

        //Buttons
        graphics.draw(IMG_PATH + "buttons/continue.png", getRealPosition(CONTINUE));
        graphics.draw(IMG_PATH + "buttons/new_game.png", getRealPosition(NEW_GAME));
        graphics.draw(IMG_PATH + "buttons/exit.png", getRealPosition(EXIT));

        // Menu overlay
        graphics.draw(IMG_PATH + "buttons/overlay.png", getRealPosition(model.getCurrentButton()));

    }

    public Point getRealPosition(Point gridPosition) {
        if (gridPosition.equals(CONTINUE))
            return camera.getPosition().add(735, 382);

        if (gridPosition.equals(NEW_GAME))
            return camera.getPosition().add(735, 241);

        if (gridPosition.equals(EXIT))
            return camera.getPosition().add(735, 100);

        return null;
    }
}

