package se.chalmers.get_rect.game.entities.worldObjects.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.worldObjects.model.Coffee;

public class CoffeeView extends AbstractAnimatedView{
    private static final int NONE = 0;
    private static final int DONE = 1;

    private Coffee model;

    public CoffeeView(IModel model) {
        super(model, NONE);
        this.model = (Coffee)model;
        addAnimationFrame(NONE, "img/entities/coffee/coffee_before.png");
        addAnimationFrame(DONE, "img/entities/coffee/coffee_after.png");
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if(model.getInteractedWith()){
            playSequence(DONE);
        }else{
            playSequence(NONE);
        }

        super.draw(graphics);
    }
}
