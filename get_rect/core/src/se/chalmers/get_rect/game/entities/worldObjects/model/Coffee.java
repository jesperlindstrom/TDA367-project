package se.chalmers.get_rect.game.entities.worldObjects.model;

import se.chalmers.get_rect.game.entities.AbstractInteractableModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.Objective;
import se.chalmers.get_rect.game.quests.data.QuestState;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public class Coffee extends AbstractInteractableModel {
    private static final int QUEST_ID = 4;
    private boolean interactedWith = false;

    public Coffee(Point position, IRectangleFactoryAdapter factory, QuestManager questManager) {
        super(position, new Point(0,0), false, true, factory);
        setBoundingBox(100, 100);
        interactedWith = isCompleted(questManager.get(QUEST_ID));
    }

    private boolean isCompleted(IQuest quest) {
        if (quest == null)
            return false;

        if (quest.getState().equals(QuestState.COMPLETED))
            return true;

        for (Objective obj : quest.getObjectives()) {
            if (obj.getType().equals("coffee") && obj.getAction().equals("interacted")) {
                return obj.isCompleted();
            }
        }

        return false;
    }

    @Override
    public void onInteract(IModel model) {
        triggerEvent("coffee", "interacted");
            interactedWith = true;
    }
    public boolean getInteractedWith(){
        return interactedWith;
    }
}
