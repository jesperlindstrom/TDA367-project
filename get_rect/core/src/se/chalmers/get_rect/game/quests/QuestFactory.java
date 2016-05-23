package se.chalmers.get_rect.game.quests;

import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.Objective;
import se.chalmers.get_rect.game.quests.data.Quest;
import se.chalmers.get_rect.game.quests.data.QuestState;

import java.util.List;

public class QuestFactory {
    public IQuest make(int id, String title, QuestState state, List<Objective> objectives, String acceptText, String completionText) {
        return new Quest(id, title, state, objectives, acceptText, completionText);
    }

    public Objective makeObjective(String type, String action, int required, int count, String info) {
        return new Objective(type, action, required, count, info);
    }
}
