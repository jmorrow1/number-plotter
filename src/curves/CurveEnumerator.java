package curves;

import int_properties.IntSequence;

public abstract class CurveEnumerator<T extends Curve> {
    protected T curve;
    protected int index;
    protected IntSequence sequence;
    
    public CurveEnumerator(T curve) {
        this.curve = curve;
        start();
    }
    
    public abstract void start();
    public abstract void step(Point pt);
    public Point step() {
        Point pt = new Point(0, 0);
        step(pt);
        return pt;
    }
    
    public int getIndex() {
        return index;
    }
}
