package int_properties;

public class IsInteger implements IntSequence {

    public final static IsInteger instance = new IsInteger();

    private IsInteger() {
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
