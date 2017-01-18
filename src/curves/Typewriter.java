package curves;

public class Typewriter implements Curve {
    private int rowLength;

    public Typewriter(int rowLength) {
        this.rowLength = Math.max(rowLength, 1);
    }
    
    public int toInteger(float x, float y) {
        return rowLength * Math.round(y) + Math.round(x);
    }
    
    public void toPoint(int n, Point pt) {
        pt.x = n % rowLength;
        pt.y = n / rowLength;
    }

    @Override
    public void enumerate(int end, Point[] mem, int memStartIndex) {
        int x = 0;
        int y = 0;

        int i = memStartIndex;
        int n = 1;
        while (n <= end) {
            // save position to memory
            if (mem[i] != null) {
                mem[i].x = x;
                mem[i].y = y;
            } else {
                mem[i] = new Point(x, y);
            }

            // advance position
            if (x == rowLength - 1) {
                x = 0;
                y++;
            } else {
                x++;
            }

            // increment index
            i++;
            n++;
        }
    }
    
    private static class TypewriterEnumerator extends CurveEnumerator<Typewriter> {
        private int x, y;
        private int n;
        
        private TypewriterEnumerator(Typewriter curve) {
            super(curve);
        }

        @Override
        public void start() {
            x = 0;
            y = 0;
            n = 1;
        }

        @Override
        public void step(Point pt) {
            // save position to memory
            pt.x = x;
            pt.y = y;

            // advance position
            if (x == curve.rowLength - 1) {
                x = 0;
                y++;
            } else {
                x++;
            }

            // increment index
            n++;
        }
        
    }

    public int getRowLength() {
        return rowLength;
    }

    public void setRowLength(int rowLength) {
        this.rowLength = rowLength;
    }
    
    @Override
    public CurveEnumerator<Typewriter> enumerator() {
        return new TypewriterEnumerator(this);
    }
}