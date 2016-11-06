package curves;

public class Diamond implements Curve {
	private int maxRowWidth;
	private int dRowWidth;
	
	public Diamond(int maxRowWidth, int dRowWidth) {
		this.maxRowWidth = Math.max(maxRowWidth, 1);
		this.dRowWidth = Math.max(dRowWidth, 2);
	}

	@Override
	public void enumerate(int end, Point[] mem, int memStartIndex) {
		int x = 0;
		int y = 0;
		int rowWidth = 1;
		boolean incrementingRowWidth = true;
		
		int i = memStartIndex;
		int n = 1;
		while (n <= end) {
			//save position to memory
			if (mem[i] != null) {
				mem[i].x = -rowWidth/2 + x;
				mem[i].y = y;	
			}
			else {
				mem[i] = new Point(-rowWidth/2 + x, y);
			}
			
			//advance position
			if (x == rowWidth) {
				if (maxRowWidth != 1) {
					if (incrementingRowWidth && rowWidth + 2 > maxRowWidth) {
						incrementingRowWidth = false;
					}
					else if (!incrementingRowWidth && rowWidth == 1) {
						incrementingRowWidth = true;
					}
					
					//change row width by one step
					if (incrementingRowWidth) {
						rowWidth += dRowWidth;
					}
					else {
						rowWidth -= dRowWidth;
					}
				}
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

	public int getMaxRowWidth() {
		return maxRowWidth;
	}

	public void setMaxRowWidth(int maxRowWidth) {
		this.maxRowWidth = maxRowWidth;
	}

	public int getdRowWidth() {
		return dRowWidth;
	}

	public void setdRowWidth(int dRowWidth) {
		this.dRowWidth = dRowWidth;
	}
}