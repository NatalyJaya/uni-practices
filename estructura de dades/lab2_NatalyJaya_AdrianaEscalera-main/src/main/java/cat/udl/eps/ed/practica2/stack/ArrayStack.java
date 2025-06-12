package cat.udl.eps.ed.practica2.stack;

import java.util.NoSuchElementException;

/**
 * An implementation of a stack using an extensible array
 *
 * @param <E> the type of elements in the stack
 * @see Stack
 */
public class ArrayStack<E> implements Stack<E> {
    public E[] stackArray;
    public int top;
    private static final int DEFAULT_SIZE = 10;

    /**
     * Creates an empty stack
     * @implNote
     * Uses a default size of 10 to create the array
     */

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        this.stackArray = (E[]) new Object[DEFAULT_SIZE];
        this.top = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize (){
        E[] auxStackArray = (E[]) new Object[this.stackArray.length * 2];
        for(int i = 0; i <stackArray.length; i++){
            auxStackArray[i] = stackArray[i];
        }
        stackArray = auxStackArray;
    }


    /**
     * Adds an element to the top of the stack.
     *
     * @implNote
     *  If there is no room in the stack, the size of the array is doubled
     *
     * @param elem the element to be added
     */

    @Override
    public void push(E elem) {
        if(this.top == stackArray.length) resize();
        this.stackArray[top] = elem;
        this.top++;
    }

    /**
     * Returns the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E top() {
        if(isEmpty()) throw new NoSuchElementException();
        return this.stackArray[top-1];
    }

    /**
     * Removes the element at the top of the stack.
     *
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public void pop() {
        if(isEmpty()) throw new NoSuchElementException();
        this.top--;
        this.stackArray[top] = null;
    }

    /**
     * Returns true if the stack is empty, false otherwise.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.top == 0;
    }
}
