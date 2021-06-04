package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 * JSON file parser Class
 */
public class JsonParser {
    /**
     * To parse the model.json file
     *
     * @return JSONObject containing data from the parsed file
     */
    public static JSONObject getJson() {
        JSONObject json = null;
        try {
            JSONParser parser = new JSONParser();
            URL path = JsonParser.class.getResource("/JSON/model.json");
            File file = new File(path.getFile());

            json = (JSONObject) parser.parse(new FileReader(file));

        } catch (IOException | ParseException ex) {
            System.out.println(ex.toString());
            System.out.println("No such file available");
        }
        return json;
    }
}
