package persistence;

import org.json.JSONObject;

public interface Writable {
    // Code snippet from: WorkRoomApp
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
