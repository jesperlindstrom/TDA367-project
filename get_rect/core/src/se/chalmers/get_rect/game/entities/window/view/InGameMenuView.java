package se.chalmers.get_rect.game.entities.window.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.window.model.AbstractGridModel;
import se.chalmers.get_rect.utilities.Point;

public class InGameMenuView extends AbstractView {
    private static final Point CONTINUE = new Point(0 ,0);
    private static final Point EXIT = new Point(0 ,1);
    private Point offset = new Point(70, 240);
    private static final String IMG_PATH = "img/pauseMenu/";
    private ICamera camera;
    private AbstractGridModel model;

    public InGameMenuView(AbstractGridModel model, ICamera camera) {
        this.camera = camera;
        this.model = model;
    }

    @Override
    public boolean shouldBeRemoved() {
        return false;
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
        graphics.draw(IMG_PATH + "buttons/exit.png", getRealPosition(menuPos, EXIT));

        graphics.draw(IMG_PATH + "buttons/overlay.png", getRealPosition(menuPos, model.getCurrentlyMarked()));
    }

    public Point getRealPosition(Point menuBoxPos, Point gridPosition) {
        return menuBoxPos.add(offset).add(gridPosition.multiply(new Point(0, -170)));
    }
}
