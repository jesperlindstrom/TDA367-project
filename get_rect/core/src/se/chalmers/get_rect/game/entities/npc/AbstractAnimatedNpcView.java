package se.chalmers.get_rect.game.entities.npc;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractAnimatedView;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.quests.QuestState;
import se.chalmers.get_rect.utilities.Point;


public abstract class AbstractAnimatedNpcView extends AbstractAnimatedView {

    private INpcModel model;

    /**
     * Create a new animated view
     *
     * @param model           The model to use for positioning
     * @param defaultSequence The default sequence ID
     */
    protected AbstractAnimatedNpcView(IModel model, int defaultSequence) {
        super(model, defaultSequence);
        if(model instanceof INpcModel){
            this.model = (INpcModel)model;
        }
    }

    @Override
    public void draw(IGraphicsAdapter graphics){

        if (model.getQuestState() == QuestState.AVAILABLE){
            graphics.draw("img/interact/exclamation.png", new Point(model.getPosition().add(75,300)));
        }
        if (model.isDialogVisible()) {
            graphics.drawText(model.getDialog(), model.getPosition().add(new Point(50, 60)));
        }
        if(model.showInteractionHint()){
            graphics.draw("img/interact/e.png",new Point(model.getPosition().add(20,300)));
        }
        super.draw(graphics);

    }
}
