package se.chalmers.get_rect.game.entities.npc.model;

import se.chalmers.get_rect.game.entities.IInteractableModel;
import se.chalmers.get_rect.game.quests.data.QuestState;

public interface INpcModel extends IInteractableModel {
    QuestState getQuestState();
}