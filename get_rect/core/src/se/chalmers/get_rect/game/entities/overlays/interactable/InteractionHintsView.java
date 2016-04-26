package se.chalmers.get_rect.game.entities.overlays.interactable;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IInteractableModel;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.utilities.Point;

public class InteractionHintsView implements IView {
    private Player player;

    public InteractionHintsView(Player player) {
        this.player = player;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (player.getCurrentNpc() == null)return;
        graphics.draw("img/interact/e.png", new Point(player.getCurrentNpc().getPosition().add(20, 300)));
    }
}
