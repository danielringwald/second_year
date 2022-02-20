package queue_singlelinkedlist;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**
	 * Inserts the specified element into this queue, if possible post: The
	 * specified element is added to the rear of this queue
	 * 
	 * @param e the element to insert
	 * @return true if it was possible to add the element to this queue, else false
	 */
	public boolean offer(E e) {
		if (e == null) {
			return false;
		}

		QueueNode<E> newElement = new QueueNode<>(e);

		if (size != 0) {
			QueueNode<E> temp = last.next;
			last.next = newElement;
			last = newElement;
			last.next = temp;
			size++;
			return true;
		}

		last = newElement;
		last.next = last;
		size++;
		return true;

	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		if (size != 0) {
			return last.next.element;
		}
		return null;
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is empty.
	 * post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		if (size != 0) {
			E element = last.next.element;

			last.next = last.next.next;
			size--;
			return element;
		}
		return null;
	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<E> {
		QueueNode<E> pos;

		private QueueIterator() {
			pos = last;
		}

		public boolean hasNext() {
			return pos != null;
		}

		public E next() {

			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			pos = pos.next;
			E e = pos.element;

			if (pos == last) {
				pos = null;
			}

			return e;

		}

	}

	/**
	 * Appends the specified queue to this queue post: all elements from the
	 * specified queue are appended to this queue. The specified queue (q) is empty
	 * after the call.
	 * 
	 * @param q the queue to append
	 * @throws IllegalArgumentException if this queue and q are identical
	 */
	public void append(FifoQueue<E> q) {
		if (this == q) {
			throw new IllegalArgumentException();
		}

		
		
		if (q.size() != 0 && size != 0) {
			
			QueueNode<E> temp = last.next;
			last.next = q.last.next;
			q.last.next = temp;
			last = q.last;
			
		}

		if (size == 0 && q.size() != 0) {
			last = q.last;
		}
		
		this.size = this.size + q.size();
		q.last = null;
		q.size = 0;
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
