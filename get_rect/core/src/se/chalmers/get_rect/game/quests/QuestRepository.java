package se.chalmers.get_rect.game.quests;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.Objective;
import se.chalmers.get_rect.game.quests.data.Quest;
import se.chalmers.get_rect.game.quests.data.QuestState;

import java.util.ArrayList;
import java.util.List;

class QuestRepository {
    @Inject private QuestFactory factory;

    public IQuest get(Integer id) {
        List<Objective> objectives = new ArrayList<>();
        objectives.add(new Objective("zombie", "died", 5, 0));

        return factory.make(QuestState.AVAILABLE, objectives);
    }
}
