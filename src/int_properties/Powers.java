package int_properties;

public class Powers implements IntSequence {
    private int exponent;
    private double inverseOfExponent;

    public Powers(int exponent) {
        setExponent(exponent);
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
        int root = (int) Math.round(Math.pow(value, inverseOfExponent));
        return value == (int) Math.pow(root, exponent) ? 1 : 0;
    }

    @Override
    public long nth(int n) {
        return (int) Math.pow(n, exponent);
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
        inverseOfExponent = 1.0 / exponent;
    }

    public int getExponent() {
        return exponent;
    }
}