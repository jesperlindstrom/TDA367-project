package se.chalmers.get_rect.game.quests.data;

import se.chalmers.get_rect.event.Event;

import java.util.List;

public class Quest implements IQuest {
    private QuestState state;
    private List<QuestObjective> objectives;

    public Quest(QuestState state, List<QuestObjective> objectives) {
        this.state = state;
        this.objectives = objectives;
    }

    private void updateState() {
        // if (state.equals())
    }

    @Override
    public QuestState getState() {
        return state;
    }

    @Override
    public void handleEvent(Event e) {

    }
}
