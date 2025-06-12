package cat.udl.eps.ed.bst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreapTest extends MapTest {

    @BeforeEach
    void createMap() {
        map = new Treap<>();
    }

    @Test
    @DisplayName("Insert elements and verify the map contains them")
    void testInsertAndContains() {
        Treap<Integer, Integer> treap = new Treap<>();
        treap.put(10, 10);
        treap.put(5, 5);
        treap.put(15, 15);

        assertTrue(treap.containsKey(10), "Treap should contain the key 10.");
        assertTrue(treap.containsKey(5), "Treap should contain the key 5.");
        assertTrue(treap.containsKey(15), "Treap should contain the key 15.");
        assertFalse(treap.containsKey(20), "Treap should not contain the key 20.");
    }

    @Test
    @DisplayName("Remove elements and verify they are no longer in the map")
    void testRemove() {
        Treap<Integer, Integer> treap = new Treap<>();
        treap.put(10, 10);
        treap.put(5, 5);
        treap.put(15, 15);

        treap.remove(10);
        assertFalse(treap.containsKey(10), "Treap should not contain the key 10 after removal.");
        assertTrue(treap.containsKey(5), "Treap should still contain the key 5.");
        assertTrue(treap.containsKey(15), "Treap should still contain the key 15.");
    }

    @Test
    @DisplayName("Height after inserting n elements is less than 3 * log(n)")
    void testHeight() {
        Treap<Integer, Integer> treap = new Treap<>();
        int log = 10; // Insert 2^log elements
        for (int i = 0; i < (1 << log); i++) {
            treap.put(i, i);
        }

        assertTrue(treap.height() < log * 3, "Height should be less than 3 * log(n)");
    }

    @Test
    @DisplayName("Height of empty Treap is zero")
    void testEmptyTreapHeight() {
        Treap<Integer, Integer> treap = new Treap<>();
        assertEquals(0, treap.height(), "The height of an empty Treap should be 0.");
    }

    @Test
    @DisplayName("Reinserting a key updates its value")
    void testReinsertUpdatesValue() {
        Treap<Integer, Integer> treap = new Treap<>();
        treap.put(10, 10);
        treap.put(10, 20);

        assertEquals(20, treap.get(10), "Reinserting a key should update its value.");
    }
}
