package curves;

public class Point {
    public float x, y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point pt) {
        this(pt.x, pt.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
