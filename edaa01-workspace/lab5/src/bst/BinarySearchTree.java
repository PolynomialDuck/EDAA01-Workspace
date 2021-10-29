package bst;

import java.util.ArrayList;
import java.util.Comparator;


public class BinarySearchTree<E extends Comparable<E>> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> comparator;
    
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		this.size = 0;
		this.root = null;
		this.comparator = E::compareTo;
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		this.size = 0;
		this.root = null;
		this.comparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if(root==null) {
			root = new BinaryNode<E>(x);
			size=1;
			return true;
		}
		return add(root, x);
	}
	
	private boolean add(BinaryNode<E> node, E x) {
		boolean temp = false;

		if(comparator.compare(node.element, x) == 0) {
			return false;	
		}
		if(comparator.compare(node.element, x) < 0 && node.right == null) {
			node.right = new BinaryNode<E>(x);
			size++;
			return true;
		}
		else if(comparator.compare(node.element, x) < 0 && node.right != null) {
			temp = add(node.right, x);
		}
		if(comparator.compare(node.element, x) > 0 && node.left == null){
			node.left = new BinaryNode<E>(x);
			size++;
			return true;
		}
		else if(comparator.compare(node.element, x) > 0 && node.left != null) {
			temp = add(node.left, x);
		}
		return temp;
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	private int height(BinaryNode<E> node) {
		if(node == null) {
			return 0;
		}
		int lHeight = height(node.left);
		int rHeight = height(node.right);
		
		return (Math.max(lHeight, rHeight)+1);
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		size = 0;
		root = null;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}
	private void printTree(BinaryNode<E> n) { 
		if(n != null) {
			printTree(n.left);
			System.out.println(n.element);
			printTree(n.right);
		}
	} 
	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> temp = new ArrayList<E>();
		toArray(root,temp);
		root = buildTree(temp,0,temp.size()-1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
	if(n != null) {
		toArray(n.left, sorted);
		sorted.add(n.element);
		toArray(n.right, sorted);
		}
	}
	
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if(first > last) {
			return null;
		}
		int mid = middle(first, last);
		BinaryNode<E> rootTree = new BinaryNode<E>(sorted.get(mid));
		rootTree.left = buildTree(sorted, first, mid-1);
		rootTree.right = buildTree(sorted, 1+mid, last);
			
		return rootTree;
	}
	
	private int middle(int x, int y) {
		return (x+((y-x)/2));
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}
