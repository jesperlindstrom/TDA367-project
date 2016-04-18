package se.chalmers.get_rect.game.entities.npc;

import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.quests.QuestState;

public interface INpcModel extends IPhysicsModel {
    void onInteract(IModel model);
    QuestState getQuestState();
    void showDialog(String text);
    String getDialog();
    boolean isDialogVisible();
    boolean showInteractionHint();
}
