package se.chalmers.get_rect.game.quests;

import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.Objective;
import se.chalmers.get_rect.game.quests.data.Quest;
import se.chalmers.get_rect.game.quests.data.QuestState;

import java.util.List;

public class QuestFactory {
    public IQuest make(QuestState state, List<Objective> objectives) {
        return new Quest(QuestState.AVAILABLE, objectives);
    }
}
