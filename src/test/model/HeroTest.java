package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HeroTest {
    Hero link;

    @BeforeEach
    public void setup() {
        link = new Hero("Link");
    }

    @Test
    public void testConstructor() {
        assertEquals("Link", link.getName());
        assertEquals(10, link.getHealth());
        assertEquals(0, link.getExperience());
        assertEquals(1, link.getLevel());
        assertEquals(1000, link.getLevelUpExpRequired());
        assertEquals(0, link.getGold());
        assertEquals(0, link.getWeapons().size());
    }

    @Test
    public void testHeroEnoughSpendingGold() {
        int spend = 100;
        assertEquals(0, link.getGold());
        link.setGold(100);
        assertEquals(100, link.getGold());
        link.heroGoldCostAmount(spend);
        assertEquals(0, link.getGold());
    }

    @Test
    public void testHeroOverSpendingGold() {
        int spend = 100;
        assertEquals(0, link.getGold());
        link.setGold(50);
        assertEquals(50, link.getGold());
        link.heroGoldCostAmount(spend);
        assertEquals(-50, link.getGold());
    }

    @Test
    public void testHeroSpendingGoldTwice() {
        int spend = 100;
        int spend2 = 50;
        assertEquals(0, link.getGold());
        link.setGold(300);
        assertEquals(300, link.getGold());
        link.heroGoldCostAmount(spend);
        assertEquals(200, link.getGold());
        link.heroGoldCostAmount(spend2);
        assertEquals(150, link.getGold());
    }

    @Test
    public void testHeroGetMaxHealthStartLevel() {
        assertEquals(1, link.getLevel());
        assertEquals(10, link.heroMaxHealth());
    }

    @Test
    public void testHeroGetMaxHealthHighLevel() {
        link.setLevel(2);
        assertEquals(2, link.getLevel());
        assertEquals(20, link.heroMaxHealth());
        link.setLevel(3);
        assertEquals(3, link.getLevel());
        assertEquals(30, link.heroMaxHealth());
    }

    @Test
    public void testHeroHealHealth() {
        int heal = 20;
        int heal2 = 10;
        assertEquals(10, link.getHealth());
        link.heroHeal(heal);
        assertEquals(30, link.getHealth());
        link.heroHeal(heal2);
        assertEquals(40, link.getHealth());
    }

    @Test
    public void testHeroDamageHealth() {
        int damage = 5;
        int damage2 = 2;
        assertEquals(10, link.getHealth());
        link.damageToHero(damage);
        assertEquals(5, link.getHealth());
        link.damageToHero(damage2);
        assertEquals(3, link.getHealth());
    }

    @Test
    public void testValidHeroDeath() {
        link.setHealth(0);
        assertEquals(0, link.getHealth());
        assertTrue(link.heroDeath());
    }

    @Test
    public void testInvalidHeroDeath() {
        link.setHealth(5);
        assertEquals(5, link.getHealth());
        assertFalse(link.heroDeath());
    }

    @Test
    public void testHeroGainGold() {
        int gold = 20;
        int gold2 = 10;
        assertEquals(0, link.getGold());
        link.heroGainGold(gold);
        assertEquals(20, link.getGold());
        link.heroGainGold(gold2);
        assertEquals(30, link.getGold());
    }

    @Test
    public void testHeroGainExperience() {
        int experience = 200;
        int experience2 = 400;
        assertEquals(0, link.getExperience());
        link.heroGainExperience(experience);
        assertEquals(200, link.getExperience());
        link.heroGainExperience(experience2);
        assertEquals(600, link.getExperience());
    }

    @Test
    public void testHeroGainSingleLevelUp() {
        int experience = 2000;
        link.heroGainExperience(experience);
        link.heroLevelUp();
        assertEquals(2, link.getLevel());
        assertEquals(1000, link.getExperience());
        assertEquals(20, link.getHealth());
        assertEquals(2000, link.getLevelUpExpRequired());
    }

    @Test
    public void testHeroGainDoubleLevelUp() {
        int experience = 3000;
        link.heroGainExperience(experience);
        link.heroLevelUp();
        assertEquals(3, link.getLevel());
        assertEquals(0, link.getExperience());
        assertEquals(30, link.getHealth());
        assertEquals(6000, link.getLevelUpExpRequired());
    }

    @Test
    public void testHeroAddOneWeapon() {
        String testWeapon = "Sword";
        link.addWeapon(testWeapon);
        assertEquals(1, link.getWeapons().size());
        assertTrue(link.determineWeaponPresent(link.getWeapons().get(0)));
    }

    @Test
    public void testHeroAddTwoWeapon() {
        String testWeapon = "Sword";
        link.addWeapon(testWeapon);
        assertEquals(1, link.getWeapons().size());
        assertTrue(link.determineWeaponPresent(link.getWeapons().get(0)));
        String testWeapon2 = "Dagger";
        link.addWeapon(testWeapon2);
        assertEquals(2, link.getWeapons().size());
        assertTrue(link.determineWeaponPresent(link.getWeapons().get(1)));
    }


    @Test
    public void testHeroRemoveOneWeapon() {
        String testWeapon = "Sword";
        link.addWeapon(testWeapon);
        assertEquals(1, link.getWeapons().size());
        assertTrue(link.determineWeaponPresent(link.getWeapons().get(0)));
        int firstSlot = 1;
        link.removeWeapon(firstSlot);
        assertEquals(0, link.getWeapons().size());
    }

    @Test
    public void testHeroRemoveTwoWeapon() {
        String testWeapon = "Sword";
        link.addWeapon(testWeapon);
        Weapon sword = link.getWeapons().get(0);
        assertEquals(1, link.getWeapons().size());
        assertTrue(link.determineWeaponPresent(sword));
        String testWeapon2 = "Dagger";
        link.addWeapon(testWeapon2);
        Weapon dagger = link.getWeapons().get(1);
        assertEquals(2, link.getWeapons().size());
        assertTrue(link.determineWeaponPresent(dagger));
        String testWeapon3 = "Axe";
        link.addWeapon(testWeapon3);
        Weapon axe = link.getWeapons().get(2);
        assertEquals(3, link.getWeapons().size());
        assertTrue(link.determineWeaponPresent(axe));
//        int firstSlot = 1;
//        link.removeWeapon(firstSlot);
//        assertEquals(2, link.getWeapons().size());
//        assertTrue(link.determineWeaponPresent(link.getWeapons().get(1)));
//        assertTrue(link.determineWeaponPresent(link.getWeapons().get(0)));
        int secondSlot = 2;
        link.removeWeapon(secondSlot);
        assertEquals(2, link.getWeapons().size());
        assertEquals(sword, link.getWeapons().get(0));
        assertEquals(axe, link.getWeapons().get(1));
        assertTrue(link.determineWeaponPresent(link.getWeapons().get(0)));
        assertTrue(link.determineWeaponPresent(link.getWeapons().get(1)));
        int firstSlot = 1;
        link.removeWeapon(firstSlot);
        assertEquals(1, link.getWeapons().size());
        assertEquals(axe, link.getWeapons().get(0));
        assertTrue(link.determineWeaponPresent(link.getWeapons().get(0)));
    }

    @Test
    public void testRetrieveWeaponAtSlotNumber() {
        String testWeapon = "Sword";
        link.addWeapon(testWeapon);
        assertEquals(1, link.getWeapons().size());
        assertTrue(link.determineWeaponPresent(link.getWeapons().get(0)));
        assertEquals(link.getWeapons().get(0), link.weaponAtSlotNumber(1));
    }

}