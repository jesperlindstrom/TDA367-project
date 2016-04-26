package se.chalmers.get_rect.game.entities.overlays.quests;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.npc.INpcModel;
import se.chalmers.get_rect.game.quests.QuestState;
import se.chalmers.get_rect.utilities.Point;

public class QuestMarkersView implements IView {
    private NpcList npcList;

    public QuestMarkersView(NpcList npcList) {
        this.npcList = npcList;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        for (IEntity entity : npcList.get()) {
            INpcModel npc = (INpcModel)entity.getModel();
            drawQuestMarker(graphics, npc);
        }
    }

    private String getQuestStateImage(QuestState state) {
        if (state == QuestState.IN_PROGRESS)
            return "img/interact/quest_in_progress.png";

        if (state == QuestState.AVAILABLE)
            return "img/interact/quest_available.png";

        if (state == QuestState.COMPLETABLE)
            return "img/interact/quest_completable.png";

        return null;
    }

    public void drawQuestMarker(IGraphicsAdapter graphics, INpcModel model) {
        String questStateImage = getQuestStateImage(model.getQuestState());

        if (questStateImage != null) {
            graphics.draw(questStateImage, new Point(model.getPosition().add(75,300)));
        }
    }
}
