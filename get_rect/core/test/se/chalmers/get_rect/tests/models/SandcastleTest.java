package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.item.WeaponRepository;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.worldObjects.model.SandCastle;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.Objective;
import se.chalmers.get_rect.game.quests.data.QuestState;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SandcastleTest {

    private Player player;
    private SandCastle sandCastle;
    private WeaponRepository weaponRepository;

    @Before
    public void setup(){
        player = mock(Player.class);
        QuestManager questManager = mock(QuestManager.class);
        when(questManager.get(0)).thenReturn(null);
        weaponRepository = mock(WeaponRepository.class);
        sandCastle = new SandCastle(new Point(), new RectangleFactoryAdapterStub(), questManager, weaponRepository);
    }

    @Test
    public void testInteractWithNull(){
        assertFalse("Should be false, no one interacted", sandCastle.getInteractedWith());
        sandCastle.onInteract(player);
        assertTrue("Should be true, player interacted", sandCastle.getInteractedWith());
    }

    @Test
    public void testInteractWithCompleted(){
        IQuest quest = mock(IQuest.class);
        when(quest.getState()).thenReturn(QuestState.IN_PROGRESS);
        Objective objective = new Objective("sandCastle", "interacted", 1,1,"Done");
        List<Objective> list = new ArrayList<>();
        list.add(objective);
        when(quest.getObjectives()).thenReturn(list);
        QuestManager questManager = mock(QuestManager.class);
        when(questManager.get(0)).thenReturn(quest);
    }

    @Test
    public void givePaddle() {
        IWeapon weapon = mock(IWeapon.class);
        when(weaponRepository.getSingleWeapon("paddle")).thenReturn(weapon);
        verify(player, never()).addNewWeapon(any(IWeapon.class));
        sandCastle.onInteract(player);
        verify(player, never()).addNewWeapon(any(IWeapon.class));
        sandCastle.onInteract(player);
        verify(player, never()).addNewWeapon(any(IWeapon.class));
        sandCastle.onInteract(player);
        verify(player, never()).addNewWeapon(any(IWeapon.class));
        sandCastle.onInteract(player);
        verify(player, never()).addNewWeapon(any(IWeapon.class));
        sandCastle.onInteract(player);
        verify(player, never()).addNewWeapon(any(IWeapon.class));
        sandCastle.onInteract(player);
        verify(player, never()).addNewWeapon(any(IWeapon.class));
        sandCastle.onInteract(player);
        verify(player).addNewWeapon(weapon);
    }
}
