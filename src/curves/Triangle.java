package curves;

public class Triangle implements Curve {
    private int dRowWidth;

    public Triangle(int dRowWidth) {
        this.dRowWidth = dRowWidth;
    }

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

    public int getdRowWidth() {
        return dRowWidth;
    }

    public void setdRowWidth(int dRowWidth) {
        this.dRowWidth = dRowWidth;
    }
}