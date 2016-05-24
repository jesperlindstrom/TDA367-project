package se.chalmers.get_rect.game.window.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.window.model.AbstractGridModel;
import se.chalmers.get_rect.utilities.Point;

public class InGameMenuView implements IWindowView {
    private static final Point CONTINUE = new Point(0, 0);
    private static final Point SAVE = new Point(0, 1);
    private static final Point EXIT = new Point(0, 2);
    private Point offset = new Point(70, 300);
    private static final String IMG_PATH = "img/pauseMenu/";
    private ICamera camera;
    private AbstractGridModel model;

    public InGameMenuView(AbstractGridModel model, ICamera camera) {
        this.camera = camera;
        this.model = model;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        Point cameraPos = camera.getAdapter().getPosition();

        //Backgrounds
        graphics.draw(IMG_PATH + "menuShader.png", cameraPos.add(-1920/2, -1080/2));
        Point menuPos = cameraPos.add(-300, -400);
        graphics.draw(IMG_PATH + "buttons/pause_menu_bg.png", menuPos);

        //Buttons
        graphics.draw(IMG_PATH + "buttons/resume_game.png", getRealPosition(menuPos, CONTINUE));
        graphics.draw(IMG_PATH + "buttons/save.png", getRealPosition(menuPos, SAVE));
        graphics.draw(IMG_PATH + "buttons/exit.png", getRealPosition(menuPos, EXIT));

        graphics.draw(IMG_PATH + "buttons/overlay.png", getRealPosition(menuPos, model.getCurrentlyMarked()), camera.getAdapter().getWidth(), camera.getAdapter().getHeight());
    }

    private Point getRealPosition(Point menuBoxPos, Point gridPosition) {
        return menuBoxPos.add(offset).add(gridPosition.multiply(new Point(0, -125)));
    }
}
