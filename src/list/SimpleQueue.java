package list;

/**
 * An interface for queues that includes an Iterator. All of these operations
 * should run in O(1) time. Make *NO*! changes to this file!
 */

public interface SimpleQueue<AnyType> extends Iterable<AnyType> {
    /**
     * Determines if the queue has no elements
     * 
     * @return <code>true</code> if the queue is empty; <code>false</code> otherwise
     */
    boolean isEmpty();

    /**
     * Inserts an element at the end of the queue
     * 
     * @param value the element to be inserted into the queue
     */
    void enqueue(AnyType value);

    /**
     * Retrieves and removes the first element of the queue
     * 
     * @return the element at the front of the original queue, or <code>null</code>
     *         if the queue is empty
     */
    AnyType dequeue();

    /**
	 * Retrieves, but does not remove, the first element of the queue
	 * 
	 * @return the element at the front of the original queue, or <code>null</code>
	 *         if the queue is empty
	 */
    AnyType peek();

}
