package cat.udl.eps.ed.mcss;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Mcss1Test {

    @Test
    void mixedIntegers() {
       var example = new int[] { -2, 11, -4, 13, -5, -2};
        assertEquals(20, Mcss1.mcss(example));
    }

    @Test
    void allPositives() {
       var example = new int[] { 2, 11, 4, 13, 5, 2};
        assertEquals(37, Mcss1.mcss(example));
    }

    @Test
    void allNegatives() {
       var example = new int[] { -2, -11, -4, -13, -5, -2};
        assertEquals(-2, Mcss1.mcss(example));
    }

    @Test
    void emptyArray() {
        var empty = new int[] {};
        assertEquals(Integer.MIN_VALUE, Mcss1.mcss(empty));
    }
}
