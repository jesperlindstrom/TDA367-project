package se.chalmers.get_rect.tests.models;


import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.item.WeaponRepository;
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

public class SawmillExpressTest {

    private Player player;
    private SawmillExpress sawmillExpress;
    private IQuest quest;
    private QuestManager questManager;
    private List<String> list;
    private WeaponRepository weaponRepository;

    @Before
    public void setup() {
        player = mock(Player.class);
        weaponRepository = mock(WeaponRepository.class);
        list = new ArrayList<>();
        questManager = mock(QuestManager.class);
        quest = mock(IQuest.class);
        when(quest.getState()).thenReturn(QuestState.IN_PROGRESS);
        when(questManager.get(0)).thenReturn(quest);
        list.add("SawmillExpress");
        sawmillExpress = new SawmillExpress(new Point(), new RectangleFactoryAdapterStub(), list,questManager, weaponRepository);
    }

    @Test
    public void testonInteract() throws Exception {
        sawmillExpress.onInteract(player);
        assertEquals("Should be equal to SawmillExpress", "SawmillExpress\n", sawmillExpress.getDialog());
        when(quest.getState()).thenReturn(QuestState.COMPLETED);
        sawmillExpress = new SawmillExpress(new Point(),new RectangleFactoryAdapterStub(),list, questManager, weaponRepository);
        assertTrue("Should be removed", sawmillExpress.shouldBeRemoved());
    }

    @Test
    public void testUpdate(){
        sawmillExpress.update(16);
        assertTrue("No velocity in y", sawmillExpress.getVelocity().getY() == 0);


    }

}
