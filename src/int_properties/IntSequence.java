package int_properties;

import java.util.function.IntPredicate;

public interface IntSequence extends IntProperty {
	public long nth(int n);
}
