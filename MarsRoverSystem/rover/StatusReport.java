package rover;

public class StatusReport {
    private int x;
    private int y;
    private Direction direction;

    public StatusReport(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Rover is at (" + x + ", " + y + ") facing " + direction;
    }
}
