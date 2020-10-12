/*
Benjamin Godwin 2020
CSC1052 - Printer Queue Project
IQueue.java

Info: public interface for a Queue data structure
 */

import java.util.NoSuchElementException;

public interface IQueue<T> {

    /**
     * Adds an element to the queue
     * @param element to be added
     */
    void enqueue(T element);

    /**
     * Removes and returns the first element in the queue
     * @return first element in the queue
     * @throws NoSuchElementException if queue is empty
     */
    T dequeue() throws NoSuchElementException;

    /**
     * Returns true if the queue is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the collection
     */
    int size();
}
