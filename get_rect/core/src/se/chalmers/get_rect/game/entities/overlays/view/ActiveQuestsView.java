package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.game.quests.data.IQuest;

public class ActiveQuestsView extends AbstractView {
    private QuestManager questManager;

    public ActiveQuestsView(QuestManager questManager) {
        this.questManager = questManager;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        for (IQuest quest : questManager.getAllActive()) {
            System.out.println(quest.getTitle());
        }
    }
}
