package curves;

public class UlamSpiral implements Curve {
    private final static int UP = 0, LEFT = 1, DOWN = 2, RIGHT = 3;

    public UlamSpiral() {
    }

    @Override
    public void enumerate(int end, Point[] mem, int memStartIndex) {
        int x = 0;
        int y = 0;
        int margin = 1;
        int direction = UP;

        int i = memStartIndex;

        // first iteration
        if (1 <= end) {
            // save first position to memory
            if (mem[i] != null) {
                mem[i].x = x;
                mem[i].y = y;
            } else {
                mem[i] = new Point(x, y);
            }
        }
        x++;
        i++;

        // second to last iterations
        int n = 2;
        while (n <= end) {
            // save position to memory
            if (mem[i] != null) {
                mem[i].x = x;
                mem[i].y = y;
            } else {
                mem[i] = new Point(x, y);
            }

            // change direction, if needed
            boolean changeDirection = (direction == RIGHT && x == margin) || (direction == LEFT && x == -margin)
                    || (direction == DOWN && y == margin) || (direction == UP && y == -margin);

            if (changeDirection) {
                if (direction != RIGHT) {
                    direction++;
                    if (direction == RIGHT) {
                        margin++;
                    }
                } else {
                    direction = UP;
                }
            }

            // advance position
            x += (direction == LEFT) ? -1 : (direction == RIGHT) ? 1 : 0;
            y += (direction == UP) ? -1 : (direction == DOWN) ? 1 : 0;

            // increment index
            i++;
            n++;
        }
    }
    
    private static class UlamSpiralEnumerator extends CurveEnumerator<UlamSpiral> {
        private int n, x, y, margin, direction;

        public UlamSpiralEnumerator(UlamSpiral curve) {
            super(curve);
        }

        @Override
        public void start() {
            n = 1;
            x = 0;
            y = 0;
            margin = 1;
            direction = UP;
        }

        @Override
        public void step(Point pt) {
            // save position to memory
            pt.x = x;
            pt.y = y;

            if (n > 1) {
                // change direction, if needed
                boolean changeDirection = (direction == UlamSpiral.RIGHT && x == margin) || (direction == UlamSpiral.LEFT && x == -margin)
                        || (direction == UlamSpiral.DOWN && y == margin) || (direction == UP && y == -margin);

                if (changeDirection) {
                    if (direction != UlamSpiral.RIGHT) {
                        direction++;
                        if (direction == UlamSpiral.RIGHT) {
                            margin++;
                        }
                    } else {
                        direction = UlamSpiral.UP;
                    }
                }

                // advance position
                x += (direction == UlamSpiral.LEFT) ? -1 : (direction == UlamSpiral.RIGHT) ? 1 : 0;
                y += (direction == UlamSpiral.UP) ? -1 : (direction == UlamSpiral.DOWN) ? 1 : 0;

                // increment index
                n++;
            }
        }       
    }
    
    @Override
    public CurveEnumerator<UlamSpiral> enumerator() {
        return new UlamSpiralEnumerator(this);
    }
}