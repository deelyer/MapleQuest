package persistence;

import model.Hero;
import model.Weapon;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            Hero hero = new Hero("My Hero");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyWeaponHero() {
        try {
            Hero hero = new Hero("My Hero");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWeaponHero.json");
            writer.open();
            writer.write(hero);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWeaponHero.json");
            hero = reader.read();
            assertEquals("My Hero", hero.getName());
            assertEquals(0, hero.getWeapons().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    public void testWriterGeneralWeaponHero() {
        try {
            Hero hero = new Hero("My Hero");
            hero.addWeapon(new Weapon("Sword", 10, 1));
            hero.addWeapon(new Weapon("Dagger", 20, 2));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWeaponHero.json");
            writer.open();
            writer.write(hero);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWeaponHero.json");
            hero = reader.read();
            assertEquals("My Hero", hero.getName());
            List<Weapon> weapons = hero.getWeapons();
            assertEquals(2, weapons.size());
            checkWeapon("Sword", 10,1, weapons.get(0));
            checkWeapon("Dagger",20,2,weapons.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown.");
        }
    }

}
