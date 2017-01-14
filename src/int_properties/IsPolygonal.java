package int_properties;

public class IsPolygonal implements IntSequence {
    private int n; // number of sides

    public IsPolygonal(int n) {
        this.n = n;
    }

    @Override
    public int numPossibleStates() {
        return 2;
    }

    @Override
    public int evaluate(long x) {
        return 0;
    }

    @Override
    public long nth(int i) {
        return (int) (1 + (n - 1) * (i - 1) + 0.5f * (i - 2) * (i - 1) * (n - 2));
    }

    public void setNumSides(int n) {
        this.n = n;
    }

    public int getNumSides() {
        return n;
    }
}