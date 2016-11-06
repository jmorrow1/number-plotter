package curves;

public class Typewriter implements Curve {
	private int rowLength;
    
    public Typewriter(int rowLength) {
        this.rowLength = Math.max(rowLength, 1);
    }
    
    @Override
    public void enumerate(int end, Point[] mem, int memStartIndex) {
    	int x = 0;
        int y = 0;
        
        int i = memStartIndex;
        int n = 1;
        while (n <= end) {
        	//save position to memory
            if (mem[i] != null) {
            	mem[i].x = x;
            	mem[i].y = y;
            }
            else {
            	mem[i] = new Point(x, y);
            }
            
            //advance position
            if (x == rowLength-1) {
                x = 0;
                y++;
            }
            else {
            	x++;
            }
            
            //increment index
            i++;
            n++;
        }
    }

	public int getRowLength() {
		return rowLength;
	}

	public void setRowLength(int rowLength) {
		this.rowLength = rowLength;
	}
}