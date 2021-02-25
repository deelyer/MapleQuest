package persistence;

import model.Hero;
import model.Weapon;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    // Code snippet from: WorkRoomApp
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noFile.json");
        try {
            Hero hero = reader.read();
            fail("IOException expected.");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyWeaponHero() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWeaponHero.json");
        try {
            Hero hero = reader.read();
            assertEquals("My Hero", hero.getName());
            assertEquals(0, hero.getWeapons().size());
        } catch (IOException e) {
            fail("Couldn't read from file.");
        }
    }

    @Test
    public void testReaderGeneralWeaponHero() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWeaponHero.json");
        try {
            Hero hero = reader.read();
            assertEquals("My Hero", hero.getName());
            List<Weapon> weapons = hero.getWeapons();
            assertEquals(2, weapons.size());
            checkWeapon("Sword", 10, 1, weapons.get(0));
            checkWeapon("Dagger", 20, 2, weapons.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file.");
        }
    }

}
