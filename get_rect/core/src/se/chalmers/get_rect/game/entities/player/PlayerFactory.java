package se.chalmers.get_rect.game.entities.player;

import com.google.inject.Inject;
import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.item.WeaponFactory;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;

public class PlayerFactory {
    @Inject private IRectangleFactoryAdapter rectangleFactory;
    @Inject private IAudioManagerAdapter audioManager;
    @Inject private WeaponFactory weaponFactory;

    public IEntity make() {
        Player model = new Player(rectangleFactory);
        IView view = new PlayerView(model, audioManager, weaponFactory);
        return new Entity(model,view);
    }
}
