package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MonsterTest {
    Monster ganondorf;

    @BeforeEach
    public void setup() {
        ganondorf = new Monster();
    }

    @Test
    public void testConstructor() {
        assertEquals("Goblin", ganondorf.getName());
        assertEquals(10, ganondorf.getHealth());
        assertEquals(200, ganondorf.getExperience());
        assertEquals(1, ganondorf.getLevel());
        assertEquals(100, ganondorf.getGold());
        assertEquals(2,ganondorf.getDamage());
    }

    @Test
    public void testDealDamageToMonster() {
        int initialHealth = 10;
        ganondorf.setHealth(initialHealth);
        int firstDamage = 2;
        ganondorf.damageToMonster(firstDamage);
        assertEquals(8, ganondorf.getHealth());
        int secondDamage = 4;
        ganondorf.damageToMonster(secondDamage);
        assertEquals(4, ganondorf.getHealth());
    }

    @Test
    public void testScaleMonsterStatisticsInitialHeroLevel() {
        int initialHeroLevel = 1;
        ganondorf.scaleMonsterStatistics(initialHeroLevel);
        assertEquals(10, ganondorf.getHealth());
        assertEquals(200, ganondorf.getExperience());
        assertEquals(1, ganondorf.getLevel());
        assertEquals(100, ganondorf.getGold());
        assertEquals(2,ganondorf.getDamage());
    }

    @Test
    public void testScaleMonsterStatisticsHighHeroLevel() {
        int higherHeroLevel = 5;
        ganondorf.scaleMonsterStatistics(higherHeroLevel);
        assertEquals(4, ganondorf.getLevel());
        assertEquals(40, ganondorf.getHealth());
        assertEquals(800, ganondorf.getExperience());
        assertEquals(400, ganondorf.getGold());
        assertEquals(8,ganondorf.getDamage());
    }

    @Test
    public void testValidMonsterDeath() {
        ganondorf.setHealth(0);
        assertTrue(ganondorf.monsterDeath());
        ganondorf.setHealth(-10);
        assertTrue(ganondorf.monsterDeath());
    }

    @Test
    public void testInvalidMonsterDeath() {
        ganondorf.setHealth(20);
        assertFalse(ganondorf.monsterDeath());
    }
}
