package int_properties;

public class Integers implements IntSequence {

    public final static Integers instance = new Integers();

    private Integers() {
    }

    @Override
    public int evaluate(long x) {
        return 1;
    }

    @Override
    public int numPossibleStates() {
        return 2;
    }

    @Override
    public long nth(int n) {
        return n;
    }

}
