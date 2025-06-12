package cat.udl.eps.ed.practica1.sorting;

import cat.udl.eps.ed.practica1.sorting.IntArraySorter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author NatalyJaya
 * @author AdrianaEscalera
 */
public class NegativeSortTest {

    private IntArraySorter sorting;

    @Test
    public void empty() {
        int[] array = new int[0];
        sorting = new IntArraySorter(array);
        assertTrue(sorting.isSorted());
    }

    @Test
    public void singleton() {
        int[] array = new int[] { -7 };
        sorting = new IntArraySorter(array);
        assertTrue(sorting.isSorted());
    }

    @Test
    public void sortedPair() {
        int[] array = new int[] { -42, -21 };
        sorting = new IntArraySorter(array);
        assertTrue(sorting.isSorted());
    }

    @Test
    public void unsortedPair() {
        int[] array = new int[] { -21, -42 };
        sorting = new IntArraySorter(array);
        assertFalse(sorting.isSorted());
    }

    @Test
    public void sortedNegatives() {
        int[] array = new int[] { -8, -5, -3, -1 };
        sorting = new IntArraySorter(array);
        assertTrue(sorting.isSorted());
    }

    @Test
    public void reverseSortedNegatives() {
        int[] array = new int[] { -1, -3, -5, -8 };
        sorting = new IntArraySorter(array);
        assertFalse(sorting.isSorted());
    }

    @Test
    public void unsortedNegatives() {
        int[] array = new int[] { -12, -7, -21, -34, -3, -8, -21, -17 };
        sorting = new IntArraySorter(array);
        assertFalse(sorting.isSorted());
    }

    @Test
    public void mixedNumbers() {
        int[] array = new int[] { -1, -3, 0, 2, -5, 4, -2 };
        sorting = new IntArraySorter(array);
        assertFalse(sorting.isSorted());
    }

    @Test
    public void allSameElements() {
        int[] array = new int[] { -1, -1, -1, -1, -1 };
        sorting = new IntArraySorter(array);
        assertTrue(sorting.isSorted());
    }

    @Test
    public void maximumAndMinimum() {
        int[] array = new int[] { Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE };
        sorting = new IntArraySorter(array);
        sorting.isSorted();
        assertTrue(sorting.isSorted());
    }

    @Test
    public void reverseSorted() {
        int[] array = new int[] { 10, 5, 1, -1, -5, -10 };
        sorting = new IntArraySorter(array);
        assertFalse(sorting.isSorted());
    }


}
