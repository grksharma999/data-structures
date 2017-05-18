/**
 * A generic implementation of a D-ary heap inspired by the work of David Brink.
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 * @author David Brink
 **/

@SuppressWarnings("unchecked")
public class DHeap <T extends Comparable> {

  int d, n, sz;
  T[] heap;
  int[] child, parent;

  // Initializes a D-ary heap with a maximum capacity of n
  public DHeap(int n, int d) {
    this.n = n;
    this.d = d;
    heap = (T[]) new Comparable[n];
    child = new int[n];
    parent = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = (i-1)/d;
      child[i] = i*d+1;
    }
  }

  public boolean isEmpty() {
    return sz == 0;
  }

  // Polls an element from the priority queue.
  // Make sure the queue is not empty before calling.
  public T poll() {
    T root = heap[0];
    heap[0] = heap[--sz];
    heap[sz] = null;
    sink(0);
    return root;
  }

  // Adds a none null element to the priority queue
  public void add(T elem) {
    if (elem == null)
      throw new IllegalArgumentException("No null elements please :)");
    heap[sz] = elem;
    swim(sz);
    sz++;
  }

  private void sink(int i) {
    int j = minChild(i);
    while(j != -1) {
      swap(i, j);
      j = minChild(i = j);
    }
  }

  private void swim(int i) {
    while(less(i, parent[i])) {
      swap(i, parent[i]);
      i = parent[i];
    }
  }

  // From the parent node at index i find the minimum child below it
  private int minChild(int i) {
    T minElem = heap[i];
    int index = -1, from = child[i], to = Math.min(sz, from + d);
    for(int j = from; j < to; j++) {
      if (heap[j].compareTo(minElem) < 0 ) {
        minElem = heap[j];
        index = j;
      }
    }
    return index;
  }

  private boolean less(int i, int j) {
    return heap[i].compareTo(heap[j]) < 0;
  }

  private void swap(int i, int j) {
    T tmp = heap[i];
    heap[i] = heap[j];
    heap[j] = tmp;
  }

}












