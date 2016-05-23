package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.Objective;
import se.chalmers.get_rect.game.quests.data.QuestState;
import se.chalmers.get_rect.utilities.Point;

public class ActiveQuestsView extends AbstractView {
    private static final int DRAW_PRIORITY = 9;
    private static final String TOP_BG = "img/interact/quest_top.png";
    private static final String OBJECTIVE_BG = "img/interact/objective_bg.png";
    private QuestManager questManager;
    private ICamera camera;

    public ActiveQuestsView(QuestManager questManager, ICamera camera) {
        this.questManager = questManager;
        this.camera = camera;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        int x = camera.getPosition().getX() + (int)camera.getAdapter().getWidth() - 300;
        int y = camera.getPosition().getY() + (int)camera.getAdapter().getHeight() - 200;

        Point pos = new Point(x, y);

        for (IQuest quest : questManager.getAllActive()) {
            pos = drawQuest(graphics, pos, quest);
        }
    }

    public Point drawQuest(IGraphicsAdapter graphics, Point pos, IQuest quest) {
        graphics.draw(TOP_BG, pos.addY(-22).addX(-30));
        graphics.drawText(quest.getTitle(), pos, IGraphicsAdapter.Colors.WHITE);

        if (quest.getState().equals(QuestState.COMPLETABLE)) {
            pos = drawObjective(graphics, pos, "Talk to quest NPC to complete.");
        } else {
            for (Objective obj : quest.getObjectives()) {
                pos = drawObjective(graphics, pos, obj.getInfoText());
                String progress = obj.getCount() + " / " + obj.getRequiredCount();

                IGraphicsAdapter.Colors color = obj.isCompleted() ? IGraphicsAdapter.Colors.BLACK : IGraphicsAdapter.Colors.RED;

                graphics.drawText(progress, pos.addX(250), color);
            }
        }

        return pos.addY(-50);
    }

    private Point drawObjective(IGraphicsAdapter graphics, Point pos, String text) {
        pos = pos.addY(-30);
        graphics.draw(OBJECTIVE_BG, pos.addY(-22).addX(-30));
        graphics.drawText(text, pos, IGraphicsAdapter.Colors.BLACK);

        return pos;
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }
}
