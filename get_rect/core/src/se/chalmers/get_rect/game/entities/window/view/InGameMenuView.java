package se.chalmers.get_rect.game.entities.window.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.window.model.AbstractGridModel;
import se.chalmers.get_rect.utilities.Point;

public class InGameMenuView extends AbstractView {
    private static final Point CONTINUE = new Point(0 ,0);
    private static final Point EXIT = new Point(0 ,1);
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
        Point cameraPos = camera.getPosition();
        double third = 3.3;
        int cameraWidth = (int)(camera.getAdapter().getWidth()/third) + 10; //divided by three because the menu and inventory gets its own third of the screen
        int cameraHeight = (int)camera.getAdapter().getHeight();

        //Backgrounds
        graphics.draw(IMG_PATH + "menuShader.png", cameraPos);
        graphics.draw(IMG_PATH + "buttons/pause_menu_bg.png", cameraPos.add(cameraWidth - 300, cameraHeight/15));
        graphics.draw(IMG_PATH + "inventory/inventory_bg.png", cameraPos.add(cameraWidth + 332, cameraHeight/6));

        //Buttons
        graphics.draw(IMG_PATH + "buttons/resume_game.png", getRealPosition(CONTINUE));
        graphics.draw(IMG_PATH + "buttons/exit.png", getRealPosition(EXIT));

        graphics.draw(IMG_PATH + "buttons/overlay.png", getRealPosition(model.getCurrentlyMarked()));
    }

    public Point getRealPosition(Point gridPosition) {
        double third = 3.3;
        int cameraWidth = (int)(camera.getAdapter().getWidth()/third);
        if (gridPosition.equals(CONTINUE))
            return camera.getPosition().add(cameraWidth - 225, 390);

        if (gridPosition.equals(EXIT))
            return camera.getPosition().add(cameraWidth - 225, 240);

        return null;
    }
}
