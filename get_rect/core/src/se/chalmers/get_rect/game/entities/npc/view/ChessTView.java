package se.chalmers.get_rect.game.entities.npc.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.npc.model.ChessT;


public class ChessTView extends AbstractAnimatedView {

    private static final int QUIET = 1;
    private static final int OPEN = 2;
    private static final int TALKING = 3;
    private ChessT model;


    public ChessTView(ChessT model) {
        super(model, 1);
        this.model = model;
        addAnimationFrame(QUIET, "img/entities/chesst/chezz_closed.png");
        addAnimationFrame(TALKING, "img/entities/chesst/chezz_open_1.png", 10);
        addAnimationFrame(TALKING, "img/entities/chesst/chezz_open_2.png", 10);
        addAnimationFrame(OPEN, "img/entities/chesst/chezz_open_2.png");
    }

    private int getSequence() {
       return model.getState();
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        playSequence(getSequence());

        super.draw(graphics);
    }
}
