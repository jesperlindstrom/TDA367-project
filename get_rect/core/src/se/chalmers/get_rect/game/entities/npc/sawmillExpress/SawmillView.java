package se.chalmers.get_rect.game.entities.npc.sawmillExpress;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.utilities.Point;

public class SawmillView implements IView{
    private SawmillExpress model;
    private int bla;

    public SawmillView(SawmillExpress SawmillExpress) {
        model = SawmillExpress;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        bla++;
        if (model.isWäääh()) {
            graphics.drawText("Wäääh", model.getPosition().add(new Point(50, 60)));
        }
        if (model.isWäääh()) {
            Point pos = model.getPosition();

            if (bla % 2 == 1) {
                pos = pos.addX(5);
            }

            graphics.draw("img/entities/sawmill/sawmill-express-waaah.png", model.getPosition().addX(5));
        } else {
            graphics.draw("img/entities/sawmill/sawmill-express.png", model.getPosition());
        }

    }
}
