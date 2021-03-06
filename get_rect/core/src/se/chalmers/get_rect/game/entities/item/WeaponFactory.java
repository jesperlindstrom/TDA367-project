package se.chalmers.get_rect.game.entities.item;

import com.google.inject.Inject;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.item.model.*;
import se.chalmers.get_rect.game.entities.item.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;
import se.chalmers.get_rect.game.entities.item.view.*;

public class WeaponFactory {
    @Inject private ProjectileFactory projectileFactory;
    @Inject private SwingFactory swingFactory;
    @Inject private IAudioManagerAdapter audioManager;

    public IWeapon make(String type, IPhysicsModel model, int reach, int damage, int cooldown, int speed, float swingDegrees, int startTilt) {
        switch (type) {
            case "pistol" : return new Pistol(model, projectileFactory, damage, speed, cooldown);
            case "opsword" : return new MeleeWeapon(model, type, swingFactory, reach, damage, cooldown, swingDegrees, startTilt, false);
            case "opaxe" : return new MeleeWeapon(model, type, swingFactory, reach, damage, cooldown, swingDegrees, startTilt, false);
            case "lasersword" : return new MeleeWeapon(model, type, swingFactory, reach, damage, cooldown, swingDegrees, startTilt, false);
            case "paddle" : return new MeleeWeapon(model, type, swingFactory, reach, damage, cooldown, swingDegrees, startTilt, true);
            case "wand_arcane" : return new Wand(model, projectileFactory, damage, speed, cooldown, "arcane");
            case "wand_fire" : return new Wand(model, projectileFactory, damage, speed, cooldown, "fireMagic");
            case "firemagic" : return new FireMagic(model, projectileFactory, damage, speed, cooldown);
            case "rock" : return new Rock(model, projectileFactory, damage, speed, cooldown);
            case "boxingGlove" : return new BoxingGlove(model, type, swingFactory, reach, damage, cooldown, swingDegrees, startTilt, false);
        }
        throw new EntityNotFoundException("item", type);
    }

    public IWeaponView makeView(IWeapon model) {
        switch (model.getType()) {
            case "pistol" : return new PistolView(model, audioManager);
            case "opsword" : return new OpSwordView(model, audioManager);
            case "opaxe" : return new OpAxeView(model, audioManager);
            case "lasersword" : return new LaserSwordView(model, audioManager);
            case "paddle" : return new PaddleView(model, audioManager);
            case "wand" : return new WandView(model, audioManager);
            case "firemagic": return new FireMagicView(model, audioManager);
            case "rock" : return new RockView(model, audioManager);
            case "boxingGlove" : return new BoxingGloveView(model, audioManager);
        }

        throw new RuntimeException("You done fucked up..  Weapon model didn't have a corresponding view");
    }
}
