package int_properties;

import java.util.function.IntPredicate;

public class IsSquare implements IntSequence {
	public final static IsSquare instance = new IsSquare();
	
	private IsSquare() {}

	@Override
	public int numPossibleStates() {
		return 2;
	}
	
	@Override
	public int evaluate(long value) {
		if (value < 1) return 0;
		int root = (int)Math.sqrt(value);
		return value == root*root ? 1 : 0;
	}
	
	@Override
	public long nth(int n) {
		return n*n;
	}
}