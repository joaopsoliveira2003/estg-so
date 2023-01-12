package enumerations;

/**
 * This enumeration represents the direction of the train
 */
public enum Direction {
    FORWARD, BACKWARD;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
