package int_properties;

import java.util.function.IntPredicate;

public class IsDivisbleBy implements IntSequence {
	private int divisor;
	
	public IsDivisbleBy(int divisor) {
		this.divisor = Math.max(1, divisor);
	}
	
	@Override
	public int numPossibleStates() {
		return 2;
	}
	
	@Override
	public int evaluate(long num) {
		return num % divisor == 0 ? 1 : 0;
	}
	
	@Override
	public long nth(int n) {
		return n * divisor;
	}
	
	public int getDivisor() {
		return divisor;
	}
	
	public void setDivisor(int divisor) {
		this.divisor = divisor;
	}
}