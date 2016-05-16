package se.chalmers.get_rect.game.entities.npc.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.npc.model.ChessT;


public class ChessTView extends AbstractAnimatedView {

    private static final int QUIET = 1;
    private static final int TALKING = 2;
    private static final int OPEN = 3;
    private ChessT model;


    public ChessTView(ChessT model) {
        super(model, 1);
        this.model = model;
        addAnimationFrame(QUIET, "img/entities/chesst/chezz.png");
        addAnimationFrame(TALKING, "img/entities/chesst/chezzT.png", 10);
        addAnimationFrame(TALKING, "img/entities/chesst/chezz.png", 10);
        addAnimationFrame(OPEN, "img/entities/chesst/chezzT.png");
    }

    private int getSequence() {
        if (model.isOpen() == 1)
            return OPEN;

        if (model.isOpen() == -1)
            return TALKING;

        return QUIET;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        playSequence(getSequence());

        super.draw(graphics);
    }
}
