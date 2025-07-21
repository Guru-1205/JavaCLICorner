package grid;

import java.util.Objects;

public class Obstacle {
    private int x;
    private int y;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Obstacle obstacle = (Obstacle) o;
        return x == obstacle.x && y == obstacle.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
