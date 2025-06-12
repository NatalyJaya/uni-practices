package cat.udl.eps.ed.mcss;

public class Mcss1 {

    private static int sumSubsegment(int[] values, int begin, int end) {
        int sum = 0;
        for (int i = begin; i < end; i++)
            sum += values[i];
        return sum;
    }

    public static int mcss(int[] values) {
        int maxSum = Integer.MIN_VALUE;
        for (int begin = 0; begin < values.length; begin++) {
            for (int end = begin + 1; end <= values.length; end++) {
                int thisSum = sumSubsegment(values, begin, end);
                if (thisSum > maxSum)
                    maxSum = thisSum;
            }
        }
        return maxSum;
    }
}
