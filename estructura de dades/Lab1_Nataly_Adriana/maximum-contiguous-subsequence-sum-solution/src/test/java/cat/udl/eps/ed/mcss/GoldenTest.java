package cat.udl.eps.ed.mcss;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoldenTest {

    private static final int ITERATIONS = 100;
    private static final int SIZE = 100;

    private static final Random rg = new Random();

    static int[] randomArray() {
        int[] array = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            array[i] = 5000 - rg.nextInt(10000);
        }
        return array;
    }

    @Test
    void sameResult() {
        for (int i = 1; i <= ITERATIONS; i++) {
            int[] array = randomArray();
            int mcs1 = Mcss1.mcss(array);
            int mcs2 = Mcss2.mcss(array);
            assertEquals(mcs1, mcs2);
        }
    }
}
