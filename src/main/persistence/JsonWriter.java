package persistence;

import model.Hero;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of hero to file
public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // Code snippet from: WorkRoomApp
    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // Code snippet from: WorkRoomApp
    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // Code snippet from: WorkRoomApp
    // MODIFIES: this
    // EFFECTS: writes JSON representation of hero to file
    public void write(Hero hero) {
        JSONObject json = hero.toJson();
        saveToFile(json.toString(TAB));
    }

    // Code snippet from: WorkRoomApp
    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // Code snippet from: WorkRoomApp
    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

}
