/**
 * Sorted list with integers
 * @version 2017-09-13
 */
public class SortedList1 { 
  private static class Node {
    int data;
    Node next;
    
    Node(int data, Node next) {
      this.data = data;
      this.next = next;     
    }
  }
  
  public static class ListException extends RuntimeException {
    public ListException(String msg) {
      super(msg);
    }   
  }
  
  private Node first;
  
  public SortedList1() {
    first = null;
  }
  
  
  public String toString() {
    String result = "";
    Node t = first; 
    while (t != null) {
      result = result + " " + t.data;   
      t = t.next;
    }
    return "(" + result + ")";
  }
  
  public int size() {
    return size(first);
  } 
  
  private static int size(Node n) {
    if (n == null) { 
      return 0;
    } else {
      return 1 + size(n.next);    
    }
  } 
  public void add(int x) {
    this.first = add(x, first);
  }
  
  private static Node add(int x, Node n) {
    if (n == null || x < n.data) {
      return new Node(x, n);
    } else {
      n.next = add(x, n.next);
      return n;  
    }
  }
  
  public SortedList1 copy() {
    return new SortedList1(copy(first));
  }
  
  private static Node copy(Node n) {
    if (n == null) {
      return null;  
    } else {
      return new Node(n.data, copy(n.next));
    }
  }
  
  private SortedList1(Node n) {  // A private constructor
    first = n;
  }
  
  public int removeFirst() {
    if (first==null) {
      throw new ListException("Empty list in removeFirst");      
    }
    int result = first.data;
    first = first.next;
    return result;
  }
  
  
  /**********************************************************
    * 
    * Assignments: Implement the following methods
    * 
    **********************************************************/
  
  
  /**
   * Check if a specified data item is in the list
   * @param x the data item to be searched for
   * @return <code>true</code> if the data item is found, else <code>false</code>
   * 
   * Notes to the programmer:
   * Should be implemented with recursion.
   * Should run in O(n) time if the list is of length n.
   */
  
  public boolean contains(int x) {
    return contains(x, first);
  }
  
  private static boolean contains(int x, Node n) {
    if (n == null) {
      return false;
    } else if (n.data == x) {
      return true;
    } else {
      return contains(x, n.next);
    }
  }
  
  /**
   * Check if a specified data item is in the list
   * @param x the data item to be searched for
   * @return <code>true</code> if the data item is found, else <code>false</code>
   * 
   * Notes to the programmer:
   * Should be implemented with iteration.
   * Should run in O(n) time if the list is of length n.
   */
  public boolean containsIter(int x) {
    Node n = first;
    boolean status = false;
    while(n != null ){
      if(n.data == x){
        return true;
      }
      
      n = n.next;
    }
    return status;
  }
  
  
  /**
   * Find the last (i.e. the largest) data item in the list
   * @return the value of the last data item
   * @throws ListException if the list is empty
   * 
   * Notes to the programmer:
   * Should be implemented with recursion.
   * Should run in O(n) time if the list is of length n.
   */
  public int getLast() {
    Node n = first;
    return getLast(n);
  }
  
  private static int getLast(Node n){
    if(n == null){
      throw new ListException("This list is empty!");
    }else if(n.next == null){
      return n.data;
    }else 
      return getLast(n.next);
  }
  
  
  /**
   * Find the last (i.e. the largest) data item in the list
   * @return the value of the last data item
   * @throws ListException if the list is empty
   * Notes to the programmer:
   * Should be implemented using iteration
   * Should run in O(n) time if the list is of length n.
   */
  public int getLastIter() {
    Node n = first;
    if (n == null) {
      throw new ListException("This list is empty!");
    }
    while(n.next != null){
      n = n.next;
    }
    
    return n.data;
  }
  
  
  /**
   * Find the data item at a specified position in the list.
   * Positions are numbered from 0 and upwards.
   * @param i the position to be checked
   * @return the value at position <code>i</code>
   * @throws ListException if <code>i</code> specifies a nonexistent position
   * 
   * Note to the programmer:
   * Should be implemented with recursion.
   * Should run in O(n) time if the list is of length n.
   */
  public int atIndex(int i) {
    Node n = first;
    return atIndex(i, n);
  }
  
  private static int atIndex(int i, Node n) {
    if (n == null || i < 0) {
      throw new ListException("Index does not exist!");
    } else if (i == 0) {
      return n.data;
    } else {
      return atIndex(i - 1, n.next);
    }
  }
  
  
  /**
   * Find the position of a specified data item
   * @param x the data item to be searched for
   * @return the position of data item or -1 if the list does not contain x
   *
   * Note to the programmer:
   * Should be implemented with recursion.
   * Should run in O(n) time if the list is of length n.
   */
  public int indexOf(int x) {
    int counter = 0;
    return indexOf(x, counter, first);   
  }
  
  private static int indexOf(int x, int counter,  Node n){
    if(n == null){
      return -1;
    }else if(x == n.data){
      return counter;
    }else 
      return indexOf(x, counter + 1, n.next);
  }
  
  
  /** 
   * Clear the list, i.e. removes all element from the list. 
   * 
   * Note to the programmer:
   * Should run in O(1) time if the list is of length n.
   */
  public void clear() {
    //
  }
  
  
  
