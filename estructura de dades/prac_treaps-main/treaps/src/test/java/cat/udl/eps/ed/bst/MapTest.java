package cat.udl.eps.ed.bst;

//import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class MapTest {

    Map<Integer, Integer> map;

    static List<Integer> shuffledEvensFrom2(int n) {
        var list = new ArrayList<Integer>(n);
        for (int i = 1; i <= n; i++) {
            list.add(i * 2);
        }
        Collections.shuffle(list);
        return list;
    }

    @Test
    @DisplayName("isEmpty / put / containsKey / get are coherent")
    void testPutAndGet() {
        var evens = shuffledEvensFrom2(1000);
        assertTrue(map.isEmpty());
        for (var i: evens) {
            map.put(i, i);
        }
        assertFalse(map.isEmpty());
        // We have inserted 2, 4, 6, ..., 2000
        for (var i: evens) {
            // we can find all of them in the map
            assertTrue(map.containsKey(i));
            // we get the value we put
            assertEquals(i, map.get(i));
        }
        for (int i = 1; i <= 2001; i += 2) {
            // note that we test numbers not in the map considering
            // numbers lower than the minimum (2), between the min and the max,
            // and higher than the maximum (2000)
            // the map does not contain the odd numbers
            assertFalse(map.containsKey(i));
            // we cannot find them
            assertNull(map.get(i));
        }
    }

<<<<<<< HEAD
    @Test
    @DisplayName("get() should be coherent with put()")
    void testGet() {
        map.put(10, 10);
        map.put(20, 20);
        map.put(5, 5);

        assertEquals(10, map.get(10));
        assertEquals(20, map.get(20));
        assertEquals(5, map.get(5));
        map.put(10,500);
        assertEquals(500,map.get(10));
    }
    @Test
    @DisplayName("Insert and retrieve values with duplicates")
    void testPutWithDuplicates() {
        for (int i = 1; i <= 100; i++) {
            map.put(i, i);
        }
        for (int i = 1; i <= 100; i++) {
            map.put(i, i * 10); // Overwrite values
        }
        for (int i = 1; i <= 100; i++) {
            assertEquals(i * 10, map.get(i), "Value should be updated to the latest inserted value");
        }
    }

    @Test
    @DisplayName("Remove existing and non-existing keys")
    void testRemoveKeys() {
        for (int i = 1; i <= 50; i++) {
            map.put(i, i);
        }
        for (int i = 1; i <= 50; i += 2) {
            map.remove(i); // Remove odd keys
        }
        for (int i = 1; i <= 50; i++) {
            if (i % 2 == 0) {
                assertTrue(map.containsKey(i), "Even keys should still exist in the map");
            } else {
                assertFalse(map.containsKey(i), "Odd keys should be removed from the map");
            }
        }
    }

    @Test
    @DisplayName("Edge cases with minimum and maximum keys")
    void testMinMaxKeys() {
        map.put(Integer.MIN_VALUE, -1);
        map.put(Integer.MAX_VALUE, 1);

        assertTrue(map.containsKey(Integer.MIN_VALUE), "Map should contain the minimum integer key");
        assertEquals(-1, map.get(Integer.MIN_VALUE), "Value for minimum key should be correct");

        assertTrue(map.containsKey(Integer.MAX_VALUE), "Map should contain the maximum integer key");
        assertEquals(1, map.get(Integer.MAX_VALUE), "Value for maximum key should be correct");

        map.remove(Integer.MIN_VALUE);
        assertFalse(map.containsKey(Integer.MIN_VALUE), "Minimum key should be removed from the map");
    }



    @Test
    @DisplayName("Map maintains consistency after multiple operations")
    void testMixedOperations() {
        for (int i = 1; i <= 100; i++) {
            map.put(i, i * 2);
        }
        for (int i = 1; i <= 50; i++) {
            map.remove(i);
        }
        for (int i = 1; i <= 100; i++) {
            if (i <= 50) {
                assertFalse(map.containsKey(i), "Keys removed should no longer be present");
            } else {
                assertTrue(map.containsKey(i), "Keys not removed should still be present");
                assertEquals(i * 2, map.get(i), "Values for existing keys should be correct");
            }
        }
    }

    @Test
    @DisplayName("Handle null keys gracefully")
    void testNullKeys() {
        assertThrows(NullPointerException.class, () -> map.put(null, 1), "Inserting a null key should throw NullPointerException");
        assertThrows(NullPointerException.class, () -> map.get(null), "Retrieving a null key should throw NullPointerException");
        assertThrows(NullPointerException.class, () -> map.remove(null), "Removing a null key should throw NullPointerException");
    }

    @Test
    @DisplayName("Map behaves correctly with negative keys")
    void testNegativeKeys() {
        for (int i = -100; i <= -1; i++) {
            map.put(i, i * 3);
        }
        for (int i = -100; i <= -1; i++) {
            assertTrue(map.containsKey(i), "Negative keys should be correctly stored in the map");
            assertEquals(i * 3, map.get(i), "Values for negative keys should be correct");
        }
        for (int i = 1; i <= 100; i++) {
            assertFalse(map.containsKey(i), "Positive keys not inserted should not exist in the map");
        }
    }

=======
    // Tests for both implementations go here
    @Test
    void testPutAndGet2() {
        Map<Integer, String> bst = new BST<>();
        bst.put(1, "One");
        bst.put(2, "Two");
        bst.put(3, "Three");

        assertEquals("One", bst.get(1));
        assertEquals("Two", bst.get(2));
        assertEquals("Three", bst.get(3));
        assertNull(bst.get(4)); // Key not present
    }
>>>>>>> b0aeb0f3cb691fab8497668b01b1e6a9031e756d
}