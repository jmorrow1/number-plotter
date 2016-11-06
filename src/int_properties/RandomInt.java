package int_properties;

import java.util.Arrays;
import java.util.Random;

public class RandomInt implements IntSequence {
	private float[] weights;
    private Random rng;
    
    public RandomInt(int numStates) {
    	this(numStates, new Random());
    }
    
    public RandomInt(int numStates, long seed) {
    	this(numStates, new Random(seed));
    }
    
    private RandomInt(int numStates, Random rng) {
    	this.weights = new float[numStates];
    	for (int i=0; i<weights.length; i++) {
    		weights[i] = 1;
    	}
    	normalizeAndAccumulate(weights);
    	this.rng = rng;
    }
    
    public RandomInt(float[] weights) {
    	this(weights, new Random());
    }
    
    public RandomInt(float[] weights, long seed) {
    	this(weights, new Random(seed));
    }
    
    private RandomInt(float[] weights, Random rng) {
    	 this.weights = weights;
         this.rng = rng;
         normalizeAndAccumulate(weights);
    }
    
    @Override
    public long nth(int n) {
    	return (int)(rng.nextFloat()*weights.length);
    }
    
    @Override
    public int numPossibleStates() {
    	return weights.length;
    }
    
    @Override
    public int evaluate(long num) {
        float r = rng.nextFloat();
        
        for (int i=0; i<weights.length; i++) {
        	if (r < weights[i]) {
        		return 1;
        	}
        	i++;
        }
        
        return 0;
    }
    
    private static void normalizeAndAccumulate(float[] xs) {
    	//sum
    	float sum = 0;
    	for (int i=0; i<xs.length; i++) {
    		sum += xs[i];
    	}
    	
    	//normalize
    	for (int i=0; i<xs.length; i++) {
    		xs[i] /= sum;
    	}
    	
    	//accumulate
    	for (int i=1; i<xs.length; i++) {
    		xs[i] = xs[i] + xs[i-1];
    	}
    }
}