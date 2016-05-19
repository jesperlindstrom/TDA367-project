package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.input.GameInput;
import se.chalmers.get_rect.utilities.Point;

public class DebugMouseView {
    private GameInput gameInput;
    private ICameraAdapter camera;

    public DebugMouseView (GameInput gameInput, ICameraAdapter camera) {
        super();
        this.gameInput = gameInput;
        this.camera = camera;
    }

    public Point draw(IGraphicsAdapter graphics, Point position) {
        if (GameConfig.SHOW_MOUSE_POS && !GameConfig.DISABLE_ALL) {
            position = position.addY(-20);
            Point mousePos = gameInput.getMousePosition();
            Point relativeMousePos = camera.getRelativePosition(mousePos);
            graphics.drawText(relativeMousePos.toString(), position);
        }

        return position;
    }
}
