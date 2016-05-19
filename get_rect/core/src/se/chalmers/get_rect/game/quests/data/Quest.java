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

    @Override
    public QuestState getState() {
        return state;
    }

    @Override
    public void handleEvent(Event e) {
        if (state.equals(QuestState.COMPLETED))
            return;

        //for ()
        checkObjectives();
    }

    private void checkObjectives() {
        if (!state.equals(QuestState.IN_PROGRESS))
            return;

        boolean allCompleted = true;

        for (QuestObjective objective : objectives) {
            if (!objective.isCompleted()) {
                allCompleted = false;
            }
        }

        if (allCompleted) {
            state = QuestState.COMPLETABLE;
        }
    }
}
