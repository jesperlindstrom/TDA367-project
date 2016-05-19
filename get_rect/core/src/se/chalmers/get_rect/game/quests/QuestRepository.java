package se.chalmers.get_rect.game.quests;

import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.Quest;

class QuestRepository {
    public IQuest get(Integer id) {
        // todo: read from json, return
        return new Quest(null, null);
    }
}
