package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.chalmers.get_rect.game.entities.IInteractableModel;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.game.entities.item.model.MeleeWeapon;
import se.chalmers.get_rect.game.entities.item.model.Pistol;
import se.chalmers.get_rect.game.entities.npc.model.SawmillExpress;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.worldObjects.model.BoundingBox;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;


import static org.junit.Assert.*;


public class PlayerTest {

    private Player player;
    private IInteractableModel interactableModel;
    private boolean isDead = false;

    @Before
    public void setup(){
        this.player = new Player(new RectangleFactoryAdapterStub());
        this.interactableModel = Mockito.mock(SawmillExpress.class);
    }

    @Test
    public void testHealth(){
        int oldHealth = player.getCurrentHealth();
        int maxHealth = player.getMaxHealth();
        player.setHealth(10000);
        assertFalse("Should be false, health can't be bigger then maxhealth", player.getCurrentHealth() == 10000);
        assertTrue("Should be true, health should be equals maxhealth", oldHealth == maxHealth);

        player.takeDamage(30);
        oldHealth = player.getCurrentHealth();
        player.addHealth(10);
        assertTrue("Should be true, new hp bigger then old", oldHealth < player.getCurrentHealth() && player.getCurrentHealth() - oldHealth == 10);
        player.addHealth(10000);
        assertTrue("Should be true, health should be equals maxhealth", player.getCurrentHealth() == maxHealth);

        player.takeDamage(30);
        oldHealth = player.getCurrentHealth();
        player.refillHealth();
        assertTrue("Should be true, player health should be maxhealth", player.getCurrentHealth() == maxHealth && player.getCurrentHealth() != oldHealth);

        isDead = false;

        player.addListener((e) -> {
            isDead = true;
        });

        player.takeDamage(player.getCurrentHealth());
        assertTrue("Player has died", isDead);
    }

    @Test
    public void testUpdate(){
        CollisionData playerData = new CollisionData();
        playerData.set(Side.LEFT);
        CollisionData npcData = new CollisionData();
        npcData.set(Side.RIGHT);
        assertNull("Current npc should be null", player.getCurrentNpc());
        player.setActiveWeapon(Mockito.mock(MeleeWeapon.class));
        player.update(16.0);
        player.onCollision(interactableModel, playerData, false);
        interactableModel.onCollision(player, playerData, false);
        assertNotNull(player.getCurrentNpc());
        player.update(16.0);
    }

    @Test
    public void testMovment(){
        player.moveLeft();
        assertTrue("Velocity in x should be -40", player.getVelocity().getX() == -40);
        assertTrue("Should be true", player.isWalking());
        int oldVelX = player.getVelocity().getX();
        player.stopMoving();
        assertTrue("New x velocity should be closer to 0 then old", oldVelX < player.getVelocity().getX());
        assertFalse("Should be false, no move", player.isWalking());

        player.setVelocity(new Point(3, 0));
        player.stopMoving();
        assertEquals("Player should stop", player.getVelocity(), new Point(0, 0));


        player.setRiding(true);
        assertTrue("Should be true, playe is riding", player.isRiding());
        player.moveRight();
        assertEquals("Speed should be 80 in x", player.getVelocity().getX(), 80);

    }

    @Test
    public void testCollision(){
        assertTrue("Should be true, player can jump",player.canJump());
        player.jump();
        assertFalse("Should be false, player is jumping" , player.canJump());
        BoundingBox boundingBox = new BoundingBox(new Point(), 1,1, new RectangleFactoryAdapterStub());
        CollisionData data = new CollisionData();
        data.set(Side.BOTTOM);
        player.onCollision(boundingBox, data, true);
        assertTrue("Should be true, landed on floor", player.canJump());
        player.jump();
        data.set(Side.TOP);
        player.onCollision(boundingBox,data,false);
        assertFalse("Should be false, player not landed but collided", player.canJump());
    }

    @Test
    public void testUse(){
        player.setActiveWeapon(Mockito.mock(IWeapon.class));

        player.use(new Point());
    }


    @Test
    public void testSwitchWeapon(){
        IWeapon melee = Mockito.mock(MeleeWeapon.class);
        IWeapon ranged = Mockito.mock(Pistol.class);

        player.addNewWeapon(ranged);
        player.addNewWeapon(melee);
        player.setActiveWeapon(melee);
        IWeapon oldWeapon = player.getActiveWeapon();
        assertEquals("Active weapon is melee", oldWeapon, melee);
        player.switchWeapon();
        assertEquals("Active weapon should be ranges", player.getActiveWeapon(), ranged);
        player.switchWeapon();
        assertEquals("Active weapon is melee", player.getActiveWeapon(), melee);
    }
}
