package se.chalmers.get_rect.tests.models;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import se.chalmers.get_rect.game.entities.item.WeaponRepository;
import se.chalmers.get_rect.game.entities.item.model.MeleeWeapon;
import se.chalmers.get_rect.game.entities.npc.model.SawmillExpress;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.quests.data.Quest;
import se.chalmers.get_rect.game.quests.data.QuestState;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SawmillExpressTest {

    private Player player;
    private QuestManager questManager;
    private List<String> list;
    private WeaponRepository weaponRepository;

    @Before
    public void setup() {
        player = mock(Player.class);
        weaponRepository = mock(WeaponRepository.class);
        list = new ArrayList<>();
        questManager = mock(QuestManager.class);
        list.add("SawmillExpress");
    }

    @Test
    public void testOnInteract() throws Exception {
        IQuest quest = mock(IQuest.class);
        when(quest.getState()).thenReturn(QuestState.IN_PROGRESS);
        when(questManager.get(0)).thenReturn(quest);
        SawmillExpress sawmillExpress = new SawmillExpress(new Point(), new RectangleFactoryAdapterStub(), list,questManager, weaponRepository);

        sawmillExpress.onInteract(player);
        assertEquals("Should be equal to SawmillExpress", "SawmillExpress\n", sawmillExpress.getDialog());
        when(quest.getState()).thenReturn(QuestState.COMPLETED);
        sawmillExpress = new SawmillExpress(new Point(),new RectangleFactoryAdapterStub(),list, questManager, weaponRepository);
        assertTrue("Should be removed", sawmillExpress.shouldBeRemoved());
    }

    @Test
    public void testUpdate() {
        IQuest quest = mock(IQuest.class);
        when(quest.getState()).thenReturn(QuestState.IN_PROGRESS);
        when(questManager.get(0)).thenReturn(quest);
        SawmillExpress sawmillExpress = new SawmillExpress(new Point(), new RectangleFactoryAdapterStub(), list,questManager, weaponRepository);
        sawmillExpress.update(16);
        assertTrue("No velocity in y", sawmillExpress.getVelocity().getY() == 0);
        sawmillExpress.setFlying();
        assertTrue("Should be true",sawmillExpress.isFlying());
        sawmillExpress.update(16);
        assertTrue("Has velocity in y", sawmillExpress.getVelocity().getY() > 0);
    }
    @Test
    public void testOnQuestCompletion() {
        when(questManager.get(0)).thenReturn(new Quest(0, "", QuestState.COMPLETABLE, null, "", ""));
        SawmillExpress sawmillExpress = new SawmillExpress(new Point(), new RectangleFactoryAdapterStub(), list,questManager, weaponRepository);

        MeleeWeapon weapon = mock(MeleeWeapon.class);
        when(weaponRepository.getSingleWeapon("lasersword")).thenReturn(weapon);

        sawmillExpress.onInteract(player);
        sawmillExpress.onInteract(player);

        verify(player, times(1)).addNewWeapon(weapon);
        verify(player, times(1)).addHealth(player.getMaxHealth());

        assertTrue("Should be flying", sawmillExpress.isFlying());

    }

}
