package se.chalmers.get_rect.game.overlays.quests;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.IGameComponent;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.npc.INpcModel;
import se.chalmers.get_rect.game.quests.QuestState;
import se.chalmers.get_rect.utilities.Point;

import java.util.List;
import java.util.stream.Collectors;

public class QuestMarkerOverlay implements IGameComponent {
    List<IEntity> entities;

    public QuestMarkerOverlay(List<IEntity> entity) {
        entities = entity;
    }

    private List<IEntity> getNpcs() {
        return entities.stream().filter((e) -> e.getModel() instanceof INpcModel).collect(Collectors.toList());
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        for (IEntity entity : getNpcs()) {
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

        if(model.showInteractionHint()){
            graphics.draw("img/interact/e.png",new Point(model.getPosition().add(20,300)));
        }
    }
}
