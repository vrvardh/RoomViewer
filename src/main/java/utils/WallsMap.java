package utils;

import javafx.scene.image.Image;
import models.Direction;
import models.Wall;

import java.util.HashMap;

/**
 * Class for Walls HashMap
 */
public class WallsMap {

    private HashMap<Direction, Wall> walls = new HashMap<>();

    /**
     * To get walls from walls HashMap
     *
     * @return Walls HashMap
     */
    public HashMap<Direction, Wall> getWalls() {
        return walls;
    }

    /**
     * Add a wall to the Walls HashMap
     *
     * @param direction specific direction
     * @param walls     Corresponding wall
     */
    public void add(Direction direction, Wall walls) {
        this.walls.put(direction, walls);
    }

    /**
     * To find whether or not a wall is available in the given direction
     *
     * @param direction Desired Direction
     * @return True or False
     */
    public Boolean containsWall(Direction direction) {
        return this.walls.containsKey(direction);
    }

    /**
     * To get the Wall from the given direction
     *
     * @param direction Desired direction
     * @return wall or null
     */
    public Wall getWall(Direction direction) {
        if (this.containsWall(direction)) {
            return this.walls.get(direction);
        }
        return null;
    }

    /**
     * To get the image of the wall based on the direction
     *
     * @param direction Desired direction
     * @return Image of the direction or null
     */
    public Image getWallImage(Direction direction) {
        Wall wall = this.getWall(direction);
        if (wall != null) {
            return wall.getWallImage();
        }
        return null;
    }
}