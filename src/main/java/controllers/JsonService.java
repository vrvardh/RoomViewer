package controllers;

import models.Player;
import org.json.simple.parser.JSONParser;
import utils.JsonBuilder;

/**
 * Json Service class that constructs the model from json
 */
public class JsonService {

    private JSONParser jsonParser;

    /**
     *
     */
    public JsonService() {
        this.jsonParser = new JSONParser();
    }

    /**
     * @return
     */
    public Player create() {
        if (this.jsonParser == null) {
            return null;
        }
        JsonBuilder jsonBuilder = new JsonBuilder(this.jsonParser);
        return jsonBuilder.createPlayer();
    }

}
