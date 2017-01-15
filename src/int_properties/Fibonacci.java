package int_properties;

import java.math.BigInteger;

public class Fibonacci implements IntSequence {
    private int n;
    private long a, b;

    public Fibonacci() {
        this.n = 1;
        this.a = 0;
        this.b = 1;
    }

    @Override
    public int evaluate(long x) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int numPossibleStates() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long nth(int m) {
        if (m < 1) {
            return -1;
        } else if (n < m) {
            long c = 0;
            while (n < m) {
                c = a + b;
                n++;
                a = b;
                b = c;

            }
            return c;
        } else {
            n = 0;
            a = 0;
            b = 1;
            return nth(m);
        }

    }

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        for (int i = 0; i < 100; i++) {
            System.out.println(fib.nth(i));
        }

    }
}
