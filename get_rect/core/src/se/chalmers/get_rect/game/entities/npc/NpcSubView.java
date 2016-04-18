package se.chalmers.get_rect.game.entities.npc;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.quests.QuestState;
import se.chalmers.get_rect.utilities.Point;

public class NpcSubView implements IView {
    private INpcModel model;

    public NpcSubView(INpcModel model) {
        this.model = model;
    }


    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (model.getQuestState() == QuestState.AVAILABLE){
            graphics.draw("img/interact/exclamation.png", new Point(model.getPosition().add(75,300)));
        }
        if (model.isDialogVisible()) {
            graphics.drawText(model.getDialog(), model.getPosition().add(new Point(50, 60)));
        }
        if(model.showInteractionHint()){
            graphics.draw("img/interact/e.png",new Point(model.getPosition().add(20,300)));
        }
    }
}
