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

    private String getQuestStateImage() {
        if (model.getQuestState() == QuestState.IN_PROGRESS)
            return "img/interact/quest_in_progress.png";
        if (model.getQuestState() == QuestState.AVAILABLE)
            return "img/interact/quest_available.png";
        if (model.getQuestState() == QuestState.COMPLETABLE)
            return "img/interact/quest_completable.png";
        return null;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {

        String questStateImage = getQuestStateImage();

        if (questStateImage != null) {
            graphics.draw(questStateImage, new Point(model.getPosition().add(75,300)));
        }

        if(model.showInteractionHint()){
            graphics.draw("img/interact/e.png",new Point(model.getPosition().add(20,300)));
        }
    }
}
