package int_properties;

import java.util.function.IntPredicate;

public class Mod implements IntProperty {
    private int divisor;

    public Mod(int divisor) {
        this.divisor = Math.max(1, divisor);
    }

    @Override
    public int evaluate(long num) {
        return ((int) num) % divisor;
    }

    @Override
    public int numPossibleStates() {
        return divisor;
    }

    public int getDivisor() {
        return divisor;
    }

    public void setDivisor(int divisor) {
        this.divisor = divisor;
    }
}