package curves;

import int_properties.IsSquare;

public class SacksSpiral implements Curve {
    private IsSquare isSquare = IsSquare.instance;

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
}