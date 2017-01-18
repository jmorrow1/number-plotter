package curves;

import int_properties.IsPower;

public class SacksSpiral implements Curve {
    private IsPower isSquare = IsPower.instance;

//    public int toInteger(float x, float y) {
//        
//    }
//    
//    public void toPoint(int n, Point pt) {
//        
//    }
    
    @Override
    public void enumerate(int end, Point[] mem, int memStartIndex) {
        int radius = 1;
        double theta = 0;
        double dTheta = 2 * Math.PI;

        int i = memStartIndex;
        int n = 1;
        while (n <= end) {
            // save position to memory
            if (mem[i] != null) {
                mem[i].x = (float) (radius * Math.cos(theta));
                mem[i].y = (float) (radius * Math.sin(theta));
            } else {
                mem[i] = new Point((float) (radius * Math.cos(theta)), (float) (radius * Math.sin(theta)));
            }

            // adjust properties of next position
            boolean squareNumber = isSquare.evaluate(n) == 1;
            if (squareNumber) {
                radius++;
                dTheta = (2 * Math.PI) / n;
                theta = 0;
            } else {
                theta += dTheta;
            }

            // increment index
            i++;
            n++;
        }
    }
    
    private static class SacksSpiralEnumerator extends CurveEnumerator<SacksSpiral> {
        private int radius;
        private double theta, dTheta;
        
        private SacksSpiralEnumerator(SacksSpiral curve) {
            super(curve);
        }

        @Override
        public void start() {
            radius = 1;
            theta = 0;
            dTheta = 2 * Math.PI;
            index = 0;
        }

        @Override
        public void step(Point pt) {
            // save position to memory
            pt.x = (float) (radius * Math.cos(theta));
            pt.y = (float) (radius * Math.sin(theta));

            // adjust properties of next position
            boolean squareNumber = curve.isSquare.evaluate(index+1) == 1;
            if (squareNumber) {
                radius++;
                dTheta = (2 * Math.PI) / (index+1);
                theta = 0;
            } else {
                theta += dTheta;
            }

            // increment index
            index++;
        }     
    }
    
    @Override
    public CurveEnumerator<SacksSpiral> enumerator() {
        return new SacksSpiralEnumerator(this);
    }
}