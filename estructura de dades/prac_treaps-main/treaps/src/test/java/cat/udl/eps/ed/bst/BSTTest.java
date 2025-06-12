package cat.udl.eps.ed.bst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BSTTest extends MapTest {

    @BeforeEach
    void createMap() {
        map = new BST<>();
    }

    @RepeatedTest(value = 5, failureThreshold = 2)
    @DisplayName("The heigh after randomly inserting n elements is less than 3 * log n")
    void testHeigh() {
        var log = 10;
        var evens = shuffledEvensFrom2(1 << log);
        for (var i: evens) {
            map.put(i, i);
        }
        var bst = (BST<Integer, Integer>) map;
        assertTrue(bst.height() < log * 3);
    }
    @Test
    @DisplayName("Test remove operation in BST")
    void testRemove() {
    BST<Integer, Integer> bst = new BST<>();
    bst.put(1, 100);
    bst.put(2, 200);
    bst.put(3, 300);
    
    // Verifies that the elements exist before removal
    assertTrue(bst.containsKey(1));
    assertTrue(bst.containsKey(2));
    
    // Removes the elements
    bst.remove(1);
    bst.remove(2);
    
    // Verifies that the elements were removed
    assertFalse(bst.containsKey(1));
    assertFalse(bst.containsKey(2));
    assertTrue(bst.containsKey(3));
}
@Test
@DisplayName("Test containsKey operation")
void testContainsKey() {
    BST<Integer, Integer> bst = new BST<>();
    bst.put(1, 100);
    bst.put(2, 200);
    bst.put(3, 300);
    
    // Verifies that the inserted keys are present
    assertTrue(bst.containsKey(1));
    assertTrue(bst.containsKey(2));
    assertTrue(bst.containsKey(3));
    
    // Verifies that a non-inserted key is not present
    assertFalse(bst.containsKey(4));
}

    // Test for only BSTs go here
   // @RepeatedTest(value = 5, failureThreshold = 2)
   // @DisplayName("The height after sequentially inserting n elements is less than 2 * log n")
   // void testSequentialInsertionsHeight() {
   // var log = 10;
   // var n = 1 << log;  // Calculates 2^log, i.e., 1024
   // for (int i = 1; i <= n; i++) {
   //     map.put(i, i);  // Inserts elements sequentially
   // }
   // var bst = (BST<Integer, Integer>) map;
   // // Verifies that the height is less than 2 times log(n)
   // assertTrue(bst.height() < 2 * log , "The tree height should be less than 2 * log(n)");
//}

    @Test
    public void testPutAndGet() {
        var bst = new BST<Integer, Integer>();
       // Insert a key-value pair
       bst.put(1, 100); 
       
       // Verify if we can get the value associated with the key 
       assertEquals(100, bst.get(1));
       
       // Verify that a non-inserted key does not exist
        assertNull(bst.get(2));
    }




}
