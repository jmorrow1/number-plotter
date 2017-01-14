package curves;

public class UlamSpiral implements Curve {
    private final int UP = 0, LEFT = 1, DOWN = 2, RIGHT = 3;

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
}