package persistence;


import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import jdk.jfr.Category;
import model.Hero;
import model.Weapon;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads mapledata from JSON data stored in file
public class JsonReader {
    private String source;

    // Code snippet from: WorkRoomApp
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // Code snippet from: WorkRoomApp
    // EFFECTS: reads hero from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Hero read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHero(jsonObject);
    }

    // Code snippet from: WorkRoomApp
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // Code snippet from: WorkRoomApp
    // EFFECTS: parses hero from JSON object and returns it
    private Hero parseHero(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Hero hero = new Hero(name);
        addWeapons(hero, jsonObject);
        return hero;
    }

    // MODIFIES: hero
    // EFFECTS: parses weapons from JSON object and adds them to hero
    private void addWeapons(Hero hero, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("weapons");
        for (Object json : jsonArray) {
            JSONObject nextWeapon = (JSONObject) json;
            addWeapon(hero, nextWeapon);
        }
    }

    // MODIFIES: md
    // EFFECTS: parses weapon from JSON object and adds it to hero
    private void addWeapon(Hero hero, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int weaponDamage = jsonObject.getInt("weapondamage");
        int weaponTier = jsonObject.getInt("weapontier");
        Weapon weapon = new Weapon(name, weaponDamage, weaponTier);
        hero.addWeapon(weapon);
    }


}
