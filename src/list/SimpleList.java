package list;

/**
 * An interface for simple lists that includes an Iterator. All of these
 * operations except <code>remove</code> should run in O(1) time. Make *NO*!
 * changes to this file!
 */

public interface SimpleList<AnyType> extends Iterable<AnyType> {
	/**
	 * Determines whether the list is empty.
	 * 
	 * @return <code>true</code> if the list is empty; <code>false</code> otherwise
	 */
	boolean isEmpty();

	/**
	 * Inserts an element at the end of the list.
	 * 
	 * @param value the element to be inserted into the list
	 * @return <code>true</code> if the element was added; <code>false</code>
	 *         otherwise
	 */
	boolean add(AnyType value);

	/**
	 * Removes the first element of the list that equals value.
	 * 
	 * @param value the element to remove
	 * @return <code>true</code> if the element was removed; <code>false</code>
	 *         otherwise
	 */
	boolean remove(AnyType value);

}
