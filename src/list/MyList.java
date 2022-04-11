package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic linked list class that you will write so that it implements the
 * SimpleList and SimpleQueue interfaces.
 * 
 * NAME: Ahmad Hanif Andrew ID: ahanif Hours worked: 15 Hours
 */

public class MyList<DataType> implements SimpleList<DataType>, SimpleQueue<DataType> {
	private ListNode head;
	private ListNode last;

	private int size;

	/**
	 * private inner class to provide the nodes of the Linked List
	 */
	private class ListNode {
		private DataType data;
		private ListNode next;

		private ListNode(DataType data) {
			this.data = data;
			this.next = null;
		}

		public String toString() {
			return data.toString();
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("List Contents: ");
		for (ListNode tmpNode = head; tmpNode != null; tmpNode = tmpNode.next) {
			sb.append(tmpNode);
			sb.append(" --> ");
		}
		sb.append("<null>");
		return sb.toString();
	}

	// Write These Functions

	public class MyListIterator implements Iterator<DataType> {
		private ListNode cur = head;

		@Override
		public boolean hasNext() {
			return (cur != null);
		}

		@Override
		public DataType next() {
			if (hasNext()) {
				DataType tmp = cur.data;
				cur = cur.next;
				return tmp;
			} else {
				throw new NoSuchElementException();
			}
		}

	}

	@Override
	public Iterator<DataType> iterator() {
		return new MyListIterator();
	}

	@Override
	public void enqueue(DataType value) {

		if (isEmpty()) {
			ListNode newNode = new ListNode(value);
			newNode.next = head;
			head = newNode;
			last = head;
			size++;
		} else if (!isEmpty()) {
			ListNode tmpNode = new ListNode(value);
			last.next = tmpNode;
			last = last.next;
			size++;
		}
	}

	@Override
	public DataType dequeue() {
		if (!isEmpty()) {
			DataType tmp = head.data;
			head = head.next;
			size--;
			return tmp;
		} else {
			return null;
		}
	}

