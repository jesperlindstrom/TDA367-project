package se.chalmers.get_rect.game.entities.npc.sawmillExpress;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.npc.AbstractAnimatedNpcView;
import se.chalmers.get_rect.game.quests.QuestState;
import se.chalmers.get_rect.utilities.Point;

public class SawmillView extends AbstractAnimatedNpcView {
    private static final int DEFAULT = 1;
    private static final int FLYING = 2;
    private SawmillExpress model;

    public SawmillView(SawmillExpress model) {
        super(model, DEFAULT);
        this.model = model;

        addAnimationFrame(DEFAULT, "img/entities/sawmill/sawmill-express.png");
        addAnimationFrame(FLYING, "img/entities/sawmill/sawmill-express-waaah.png");
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (model.isFlying()) {
            addAnimationFrame(0, "img/interact/exclamation.png");
            playSequence(FLYING);
        } else {
            playSequence(DEFAULT);
        }
        super.draw(graphics);
    }
}
