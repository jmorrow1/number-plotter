package int_properties;

import java.util.function.IntPredicate;

public class IsPower implements IntProperty {
    public final static IsPower instance = new IsPower();

    private IsPower() {
    }

    @Override
    public int numPossibleStates() {
        return 2;
    }

    @Override
    public int evaluate(long value) {
        if (value < 1) {
            return 0;
        }
        
        int exponent = 2;
        
        while (true) {
            float inverseOfExponent = 1f / exponent;
            double realRoot = Math.pow(value, inverseOfExponent);
            int intRoot = (int)realRoot;       
                   
            int pow = (int) Math.pow(intRoot, exponent);
            
            if (value == pow) {
                return 1;
            }
            else if (intRoot == 1) {
                return 0;
            }
            exponent++;
        }
    }
}