  /**
   * Remove the last element in the list.
   * @returns the data item in the removed element
   * @throws ListException if the list is empty
   *
   * Notes to the programmer:
   * Should run in O(n) time if the list is of length n.
   */
  public int removeLast() {
    return -999;
  }
  
  
  /**
   * Remove the first node with a specified data item
   * @param x the value to be removed
   * @throws ListException if x is not found
   */
  public void remove(int x) { 
    first = remove(x, first); 
  }
  
  /**
   * Recursive help method for remove.
   * @param x the value to be removed
   * @param n the first node in the sequences of nodes to be handled
   * @return the first node in the shrinked list
   * @throws ListException if x is not found
   * 
   * Notes to the programmer: 
   * Should be implemented with recursion.
   * This may be a bit tricky - study the given insert method!
   * Should run in O(n) time
   */
  private static Node remove(int x, Node p) { 
    return null;
  }
  
  
  
  /**
   * Merge this list with another list.
   * <p>
   * Example:
   * <p>
   * If this list contains {1, 3, 4, 8} and the other list contains {2, 3, 8, 8, 9}
   * this method should create a list containing {1, 2, 3, 3, 4, 8, 8, 8, 9}
   * @param l the other list to be used
   * @return a new list object containing the union of the lists
   * 
   * Note to the programmer:
   * Should be implemented with recursion
   * Should run in O(n+m) time if the list are of length n and m.
   */
  public SortedList merge(SortedList l) {
    return null; 
  }
  
  
  /**
   * Check if two lists contain the same data items
   *
   * @param l the list to be checked against
   * @return <code>true</code> if the lists contain exactly the same elements, 
   *         else <code>false</code>
   * 
   * Notes to the programmer:
   * Should be implemented with recursion.
   * Should run in O(n+m) time if the list are of length n and m.
   * 
   */
  public boolean equals(SortedList l) {
    return false;
  }
  
  
  /**
   * Main method trying the methods above
   */
  public static void main(String[] args) {
    System.out.println("Test main version of 2017-09-13");
    SortedList p = new SortedList();
    p.add(5);
    p.add(7);
    p.add(3);
    p.add(1); 
    System.out.println("Test list: " + p.toString());
    
    System.out.println("\nSize: " + p.size());              // Test size
    System.out.println("\ncontains(0) : " + p.contains(0));  // Test contains
    System.out.println("contains(1) : " + p.contains(1));
    System.out.println("contains(2) : " + p.contains(2));
    System.out.println("contains(5) : " + p.contains(5));
    System.out.println("contains(7) : " + p.contains(7));
    System.out.println("contains(9) : " + p.contains(9));
    
    System.out.println("containsIter(0): " + p.containsIter(0));   // Test containsIter
    System.out.println("containsIter(1): " + p.containsIter(1));
    System.out.println("containsIter(2): " + p.containsIter(2));
    System.out.println("containsIter(5): " + p.containsIter(5));
    System.out.println("containsIter(7): " + p.containsIter(7));
    System.out.println("containsIter(9): " + p.containsIter(9));
    
    System.out.println("\ngetLast()    : " + p.getLast());    // Test getLast
    System.out.println("getLastIter(): " + p.getLastIter());
    
    System.out.println("\natIndex(0) : " + p.atIndex(0));     // Test atIndex
    System.out.println("atIndex(1) : " + p.atIndex(1));     
    System.out.println("atIndex(2) : " + p.atIndex(2));
    try {
      System.out.println("atIndex(9) : " + p.atIndex(9));   // Force an exception      
    } catch (ListException e) {
      System.out.println("*** List exception: " + e.getMessage());
    }
    
    System.out.println("\nindexOf(1) : " + p.indexOf(1));   // Test indexOf
    System.out.println("indexOf(7) : " + p.indexOf(7));
    System.out.println("indexOf(4) : " + p.indexOf(4));
    
    System.out.println("\nremoveFirst: " + p.removeFirst()); // Test removeFirst
    System.out.println("p: " + p); 
    
    System.out.println();
    for (int i = 0; i < 3; i++) { 
      System.out.println("removeLast : " + p.removeLast());  // Test removeLast
    }
    System.out.println("p after all removeLast: " + p); 
    
    p.add(3); p.add(8);
    System.out.println("\nnew p: " + p);
    p.clear();
    System.out.println("After clear(): " + p); 
    
    System.out.println("\nForce an exception in removeFirst:");             // Test ListException
    try {
      p.removeFirst();
    } catch (ListException e) {
      System.out.println("*** List exception: " + e.getMessage());
    }
    
    // Rebuilding lists
    for (int i=0; i<20; i++) {
      p.add((int)(Math.random()*10));
    }
    
    System.out.println("\nInserted random numbers: " + p);
    
    for (int i= 0; i<10; i+=2 ) {
      while (p.contains(i))
        p.remove(i);
    }
    System.out.println("Without even numbers   : " + p);
    
    SortedList q = p.copy();
    q.add(42);
    System.out.println("\np: " + p);
    System.out.println("q: " + q);
    
    System.out.println("p.equals(q): " + p.equals(q));   // Test equals
    p.add(42);
    System.out.println("p.equals(q): " + p.equals(q) + 
                       " after insertion in p");
    
    // Test merge
    for (int i=0; i<10; i++) {
      p.add((int)(Math.random()*15));
      q.add((int)(Math.random()*15));
    }
    
    System.out.println("\np: " + p);
    System.out.println("q: " + q);
    System.out.println("p.merge(q): " + p.merge(q));
  }
  
  
  
}