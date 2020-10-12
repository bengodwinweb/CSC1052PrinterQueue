/*
Benjamin Godwin 2020
CSC1052 - PrinterQueue Project
LinkedQueue.java

Info: linked, threadsafe implementation of a Queue data structure
 */

import java.util.NoSuchElementException;

public class LinkedQueue<T> implements IQueue<T> {

    INode<T> front;
    INode<T> rear;
    private int m_numElements;

    public LinkedQueue() {
        m_numElements = 0;
    }

    @Override
    public synchronized void enqueue(T element) {
        Node<T> node = new Node<>(element);

        if (isEmpty()) {
            front = rear = node;
        } else {
            rear.setNext(node);
            rear = node;
        }

        m_numElements++;
    }

    @Override
    public synchronized T dequeue() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T data = front.getData();
        front = front.getNext();
        m_numElements--;
        return data;
    }

    @Override
    public synchronized boolean isEmpty() {
        return front == null;
    }

    @Override
    public synchronized int size() {
        return m_numElements;
    }
}
