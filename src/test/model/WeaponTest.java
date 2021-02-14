package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {
    Weapon exalibur;

    @BeforeEach
    public void setup() {
        exalibur = new Weapon("Exalibur");
    }

    @Test
    public void testConstructor() {
        assertEquals("Exalibur", exalibur.getWeaponName());
        assertEquals(10, exalibur.getWeaponDamage());
        assertEquals(1, exalibur.getWeaponTier());
    }

    @Test
    public void testUpgradeWeapon() {
        exalibur.upgradeWeapon();
        assertEquals(2, exalibur.getWeaponTier());
        assertEquals(20, exalibur.getWeaponDamage());
        exalibur.upgradeWeapon();
        assertEquals(3, exalibur.getWeaponTier());
        assertEquals(30, exalibur.getWeaponDamage());
    }

    @Test
    public void testDetermineWeaponMaxTierTrue() {
        int maxTier = 5;
        exalibur.setWeaponTier(maxTier);
        assertTrue(exalibur.weaponMaxTier(maxTier));
    }

    @Test
    public void testDetermineWeaponOverMaxTierTrue() {
        int maxTier = 5;
        exalibur.setWeaponTier(maxTier);
        assertEquals(5, exalibur.getWeaponTier());
        assertTrue(exalibur.weaponMaxTier(maxTier));
    }

    @Test
    public void testDetermineWeaponMaxTierFalse() {
        int maxTier = 5;
        int testWeaponTier = 3;
        exalibur.setWeaponTier(testWeaponTier);
        assertFalse(exalibur.weaponMaxTier(maxTier));
    }

}
