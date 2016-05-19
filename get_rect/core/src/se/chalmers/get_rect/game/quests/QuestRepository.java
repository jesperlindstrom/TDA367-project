package se.chalmers.get_rect.game.quests;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.Objective;
import se.chalmers.get_rect.game.quests.data.QuestState;

import java.util.ArrayList;
import java.util.List;

public class QuestRepository {
    @Inject private QuestFactory factory;

    public IQuest get(int id) {
        // todo: read this from JSON

        List<Objective> objectives = new ArrayList<>();
        objectives.add(new Objective("zombie", "died", 5, 0, "Ninja-Broccolis killed"));
        objectives.add(new Objective("sandCastle", "interacted", 1, 0, "Construct a magnificent sand castle"));

        String acceptText = "Hej!";
        String completionText = "I'M OUTTA HERE! LOLWUT";
        return factory.make(0, QuestState.AVAILABLE, objectives, acceptText, completionText);
    }
}
