package se.chalmers.get_rect.game.entities.npc;

import se.chalmers.get_rect.game.entities.IInteractableModel;
import se.chalmers.get_rect.game.quests.QuestState;

public interface INpcModel extends IInteractableModel {
    QuestState getQuestState();
}