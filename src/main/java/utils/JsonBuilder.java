package utils;

import javafx.scene.image.Image;
import models.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

/**
 * Json Builder Class to set models from the data provided in the specified json file
 */
public class JsonBuilder {
    private JSONParser jsonParser;

    /**
     * Constructor for JsonBuilder class
     *
     * @param jsonParser Json file parser
     */
    public JsonBuilder(JSONParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    /**
     * Create Player with Rooms and Items specified in the json file and set to Player model
     *
     * @return Player with rooms and items
     */
    public models.Player createPlayer() {
        JSONObject jsonObject = JsonParser.getJson();
        ItemSet playerItems;
        ArrayList<Room> rooms = new ArrayList<>();

        //Get rooms and items information from json file
        JSONObject roomsJSON = (JSONObject) jsonObject.get("rooms");
        JSONObject itemsJSON = (JSONObject) jsonObject.get("items");

        // To create rooms from roomsJSON object
        this.createRooms(roomsJSON, rooms);

        playerItems = this.setPlayerItems(itemsJSON);
        return new Player(rooms.get(0), Direction.SOUTH, playerItems);
    }

    /**
     * To create Items for Player model specified in the json file
     *
     * @param itemsJSON Json Object with Item data
     * @return Set of Items
     */
    public ItemSet setPlayerItems(JSONObject itemsJSON) {
        ItemSet items = new ItemSet();
        for (Object obj : itemsJSON.keySet()) {

            JSONObject itemJSON = (JSONObject) itemsJSON.get(obj);
            Image itemImage;

            //Get images of the corresponding item and add to the ItemSet
            itemImage = new Image((String) itemJSON.get("image"));
            Item item = new Item((String) obj, itemImage);
            items.addItem(item);
        }
        return items;
    }

    /**
     * To set room for the Room model class
     *
     * @param roomJSON Json Object with rooms specified in the json file
     * @param roomName Name of the room
     * @return Room with contents, exits and views
     */
    public Room setRoom(JSONObject roomJSON, String roomName) {
        ItemSet items = new ItemSet();
        WallsMap walls = new WallsMap();

        //Get contents corresponding to the room from the roomJSON object
        if (roomJSON.containsKey("contents")) {
            this.setRoomContents((JSONArray) roomJSON.get("contents"), items);
        }

        //Get exits corresponding to the room from the roomJSON object
        if (roomJSON.containsKey("exits")) {
            this.setExitWall((JSONObject) roomJSON.get("exits"), walls);
        }

        //Get views corresponding to the room from the roomJSON object
        if (roomJSON.containsKey("views")) {
            this.createRoomsViews((JSONObject) roomJSON.get("views"), walls);
        }

        return new Room(roomName, walls, items);
    }

    /**
     * To set the contents of the Room specified in the json file
     *
     * @param roomContentsJSON Json object with room contents
     * @param items            ItemSet of items
     */

    public void setRoomContents(JSONArray roomContentsJSON, ItemSet items) {
        for (Object obj : roomContentsJSON) {
            Item item = new Item((String) obj, null);
            items.addItem(item);
        }
    }

    /**
     * To set the Exit wall in the WallsMap
     *
     * @param roomExits Json object with exits of the room specified in the json file
     * @param walls     WallsMap with exit room
     */
    public void setExitWall(JSONObject roomExits, WallsMap walls) {
        for (Object obj : roomExits.keySet()) {
            String exitName = (String) obj;
            String nextRoomName = (String) roomExits.get(exitName);

            //Set Exit wall with corresponding room
            Room nextRoom = new Room(nextRoomName, null, null);
            Wall exitWall = new Wall(null, nextRoom);
            Direction direction = Direction.valueOf(exitName.toUpperCase());
            walls.add(direction, exitWall);
        }
    }

    /**
     * To create all the Rooms specified in the json file
     *
     * @param roomsJSON Json Object of all the rooms
     * @param rooms     ArrayList which will have list of all the rooms
     */
    public void createRooms(JSONObject roomsJSON, ArrayList<Room> rooms) {
        for (Object obj : roomsJSON.keySet()) {
            JSONObject roomJSON = (JSONObject) roomsJSON.get(obj);
            rooms.add(this.setRoom(roomJSON, (String) obj));
        }
        if (rooms != null && !rooms.isEmpty()) {

            //Update the Rooms with exit wall
            for (Room roomUpdate : rooms) {
                WallsMap allDirection = roomUpdate.getWallsMap();
                Wall exitDirection;

                //Get the Exit wall of the room from the WallsMap
                for (var eachWall : allDirection.getWalls().entrySet()) {
                    exitDirection = eachWall.getValue();
                    if (exitDirection.hasAnotherRoom() && exitDirection.getRoom() != null) {

                        //From exit room name get the corresponding room and set the wall with room
                        for (Room room : rooms) {
                            if (room.getName().equals(exitDirection.getRoom().getName())) {
                                exitDirection.setRoom(room);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * To create views of each direction of the room specified in the json file
     *
     * @param roomViewsJSON Json Object with views of each direction for all the rooms
     * @param walls         WallsMap which will contain all the views
     */
    public void createRoomsViews(JSONObject roomViewsJSON, WallsMap walls) {

        for (Object obj : roomViewsJSON.keySet()) {
            Image image;
            Wall viewWall;
            image = new Image((String) roomViewsJSON.get(obj));

            Wall exitWall = walls.getWall(Direction.valueOf(((String) obj).toUpperCase()));
            if (exitWall != null) {
                viewWall = new Wall(image, exitWall.getRoom());
            } else {
                viewWall = new Wall(image, null);
            }

            // Add views for each direction of the wall
            Direction direction = Direction.valueOf(((String) obj).toUpperCase());
            walls.add(direction, viewWall);
        }
    }
}