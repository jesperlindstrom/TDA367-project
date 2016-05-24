package se.chalmers.get_rect.game.window.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.window.model.ErrorWindow;
import se.chalmers.get_rect.utilities.Point;

public class ErrorWindowView implements IWindowView {

    private ErrorWindow model;
    private ICamera camera;

    public ErrorWindowView(ErrorWindow model, ICamera camera) {
        this.model = model;
        this.camera = camera;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        Point cameraPos = camera.getAdapter().getPosition();
        graphics.draw("img/pauseMenu/menuShader.png", cameraPos.add(-1920/2, -1080/2));
        graphics.draw("img/pauseMenu/buttons/exit.png", camera.getAdapter().getPosition().addX(-225));
        graphics.draw("img/pauseMenu/buttons/overlay.png", camera.getAdapter().getPosition().addX(-225));
        graphics.drawText(model.getMessage(), camera.getAdapter().getPosition().addY(150).addX(-225), IGraphicsAdapter.Colors.WHITE);
    }
}
