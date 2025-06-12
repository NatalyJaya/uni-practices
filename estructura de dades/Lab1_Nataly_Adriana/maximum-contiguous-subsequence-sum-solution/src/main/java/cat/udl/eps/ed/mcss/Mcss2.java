package cat.udl.eps.ed.mcss;

public class Mcss2 {

    // Kadane's alñorithm
    public static int mcss(int[] values) {
        int maxSum = Integer.MIN_VALUE;
        int sumEndingHere = 0;
        for (int value : values) {
            sumEndingHere = Math.max(value, sumEndingHere + value);
            maxSum = Math.max(maxSum, sumEndingHere);
        }
        return maxSum;
    }
}
