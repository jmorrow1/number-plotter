package curves;

public interface Curve {
	public default Point[] enumerate(int end) {
		Point[] pts = new Point[end];
		enumerate(end, pts, 0);
		return pts;
	}
	public default void enumerate(Point[] mem) {
		enumerate(mem.length, mem, 0);
	}
    public void enumerate(int end, Point[] mem, int memStartIndex);
}