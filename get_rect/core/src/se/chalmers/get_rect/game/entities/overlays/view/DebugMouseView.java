package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.utilities.Point;

public class DebugMouseView {
    private IInputAdapter input;
    private ICameraAdapter camera;

    public DebugMouseView (IInputAdapter input, ICameraAdapter camera) {
        super();
        this.input = input;
        this.camera = camera;
    }

    public Point draw(IGraphicsAdapter graphics, Point position) {
        if (GameConfig.SHOW_MOUSE_POS) {
            position = position.addY(-20);
            Point mousePos = input.getMousePosition();
            Point relativeMousePos = camera.getRelativePosition(mousePos);
            graphics.drawText(relativeMousePos.toString(), position);
        }

        return position;
    }
}
