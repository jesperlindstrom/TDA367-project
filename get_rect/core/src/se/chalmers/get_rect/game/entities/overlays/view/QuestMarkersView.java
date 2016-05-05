package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.npc.INpcModel;
import se.chalmers.get_rect.game.entities.overlays.model.NpcList;
import se.chalmers.get_rect.game.quests.QuestState;
import se.chalmers.get_rect.utilities.Point;

public class QuestMarkersView extends AbstractView {
    private NpcList npcList;
    private static final int DRAW_PRIORITY = 55;

    public QuestMarkersView(NpcList npcList) {
        super();
        this.npcList = npcList;
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }


    @Override
    public void draw(IGraphicsAdapter graphics) {
        for (IModel model : npcList.get()) {
            INpcModel npc = (INpcModel)model;
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