	@Override
	public DataType peek() {
		if (!isEmpty()) {
			DataType tmp = head.data;
			return tmp;
		} else {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean add(DataType value) {
		int oldSize = size;
		this.enqueue(value);
		if (size == oldSize + 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean remove(DataType value) {
		if (isEmpty()) {
			return false;
		} else if (head.data.equals(value)) {
			head.data = null;
			head = head.next;
			size--;
			return true;
		}
		for (ListNode tmp = head; tmp.next != null; tmp = tmp.next) {
			if (tmp.next.data.equals(value)) {
				tmp.next = tmp.next.next;
				if (tmp.next == null) {
					last = tmp;
				}
				size--;
				return true;
			}
		}
		return false;
	}

	/*
	 * This getter is needed for the autograder, don't change or remove it.
	 */
	public ListNode getHead() {
		return head;
	}

	public static void main(String[] args) {
		// Your testcases here.
		MyList<String> theList = new MyList<String>();

		// Test cases for enqueue(value)...
		System.out.print("Testing enqueue(value)... ");
		theList.enqueue("A");
		if (theList.isEmpty() == true) {
			System.out.println("failed");
			return;
		}
		if (theList.size != 1) {
			System.out.println("failed");
			return;
		}
		if (!theList.toString().equals("List Contents: A --> <null>")) {
			System.out.println("failed");
			System.out.println(theList.toString());
			return;
		}
		System.out.println("passed");

		System.out.print("Testing enqueue(value)... ");
		theList.enqueue("B");
		if (theList.isEmpty() == true) {
			System.out.println("failed");
			return;
		}
		if (theList.size != 2) {
			System.out.println("failed");
			return;
		}
		if (!theList.toString().equals("List Contents: A --> B --> <null>")) {
			System.out.println("failed");
			System.out.println(theList.toString());
			return;
		}
		System.out.println("passed");

		System.out.print("Testing enqueue(value)... ");
		theList.enqueue("C");
		if (theList.isEmpty() == true) {
			System.out.println("failed");
			return;
		}
		if (theList.size != 3) {
			System.out.println("failed");
			return;
		}
		if (!theList.toString().equals("List Contents: A --> B --> C --> <null>")) {
			System.out.println("failed");
			System.out.println(theList.toString());
			return;
		}
		System.out.println("passed");

		// Test cases for dequeue()...
		System.out.print("Testing dequeue()... ");
		theList.dequeue();
		if (theList.isEmpty() == true) {
			System.out.println("failed");
			return;
		}
		if (theList.size != 2) {
			System.out.println("failed");
			return;
		}
		if (!theList.toString().equals("List Contents: B --> C --> <null>")) {
			System.out.println("failed");
			System.out.println(theList.toString());
			return;
		}
		System.out.println("passed");

		System.out.print("Testing dequeue()... ");
		theList.dequeue();
		if (theList.isEmpty() == true) {
			System.out.println("failed");
			return;
		}
		if (theList.size != 1) {
			System.out.println("failed");
			return;
		}
		if (!theList.toString().equals("List Contents: C --> <null>")) {
			System.out.println("failed");
			System.out.println(theList.toString());
			return;
		}
		System.out.println("passed");

		System.out.print("Testing dequeue()... ");
		theList.dequeue();
		theList.dequeue();
		theList.dequeue();
		if (theList.dequeue() != null) {
			System.out.println("failed");
			return;
		}
		System.out.println("passed");

		// Test cases for Peek()
		System.out.print("Testing peek()... ");
		if (theList.peek() == null) {
			System.out.println("passed");
		} else {
			System.out.println("failed");
			return;
		}

		theList.enqueue("A");
		System.out.print("Testing peek()... ");
		if (theList.peek() == "A") {
			System.out.println("passed");
		} else {
			System.out.println("failed");
			return;
		}

		theList.enqueue("A");
		theList.enqueue("B");
		System.out.print("Testing peek()... ");
		if (theList.peek() == "A") {
			System.out.println("passed");
		} else {
			System.out.println("failed");
			return;
		}

		theList.remove("A");
		theList.remove("B");

		// Test cases for isEmpty()
		System.out.print("Testing isEmpty()... ");
		if (theList.isEmpty() != false) {
			System.out.println("failed");
			return;
		}
		System.out.println("passed");

		theList.dequeue();
		System.out.print("Testing isEmpty()... ");
		if (theList.isEmpty() != true) {
			System.out.println("failed");
			return;
		}
		System.out.println("passed");

		theList.add("A");
		System.out.print("Testing isEmpty()... ");
		if (theList.isEmpty() != false) {
			System.out.println("failed");
			return;
		}
		System.out.println("passed");
		theList.dequeue();

		// Test cases for add
		System.out.print("Testing add(value)... ");
		if (theList.add("A") != true) {
			System.out.println("failed");
			return;
		}
		if (theList.isEmpty() == true) {
			System.out.println("failed");
			return;
		}
		if (theList.size != 1) {
			System.out.println("failed");
			return;
		}
		if (theList.peek() != "A") {
			System.out.println("failed");
			return;
		}
		if (!theList.toString().equals("List Contents: A --> <null>")) {
			System.out.println("failed");
			System.out.println(theList.toString());
			return;
		}
		System.out.println("passed");

		System.out.print("Testing add(value)... ");
		if (theList.add("B") != true) {
			System.out.println("failed");
			return;
		}
		if (theList.isEmpty() == true) {
			System.out.println("failed");
			return;
		}
		if (theList.size != 2) {
			System.out.println("failed");
			return;
		}
		if (!theList.toString().equals("List Contents: A --> B --> <null>")) {
			System.out.println("failed");
			System.out.println(theList.toString());
			return;
		}
		System.out.println("passed");

		System.out.print("Testing add(value)... ");
		if (theList.add("B") != true) {
			System.out.println("failed");
			return;
		}
		if (theList.isEmpty() == true) {
			System.out.println("failed");
			return;
		}
		if (theList.size != 3) {
			System.out.println("failed");
			return;
		}
		if (!theList.toString().equals("List Contents: A --> B --> B --> <null>")) {
			System.out.println("failed");
			System.out.println(theList.toString());
			return;
		}
		System.out.println("passed");

		// Test cases for remove(value)
		System.out.print("Testing remove(value)... ");
		theList.remove("B");
		if (theList.remove("B") != true) {
			System.out.println("failed");
			return;
		}
		if (theList.isEmpty() == true) {
			System.out.println("failed");
			return;
		}
		if (theList.size != 1) {
			System.out.println("failed");
			return;
		}
		if (!theList.toString().equals("List Contents: A --> <null>")) {
			System.out.println("failed");
			System.out.println(theList.toString());
			return;
		}
		System.out.println("passed");

		System.out.print("Testing remove(value)... ");
		if (theList.remove("C") != false) {
			System.out.println("failed");
			return;
		}
		if (theList.isEmpty() == true) {
			System.out.println("failed");
			return;
		}
		if (theList.size != 1) {
			System.out.println("failed");
			return;
		}
		if (!theList.toString().equals("List Contents: A --> <null>")) {
			System.out.println("failed");
			System.out.println(theList.toString());
			return;
		}
		System.out.println("passed");

		theList.add("A");
		theList.add("B");
		theList.add("C");
		System.out.print("Testing remove(value)... ");
		if (theList.remove("A") != true) {
			System.out.println("failed");
			System.out.println(theList.toString());
			return;
		}
		if (theList.isEmpty() != false) {
			System.out.println("failed");
			System.out.println(theList.toString());
			return;
		}
		if (theList.size != 3) {
			System.out.println("failed");
			System.out.println(theList.toString());
			return;
		}
		if (!theList.toString().equals("List Contents: A --> B --> C --> <null>")) {
			System.out.println("failed");
			System.out.println(theList.toString());
			return;
		}
		System.out.println("passed");

		System.out.print("Testing remove(value)... ");
		theList.remove("C");
		theList.add("C");
		theList.add("D");
		theList.add("E");

		if (theList.isEmpty() != false) {
			System.out.println("failed");
			System.out.println(theList.toString());
			return;
		}
		if (theList.size != 5) {
			System.out.println("failed");
			System.out.println(theList.toString());
			return;
		}
		if (!theList.toString().equals("List Contents: A --> B --> C --> D --> E --> <null>")) {
			System.out.println("failed");
			System.out.println(theList.toString());
			return;
		}
		System.out.println("passed");

		// Test cases for Iterator
		System.out.print("Testing Iterator... ");
		MyList<String> itrList = new MyList<String>();
		Iterator<String> listIterator1 = theList.iterator();
		while (listIterator1.hasNext()) {
			String s = listIterator1.next();
			itrList.add(s);
		}
		if (itrList.toString().equals(theList.toString())) {
			System.out.println("passed");
		} else {
			System.out.println("failed");
			return;
		}

		System.out.print("Testing Iterator... ");
		MyList<String> emptyList = new MyList<String>();
		MyList<String> itrList2 = new MyList<String>();
		Iterator<String> listIterator2 = emptyList.iterator();
		while (listIterator2.hasNext()) {
			String s = listIterator2.next();
			itrList2.add(s);
		}
		if (itrList2.toString().equals(emptyList.toString())) {
			System.out.println("passed");
		} else {
			System.out.println("failed");
			return;
		}

		System.out.print("Testing Iterator... ");
		MyList<String> theList2 = new MyList<String>();
		theList2.add("A");
		theList2.add("B");
		theList2.add("C");
		theList2.add("D");
		theList2.remove("D");
		MyList<String> itrList3 = new MyList<String>();
		Iterator<String> listIterator3 = theList2.iterator();
		while (listIterator3.hasNext()) {
			String s = listIterator3.next();
			itrList3.add(s);
		}
		if (itrList2.toString().equals(emptyList.toString())) {
			System.out.println("passed");
		} else {
			System.out.println("failed");
			return;
		}
	}
}
