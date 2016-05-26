package se.chalmers.get_rect.game.window.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.window.model.GameOverMenu;
import se.chalmers.get_rect.utilities.Point;

public class GameOverView implements IWindowView {

    private GameOverMenu model;
    private ICamera camera;
    private static final Point CONTINUE_FROM_LAST_SAVE = new Point(0, 0);
    private static final Point EXIT = new Point(0, 1);
    private static final int Y_OFFSET = -200;

    public GameOverView(GameOverMenu model, ICamera camera) {
        this.model = model;
        this.camera = camera;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("img/pauseMenu/menuShader.png", camera.getPosition());
        graphics.draw("img/pauseMenu/buttons/continue.png", getRealPosition(CONTINUE_FROM_LAST_SAVE));
        graphics.draw("img/pauseMenu/buttons/exit.png", getRealPosition(EXIT));
        graphics.draw("img/pauseMenu/buttons/overlay.png", getRealPosition(model.getCurrentlyMarked()));
        graphics.draw("img/pauseMenu/buttons/game_over_text_clean.png", camera.getAdapter().getPosition().addX(-270));
    }

    private Point getRealPosition(Point gridPosition) {
        Point center = camera.getAdapter().getPosition();
        return center.add(-225, gridPosition.getY()*-130 + Y_OFFSET);
    }
}
