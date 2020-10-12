/*
Benjamin Godwin 2020
CSC1052 - Printer Queue Project
INode.java

Info: public interface for a linked list Node.
 */

public interface INode<T> {

    /**
     * Returns the node's data object
     * @return
     */
    public T getData();

    /**
     * Sets the node's data object
     * @param data
     */
    public void setData(T data);

    /**
     * Returns a reference to the next node in the linked collection
     * @return
     */
    public INode<T> getNext();

    /**
     * Sets the reference to the next node in the linked collection
     * @param node
     */
    public void setNext(INode<T> node);
}
