package persistence;

import model.Weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkWeapon(String name, int weapondamage, int weapontier, Weapon weapon) {
        assertEquals(name, weapon.getWeaponName());
        assertEquals(weapondamage, weapon.getWeaponDamage());
        assertEquals(weapontier, weapon.getWeaponTier());
    }
}
