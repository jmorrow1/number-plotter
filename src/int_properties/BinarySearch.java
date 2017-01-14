package int_properties;

public class BinarySearch {
    public static boolean contains(int[] seq, int value) {
        int min = 0;
        int max = seq.length - 1;
        while (min <= max) {
            int guess = (max + min) / 2;
            if (seq[guess] == value) {
                return true;
            } else if (seq[guess] < value) {
                min = guess + 1;
            } else {
                max = guess - 1;
            }
        }
        return false;
    }
}
