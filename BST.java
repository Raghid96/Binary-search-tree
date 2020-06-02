import java.util.ArrayList;

/**
 * Binary search tree with strings as keys.
 * @version 2017-09-13
 */
public class BST {
  /**
   * Inner class for tree nodes
   */
  private static class Node {
    private String key;
    private Node left, right;
    
    private Node(String key, Node left, Node right) {
      this.key = key;
      this.left  = left;
      this.right = right;
    }
  }
  
  /**
   * Inner class for TreeExceptions
   */
  public static class BSTException extends RuntimeException {
    public BSTException(String msg) {
      super(msg);
    }
  }
  
  private Node root;
  
  /**
   * Standard constructor
   */
  public BST() { 
    root = null; 
  }
  
  private BST(Node r) { 
    root = r; 
  }
  
  /**
   * Searches a specified key
   * @param key the key to be searched
   * @return true if the key is found, else false
   */ 
  public boolean contains(String key) {
    return contains(key, root);
  }
  
  private static boolean contains(String key, Node r) {
    if (r==null) {
      return false;
    } else if (key.compareTo(r.key) < 0) {
      return contains(key, r.left);   
    } else if (key.compareTo(r.key) > 0) {
      return contains(key, r.right);  
    } else {
      return true;
    }
  }
  
  
  /**
   * Insert a key preserving the sorted condition
   * @param key the key to be inserted
   */
  public void add(String key) {
    root = add(key, root);
  }
  
  private static Node add(String key, Node r) {
    if (r==null) {
      return new Node(key, null, null);
    } else if (key.compareTo(r.key) < 0) {
      r.left = add(key, r.left);
    } else if (key.compareTo(r.key) > 0) {
      r.right = add(key, r.right);
    } else {
      // Do nothing - the key is already in the tree
    }
    return r;
  }
  
  public String toString() {
    return "<" + toString(root) + ">";
  }
  
  private static String toString(Node r) {
    if (r==null) {
      return "";
    } else {
      return toString(r.left) + " " + r.key + toString(r.right);
    }
  }
  
  /******************** Methods to be implemented *************/
  
  /**
   * Compute the number of nodes in the tree
   * @return the number of nodes
   */
  public int size() {
    return size(root); 
  }
  
  public static int size(Node n){
    if(n == null){
      return 0;
    }else 
      return  size(n.left) + 1 + size(n.right);
  }
  
  
  /**
   * Compute the height.
   * The height is defined as the number of nodes on 
   * the longest path from the root to a leaf.
   * @return the height
   */ 
  public int height() {
    return height(root);
  }
  
  public static int height(Node r){
    if(r == null){
      return 1;
    }else
      return 1 + Math.max(height(r.left), height(r.right));
  }
  
  
  /**
   * Find the smallest (defined by compareTo()) key in the tree
   * @return the smallest key
   */
  public String smallest() {
    return smallest(root);
    
  }
  
  public static String smallest( Node r){
    if(r == null){
      throw new BSTException("Empty");
    }else if(r.left == null){
      return r.key;
    }else 
      return smallest(r.left);      
  }
  
  
  /**
   * Construct an arraylist containing the keys from the nodes in symmetric order
   * i.e. the keys will be stored in alphabetic order.
   * @return an arraylist containing all keys from the tree i alphabetic order
   */
  public ArrayList<String> toArrayList() {
    ArrayList<String> arra = new ArrayList<String>();
    toArrayList(root, arra);
    return arra;
  }
  
  private ArrayList<String> toArrayList(Node r, ArrayList<String> a){
    if(r != null){
      toArrayList(r.left, a);
      a.add(r.key);
      toArrayList(r.right, a);
    }
    return a;
  }
  
  /**
   * Create a (deep) copy of the tree structure
   * @return a tree containing a copy of this tree
   */
  public BST copy() {
    BST newTree = new BST(copy(root));  
    return newTree;
  }
  
  private Node copy(Node r){
    if(r == null){
      return null;
    }else
      return new Node(r.key, copy(r.left) ,copy(r.right));
  }
  
  
  /**
   * Check if this tree is equal to another tree.
   * Equal means the same branching structure and the same keys in the nodes.
   * @param t the tree to be compared with
   * @return <code>true</code> if the trees are equal, else <code>false</code
   */
  public boolean equals(BST t) {
    Node r = t.root;
    return equals(r, root);
  }
  
  private boolean equals(Node r, Node s){
    if(r == null && s == null){
      return true;
    }else if(r == null){
      return false;
    }else if(s == null){
      return false;
    }else
      return r.key.equals(s.key) && equals(s.left, r.left) && equals(s.right, r.right);
  }
  
  
  /**
   * Check if two trees have exactly the same contents
   * @param t the tree to be compared with
   * @return <code>true</code> if the trees have the same contents, else <code>false</code>
   */
  public boolean sameContents(BST t) {
    String first = t.toString();
    String second = this.toString();
    return first.equals( second);
  }
  
  
  /**
   * Compute the internal path length.
   * The internal path length can be defined as the
   * sum of the depths of the individual nodes.
   * The root has depth 1, the children of the root depth 2 etc.
   * Thus, a tree with one node has ipl 1, 
   * a tree with two nodes has ipl 3 and a tree with three nodes 
   * ipl 5 or 6 depending on shape.
   */
  public int ipl() {
    int x = 0;
    return ipl(this.root, x);
  }
  
  private int ipl(Node r, int x) {
    if (r == null) {
      return 0;
    } else {
      x++;
      return ipl(r.right, x) + x + ipl(r.left, x);
    }
  }
  
  /**
   * Main-method showing calls to and results from all methods above
   */
  
  public static void main(String[] args) {
    BST bst1 = new BST();
    bst1.add("C");
    System.out.println(bst1);
    bst1.add("A");
    System.out.println(bst1);
    bst1.add("KK");
    System.out.println(bst1);
    bst1.add("T");
    System.out.println(bst1);
    
    System.out.println("BST size: " + bst1.size());
    System.out.println("BST height: " + bst1.height());
    System.out.println("Smallest: " + bst1.smallest());
    System.out.println("As arraylist: " + bst1.toArrayList());
    System.out.println("Copy last tree: " + bst1.copy());
    System.out.println("Check if two trees equals each other: " + bst1.equals(bst1.copy()));
    System.out.println("Check if two trees got same contents: " + bst1.sameContents(bst1.copy()));
    BST newBst1 = bst1.copy();
    newBst1.add("U");
    System.out.println("Check if two trees equals each other: " + bst1.equals(newBst1));
    System.out.println("The internal path length: " + bst1.ipl());
  }
  
}
