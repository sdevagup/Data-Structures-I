                                                      Pravallika Devaguptapu
                                                          ID: 1936201  

package csc402;

import stdlib.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* ***********************************************************************
 *
 *  This is a simpler version of the MaxPQ class from the algs24
 *  package.  It is a generic max priority queue implementation
 *  with a binary heap.
 *
 *  We use a one-based array to simplify parent and child calculations.
 *
 *************************************************************************/

/**
 *  The <tt>MaxPQ</tt> class represents a priority queue of generic keys.
 *  It supports the usual <em>insert</em> and <em>delete-the-maximum</em>
 *  operations, along with methods for peeking at the maximum key,
 *  testing if the priority queue is empty, and iterating through
 *  the keys.
 *  <p>
 *  The <em>insert</em> and <em>delete-the-maximum</em> operations take
 *  logarithmic amortized time.
 *  The <em>max</em>, <em>size</em>, and <em>is-empty</em> operations take constant time.
 *  Construction takes time proportional to the specified capacity or the number of
 *  items used to initialize the data structure.
 *  <p>
 *  This implementation uses a binary heap.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class FEMaxPQ<K extends Comparable<? super K>> {
	private K[] pq; // store items at indices 1 to N
	private int N;  // number of items on priority queue
	private int sinkCounter;
	private int swimCounter;

	public int getSinkCounter(){
		return this.sinkCounter;
	}
	
	public int getSwimCounter(){
		return this.swimCounter;
	}
	
	// helper function to double the size of the heap array
	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		if (capacity <= N) throw new IllegalArgumentException ();
		K[] temp = (K[]) new Comparable[capacity];
		for (int i = 1; i <= N; i++) temp[i] = pq[i];
		pq = temp;
	}

	@SuppressWarnings("unchecked")
	/** Create an empty priority queue with the given initial capacity, using the given comparator. */
	public FEMaxPQ(int initCapacity) {
		pq = (K[]) new Comparable[initCapacity + 1];
		N = 0;
	}
	/** Create an empty priority queue. */
	public FEMaxPQ() { this(1); }

	/** Is the priority queue empty? */
	public boolean isEmpty() { return N == 0; }

	/** Return the number of items on the priority queue. */
	public int size() { return N; }

	/**
	 * Return the largest key on the priority queue.
	 * @throws java.util.NoSuchElementException if the priority queue is empty.
	 */
	public K max() {
		if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		return pq[1];
	}

	/** Add a new key to the priority queue. */
	public void insert(K x) {
		// double size of array if necessary
		if (N >= pq.length - 1) resize(2 * pq.length);

		// Add x, and swim it up to maintain heap invariant
		N++;        // Increment the number of elements
		pq[N] = x;  // Place the new element at the end
		swim(N);    // Swim the new element to its proper location
	}

	/**
	 * Delete and return the largest key on the priority queue.
	 * @throws java.util.NoSuchElementException if the priority queue is empty.
	 */
	public K delMax() {
		if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		K max = pq[1];  // Save the maximum element
		pq[1] = pq[N];  // Move the last element to the root
		pq[N] = null;   // Empty the position where the last element was
		N--;            // Decrement the number of elements
		sink(1);        // Sink the moved last element to its proper location
		if ((N > 0) && (N == (pq.length - 1) / 4)) resize(pq.length / 2);
		return max;
	}


	/* *********************************************************************
	 * Helper functions to restore the heap invariant.
	 **********************************************************************/

	private void swim(int k) {
		// The first while condition stops the loop when
		// an element reaches the root; the second condition
		// stops the loop when an element reaches a position
		// where it is less than its parent element.
		//swimCounter = 0;
		while (k > 1 && less(k/2, k)) {
			swimCounter++;
			// Swap an element with its parent.
			K swap = pq[k];
			pq[k] = pq[k/2];
			pq[k/2] = swap;
			// Make k the index of the parent.
			k = k/2;
		}
	}

	private void sink(int k) {
		//sinkCounter = 0;
		// The while condition stops the loop when an element
		// reaches the bottom of the tree.
		while (2*k <= N) {
			sinkCounter++;
			// After the following two statements, j is the index
			// of the child of k with the larger value.
			int j = 2*k;
			if (j < N && less(j, j+1)) j++;
			// If the element at k is larger than the values at
			// its children, break out of the loop.
			if (!less(k, j)) break;
			// Swap the element at k with the child having
			// the larger value.
			K swap = pq[k];
			pq[k] = pq[j];
			pq[j] = swap;
			// Make k the index of the child.
			k = j;
		}
	}

	/* *********************************************************************
	 * Helper function for compares.
	 **********************************************************************/
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
}
