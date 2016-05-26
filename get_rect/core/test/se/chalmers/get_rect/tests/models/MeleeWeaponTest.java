package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.Entity;
import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.model.MeleeWeapon;
import se.chalmers.get_rect.game.entities.item.swing.Swing;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;
import se.chalmers.get_rect.utilities.Point;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MeleeWeaponTest {

    private MeleeWeapon weapon;
    private SwingFactory factory;
    private IEntityHolder holder;
    private Entity swingEntity;
    private IPhysicsModel model;

    @Before
    public void setup() {
        model = mock(IPhysicsModel.class);
        holder = mock(IEntityHolder.class);
        factory = mock(SwingFactory.class);
        Swing swing = mock(Swing.class);
        swingEntity = new Entity(swing, null);
        weapon = new MeleeWeapon(model, "box", factory, 1, 1, 4, 10, 20, false);
        when(factory.make(1, 1, 4, model, weapon)).thenReturn(swingEntity);
        when(factory.make(1, 1, 4, model, weapon, false)).thenReturn(swingEntity);
        when(model.getVelocity()).thenReturn(new Point(1, 0));
    }

    @Test
    public void testGetSwingFactory() throws Exception {
        assertEquals("should be the same factory", weapon.getSwingFactory(), factory);
    }

    @Test
    public void testUse() throws Exception {
        weapon.use(new Point(), holder);
        verify(holder).add(swingEntity);
    }

    @Test
    public void testGetCooldown() throws Exception {
        assertEquals("cooldown should be same", weapon.getCooldown(), 4);
    }

    @Test
    public void testUpdate() throws Exception {
        assertEquals("the weapon should not be on cooldown", weapon.getCooldownFrames(), 0);
        weapon.use(new Point(), holder);
        assertEquals("the weapon should be on max cooldown", weapon.getCooldownFrames(), weapon.getCooldown());
        weapon.update(1);
        assertNotEquals("the weapon should be on cooldown", weapon.getCooldownFrames(), 0);
        weapon.update(1);
        assertNotEquals("the weapon should be on cooldown", weapon.getCooldownFrames(), 0);
        weapon.update(1);
        assertNotEquals("the weapon should be on cooldown", weapon.getCooldownFrames(), 0);
        weapon.update(1);
        assertEquals("the weapon should no longer be on cooldown", weapon.getCooldownFrames(), 0);
    }


    @Test
    public void testGetSwingDegrees() throws Exception {
        assertEquals("should be the same", weapon.getSwingDegrees(), 10, 0.00001);
    }

    @Test
    public void testGetTilt() throws Exception {
        weapon.update(1);
        assertEquals("should be start tilt", weapon.getTilt(), 20);
        weapon.use(new Point(), holder);
        weapon.update(1);
        assertNotEquals("should have tilted", weapon.getTilt(), 20);
        weapon.update(1);
        weapon.update(1);
        weapon.update(1);
        assertEquals("should be back to original position", weapon.getTilt(), 20);
    }

    @Test
    public void testGetTiltFullSpin() throws Exception {
        weapon = new MeleeWeapon(model, "", factory, 1, 1, 4, 360, 20, false);
        weapon.update(1);
        assertEquals("should be start tilt", weapon.getTilt(), 20);
        weapon.use(new Point(), holder);
        weapon.update(1);
        assertNotEquals("should have tilted", weapon.getTilt(), 20);
        weapon.update(1);
        weapon.update(1);
        weapon.update(1);
        assertEquals("should be back to original position", weapon.getTilt(), 20);
    }

}