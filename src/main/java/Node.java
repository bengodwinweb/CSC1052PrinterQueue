/*
Benjamin Godwin 2020
CSC1052 - Printer Queue Project
Node.java

Info: generic node for use in a linked collection
 */

public class Node<T> implements INode<T> {
    private T m_data;
    private INode<T> m_next;

    public Node(T data) {
        m_data = data;
    }

    @Override
    public T getData() {
        return m_data;
    }

    @Override
    public void setData(T data) {
        m_data = data;
    }

    @Override
    public INode<T> getNext() {
        return m_next;
    }

    @Override
    public void setNext(INode<T> next) {
        m_next = next;
    }
}
