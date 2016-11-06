package int_properties;

//TODO Eventually updgrade this class to an IntSequence so instead of the positive integers,
//the prime numbers could form the basis of a number line.
public class IsPrime implements IntSequence {
	public final static IsPrime instance = new IsPrime();
	
	private IsPrime() {}
	
	@Override
	public int numPossibleStates() {
		return 2;
	}
	
	@Override
	public int evaluate(long value) {
		if (value < 2) return 0;
		if (value == 2) return 1;
		if (value % 2 == 0) return 0;		
		long posFactor = 3;
		long end = value / 2;
		while (posFactor < end) {
			if (value % posFactor == 0) {
				return 0;
			}
			posFactor += 2;
		}
		return 1;
	}
	
	@Override
	public long nth(int value) {
		return -1;
	}
}