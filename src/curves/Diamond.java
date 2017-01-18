package curves;

public class Diamond implements Curve {
//    private int minRowWidth;
    private int maxRowWidth;
    private int dRowWidth;

    public Diamond(/*int minRowWidth,*/ int maxRowWidth, int dRowWidth) {
//        this.minRowWidth = Math.min(maxRowWidth, minRowWidth);
        this.maxRowWidth = Math.max(maxRowWidth, 1);
        this.dRowWidth = Math.max(dRowWidth, 2);
    }
    
//    public int toInteger(float x, float y) {
//        int diamondHeight = diamondHeight();
//        int diamondIndex = (int)(y / diamondHeight);
//        diamondIndex += (int)(y % diamondHeight);
//
//        //TODO Add x
//        
//        return diamondIndex;
//    }
//    
//    public void toPoint(int n, Point pt) {
//        int diamondSize = diamondSize();
//        int diamondIndex = n / diamondSize;
//        int y = diamondIndex * diamondHeight();       
//        int innerDiamondIndex = n % diamondSize;
//        
//        int rowWidth = 1;
//        while (innerDiamondIndex >= 0) {
//            y += rowWidth;
//            
//            if (innerDiamondIndex <= 0) {
//                int x = -rowWidth/2 - innerDiamondIndex;
//                pt.x = x;
//                pt.y = y;
//                return;
//            }
//            
//            innerDiamondIndex -= rowWidth;
//            rowWidth += dRowWidth;
//        }
//    }
    
    private int diamondHeight() {
        int height = maxRowWidth / dRowWidth;
        if (maxRowWidth % dRowWidth > 0) height++;
        return height;
    }
    
    private int diamondSize() {
        if (dRowWidth == 1) {
            return (int)((maxRowWidth+1)/2f * (maxRowWidth));
        }
        
        int n = 0;
        for (int i=1; i<=maxRowWidth; i++) {
            n += i;
        }
        return n;
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
            // save position to memory
            if (mem[i] != null) {
                mem[i].x = -rowWidth / 2 + x;
                mem[i].y = y;
            }
            else {
                mem[i] = new Point(-rowWidth / 2 + x, y);
            }

            // advance position
            if (x == rowWidth) {
                if (maxRowWidth != 1) {
                    if (incrementingRowWidth && rowWidth + 2 > maxRowWidth) {
                        incrementingRowWidth = false;
                    } 
                    else if (!incrementingRowWidth && rowWidth == 1) {
                        incrementingRowWidth = true;
                    }

                    // change row width by one step
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

            // increment index
            i++;
            n++;
        }
    }
    
    private static class DiamondEnumerator extends CurveEnumerator<Diamond> {
        private int x, y;
        private int rowWidth;
        private boolean incrementingRowWidth;
        
        private DiamondEnumerator(Diamond curve) {
            super(curve);
            start();
        }
        
        public void start() {
            x = 0;
            y = 0;
            rowWidth = 1;
            incrementingRowWidth = true;
            index = 0;
        }
        
        public void step(Point pt) {
            // save position to memory
            pt.x = -rowWidth / 2 + x;
            pt.y = y;

            // advance position
            if (x == rowWidth) {
                if (curve.maxRowWidth != 1) {
                    if (incrementingRowWidth && rowWidth + 2 > curve.maxRowWidth) {
                        incrementingRowWidth = false;
                    } 
                    else if (!incrementingRowWidth && rowWidth == 1) {
                        incrementingRowWidth = true;
                    }

                    // change row width by one step
                    if (incrementingRowWidth) {
                        rowWidth += curve.dRowWidth;
                    }
                    else {
                        rowWidth -= curve.dRowWidth;
                    }
                }
                x = 0;
                y++;
            }
            else {
                x++;
            }

            // increment index
            index++;
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
    
    @Override
    public CurveEnumerator<Diamond> enumerator() {
        return new DiamondEnumerator(this);
    }
}