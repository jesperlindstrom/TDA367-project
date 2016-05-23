package se.chalmers.get_rect.game.window.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.window.model.MainMenu;
import se.chalmers.get_rect.utilities.Point;

public class MainMenuView implements IWindowView{
    private static final Point CONTINUE = new Point(0, 0);
    private static final Point NEW_GAME = new Point(0, 1);
    private static final Point EXIT = new Point(0, 2);
    private static final String IMG_PATH = "img/pauseMenu/";

    private ICamera camera;
    private MainMenu model;

    public MainMenuView(MainMenu model, ICamera camera) {
        this.camera = camera;
        this.model = model;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {

        Point cameraPosPre = camera.getPosition();
        Point cameraPos = new Point(-960,cameraPosPre.getY()+cameraPosPre.getY()/5);

        //Backgrounds
        graphics.draw(IMG_PATH + "main_menu.png", cameraPos);

        //Buttons
        if (model.isContinueAvailable()) {
            graphics.draw(IMG_PATH + "buttons/continue.png", getRealPosition(CONTINUE));
        }
        graphics.draw(IMG_PATH + "buttons/new_game.png", getRealPosition(NEW_GAME));
        graphics.draw(IMG_PATH + "buttons/exit.png", getRealPosition(EXIT));

        // Menu overlay
        graphics.draw(IMG_PATH + "buttons/overlay.png", getRealPosition(model.getCurrentlyMarked()));

    }

    private Point getRealPosition(Point gridPosition) {
        int buttonWidth = 225;

        if (gridPosition.equals(CONTINUE))
            return camera.getPosition().add((int)(camera.getAdapter().getWidth()/2) - buttonWidth, 382);

        if (gridPosition.equals(NEW_GAME))
            return camera.getPosition().add((int)(camera.getAdapter().getWidth()/2) - buttonWidth, 241);

        if (gridPosition.equals(EXIT))
            return camera.getPosition().add((int)(camera.getAdapter().getWidth()/2) - buttonWidth, 100);

        return null;
    }
}

