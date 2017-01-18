package curves;

import int_properties.IntSequence;

public class Triangle implements Curve {
    private int dRowWidth;

    public Triangle(int dRowWidth) {
        this.dRowWidth = dRowWidth;
    }
    
//    public int toInteger(float x, float y) {
//        
//    }
//    
//    public void toPoint(int n, Point pt) {
//        
//    }

    @Override
    public void enumerate(int end, Point[] mem, int memStartIndex) {
        int x = 0;
        int y = 0;
        int rowWidth = 1;

        int i = memStartIndex;
        int n = 1;
        while (n <= end) {
            // save position to memory
            if (mem[i] != null) {
                mem[i].x = -rowWidth / 2 + x;
                mem[i].y = y;
            } else {
                mem[i] = new Point(-rowWidth / 2 + x, y);
            }

            // advance position
            if (x == rowWidth) {
                x = 0;
                y++;
                rowWidth += dRowWidth;
            } else {
                x++;
            }

            // increment index
            i++;
            n++;
        }
    }
    
    private static class TriangleEnumerator extends CurveEnumerator<Triangle> {
        private int x, y;
        private int rowWidth;
        
        private TriangleEnumerator(Triangle curve) {
            super(curve);
            start();
        }
        
        public void start() {
            index = 0;
            x = 0;
            y = 0;
            rowWidth = 1;
        }
        
        public void step(Point pt) {
            // save position to memory   
            pt.x = -rowWidth / 2 + x;
            pt.y = y;        
            
            // advance position
            if (x == rowWidth) {
                x = 0;
                y++;
                rowWidth += curve.dRowWidth;
            } else {
                x++;
            }
    
            // increment index
            index++;
        }
    }

    public int getdRowWidth() {
        return dRowWidth;
    }

    public void setdRowWidth(int dRowWidth) {
        this.dRowWidth = dRowWidth;
    }
    
    @Override
    public CurveEnumerator<Triangle> enumerator() {
        return new TriangleEnumerator(this);
    }
}