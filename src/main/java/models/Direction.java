package models;

/**
 * Enum class for Direction
 */
public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    /**
     * private static copy of the values() array
     * Overcomes array copying each time next() or previous() is called
     */

    private static Direction[] directions = values();

    /**
     * To get the next Direction
     */
    public Direction next() {
        return directions[(ordinal() + 1) % directions.length];
    }

    /**
     * To get the previous Direction
     */
    public Direction previous() {
        return directions[(ordinal() - 1 + directions.length) % directions.length];
    }
}