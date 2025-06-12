package cat.udl.eps.ed.practica2.stack;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {

    @Test
    void pop_on_empty_stack_should_throw_nse_exception() {
        var emptyStack = new ArrayStack<Integer>();
        assertThrows(NoSuchElementException.class, () -> {
            emptyStack.pop();
        });
    }

    @Test
    @DisplayName("push adds elements to the stack")
    void push_adds_element_to_stack() {
        var arrayStack = new ArrayStack<Integer>();
        arrayStack.push(10);
        assertEquals(10, arrayStack.top());
    }


    @Test
    @DisplayName("pop removes the top element from the stack")
    void pop_removes_top_element() {
        var stack = new ArrayStack<Integer>();
        stack.push(5);
        stack.push(10);
        stack.pop();
        assertEquals(5, stack.top());
    }


    @Test
    @DisplayName("pop on empty stack throws NoSuchElementException")
    void pop_on_empty_stack_throws_exception() {
        var stack = new ArrayStack<Integer>();
        assertThrows(NoSuchElementException.class, stack::pop);
    }


    @Test
    @DisplayName("top returns the top element without removing it")
    void top_returns_top_element() {
        var stack = new ArrayStack<Integer>();
        stack.push(20);
        stack.push(30);
        assertEquals(30, stack.top());
        assertEquals(30, stack.top());
    }

    @Test
    @DisplayName("top on empty stack throws NoSuchElementException")
    void top_on_empty_stack_throws_exception() {
        var stack = new ArrayStack<Integer>();
        assertThrows(NoSuchElementException.class, stack::top);
    }

    @Test
    @DisplayName("resize doubles the capacity of the stack when full")
    void resize_doubles_capacity() {
        var stack = new ArrayStack<Integer>();
        for (int i = 0; i < 15; i++) {
            stack.push(i);
        }
        assertEquals(15, stack.top() + 1);
    }

    @Test
    @DisplayName("isEmpty returns true if the stack is empty, false otherwise")
    void isEmpty_checks_if_stack_is_empty() {
        var stack = new ArrayStack<Integer>();
        assertTrue(stack.isEmpty());

        stack.push(1);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("push multiple elements and should be in order")
    void push_elements() {
        var arrayStack = new ArrayStack<Integer>();
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        assertEquals(3, arrayStack.top());
        arrayStack.pop();
        assertEquals(2, arrayStack.top());
        arrayStack.pop();
        assertEquals(1, arrayStack.top());
    }

    @Test
    @DisplayName("Sequence of push and pop")
    void push_pop_elements() {
        var arrayStack = new ArrayStack<Integer>();
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.pop();
        arrayStack.push(3);
        assertEquals(3, arrayStack.top());
        arrayStack.pop();
        assertEquals(1, arrayStack.top());
    }

    @Test
    @DisplayName("multiple Push Pop cycle")
    void check_push_pop() {
        var arrayStack = new ArrayStack<Integer>();
        for (int i = 0; i < 5; i++) {
            arrayStack.push(i);
            arrayStack.push(i * 10);
            assertEquals(i * 10, arrayStack.top());
            arrayStack.pop();
            assertEquals(i, arrayStack.top());
            arrayStack.pop();
            assertTrue(arrayStack.isEmpty());
        }
    }

}




