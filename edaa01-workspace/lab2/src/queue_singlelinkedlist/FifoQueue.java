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
	* Appends the specified queue to this queue
	* post: all elements from the specified queue are appended
	* to this queue. The specified queue (q) is empty after the call.
	* @param q the queue to append
	* @throws IllegalArgumentException if this queue and q are identical
	*/
	public void append(FifoQueue<E> q) {
		if(this == q) {
			throw new IllegalArgumentException("Queue can't append itself");
		}
		if (q.last == null) {
            return;
        }
        QueueNode<E> first;
        QueueNode<E> qFirst = q.last.next;
        QueueNode<E> qLast = q.last;

        if (last == null) {
            last = new QueueNode<E>(null);
            first = qFirst;
        }
        else {
            first = last.next;
        }

        size += (q.size());
        q.last.next = null;
        q.last = null;
        // Connects the Queues
        last.next = qFirst;
        last = qLast;
        last.next = first;
        q.size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		if(last == null) {
			last = new QueueNode<E>(e);
			last.next = last;
			size++;
			return true;
		}
		else{ 
			QueueNode<E> temp = last.next;
			last.next = new QueueNode<E>(e);
			last = last.next;
			last.next = temp;
			size++;
			return true;
		}
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(last == null) {
			return null;
		}
		return last.next.element;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if(last == null) {
			return null;
		}
		if(last == last.next) {
			E p = last.next.element;
			last.next = null;
			last = null;
			size --;
			return p;
		}
		E p = last.next.element;
		last.next = last.next.next;
		size--;
		return p;
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
	
	private QueueIterator() {
		pos = null;
	}
	
	public boolean hasNext(){
		if(pos == last) {
			return false;
		}
		else{
			return true;
		}
	}
	
	public E next(){
		if(!hasNext()) {
			throw new NoSuchElementException("No element left");
		}
		if(pos == null) {
			pos = last.next;
			return pos.element;
		}
		pos = pos.next;
		return pos.element;
		}
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

