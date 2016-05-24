package se.chalmers.get_rect.game.window.view;

import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.window.model.MainMenu;
import se.chalmers.get_rect.utilities.Point;

public class MainMenuView implements IWindowView {
    private static final int OFFSET_Y = -100;
    private static final int BUTTON_WIDTH = 450;
    private static final int BUTTON_HEIGHT = 111;
    private static final int BUTTON_SPACING = 20;
    private static final Point CONTINUE = new Point(0, 0);
    private static final Point NEW_GAME = new Point(0, 1);
    private static final Point EXIT = new Point(0, 2);
    private static final String IMG_PATH = "img/pauseMenu/";

    private ICameraAdapter camera;
    private MainMenu model;

    public MainMenuView(MainMenu model, ICamera camera) {
        this.camera = camera.getAdapter();
        this.model = model;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        Point centerPos = camera.getPosition();
        int centerOffsetX = (int) -camera.getWidth()/2;
        int centerOffsetY = (int) -camera.getHeight()/2;

        //Backgrounds
        graphics.draw(IMG_PATH + "main_menu.png", centerPos.add(centerOffsetX, centerOffsetY), camera.getWidth(), camera.getHeight());

        graphics.draw("img/splash/splash_logo.png", centerPos.add(-300, OFFSET_Y + 50));

        //Buttons
        if (!model.isButtonDisabled(new Point(0, 0))) {
            graphics.draw(IMG_PATH + "buttons/continue.png", getRealPosition(CONTINUE));
        }

        graphics.draw(IMG_PATH + "buttons/new_game.png", getRealPosition(NEW_GAME));
        graphics.draw(IMG_PATH + "buttons/exit.png", getRealPosition(EXIT));

        // Menu overlay
        graphics.draw(IMG_PATH + "buttons/overlay.png", getRealPosition(model.getCurrentlyMarked()));

    }

    private Point getRealPosition(Point gridPosition) {
        Point centerPos = camera.getPosition();

        if (gridPosition.equals(CONTINUE))
            return centerPos.add(-BUTTON_WIDTH/2, OFFSET_Y);

        if (gridPosition.equals(NEW_GAME))
            return centerPos.add(-BUTTON_WIDTH/2, OFFSET_Y - (BUTTON_HEIGHT + BUTTON_SPACING));

        if (gridPosition.equals(EXIT))
            return centerPos.add(-BUTTON_WIDTH/2, OFFSET_Y - (BUTTON_HEIGHT*2 + BUTTON_SPACING*2));

        return null;
    }
}

