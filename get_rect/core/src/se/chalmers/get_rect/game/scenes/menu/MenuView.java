package se.chalmers.get_rect.game.scenes.menu;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.utilities.Point;

public class MenuView implements IView {

    private static final String IMG_PATH = "img/pauseMenu/";
    private MenuModel model;

    public MenuView(MenuModel model) {
        this.model = model;
    }


    @Override
    public void draw(IGraphicsAdapter graphics) {
        Point cameraPos = model.getPosition();

        graphics.draw(IMG_PATH + "menuShader.png", cameraPos);
        graphics.draw(IMG_PATH + "pause_menu_bg.png", cameraPos.add(new Point(660, 140)));

    }

}
