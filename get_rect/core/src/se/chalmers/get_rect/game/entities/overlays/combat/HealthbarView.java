package se.chalmers.get_rect.game.entities.overlays.combat;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.utilities.Point;

public class HealthbarView implements IView {

    private CombatList combatList;

    public HealthbarView(CombatList combatList) {
        this.combatList = combatList;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        for (IEntity entity : combatList.get()) {
            ICombatModel interactable = (ICombatModel) entity.getModel();
            drawHealthbar(graphics, interactable);
        }
    }

    public void drawHealthbar(IGraphicsAdapter graphics, ICombatModel model) {
        if (model.getcurrentHealth() < 100){
            graphics.draw("img/interact/e.png",new Point(model.getPosition().add(20,300)));
        }
    }
}
