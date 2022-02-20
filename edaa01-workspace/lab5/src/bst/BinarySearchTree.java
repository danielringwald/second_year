package bst;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTree<E> {
	BinaryNode<E> root; // Används också i BSTVisaulizer
	int size; // Används också i BSTVisaulizer
	private Comparator<E> comparator;

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree();
		bst.add(5);
		bst.add(8);
		bst.add(10);
		bst.add(19);
		bst.add(14);
		bst.add(4);
		bst.add(14);
		bst.add(1);
		bst.add(2);
		bst.add(3);
		bst.add(6);
		bst.add(7);
		bst.add(9);
		bst.add(20);
		bst.add(16);
		bst.add(19);
		bst.add(21);
		bst.add(22);
		bst.add(23);
		bst.add(24);
		bst.add(25);
		bst.add(26);
		bst.rebuild();
		BSTVisualizer bstv = new BSTVisualizer("Träd", 500, 500);
		bst.printTree();
		bstv.drawTree(bst);
	}

	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
		comparator = null;
	}

	/**
	 * Constructs an empty binary search tree, sorted according to the specified
	 * comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		root = null;
		size = 0;
		this.comparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		}
		return addable(x, root);
	}

	private boolean addable(E x, BinaryNode<E> node) {

		if (node.element == x) {
			return false;
		}

		// Mindre än noll innebär att x är "större"
		if (compare(node.element, x) < 0) {
			if (node.right == null) {
				node.right = new BinaryNode<E>(x);
				size++;
				return true;
			} else {
				return addable(x, node.right);
			}
		} else {
			if (node.left == null) {
				node.left = new BinaryNode<E>(x);
				size++;
				return true;
			} else {
				return addable(x, node.left);
			}
		}

		// Testa igen

	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		return heightCalc(root);
	}

	private int heightCalc(BinaryNode<E> node) {
		if (node == null) {
			return 0;
		}
		int heightLeft = heightCalc(node.left);
		int heightRight = heightCalc(node.right);
		return Math.max(heightLeft, heightRight) + 1;
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		if (size == 0) {

		} else {
			print(root);
		}
	}

	private void print(BinaryNode<E> node) {
		if (node != null) {
			print(node.left);
			System.out.println(node.element);
			print(node.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> sorted = new ArrayList<E>();
		toArray(root, sorted);
		int first = 0;
		int last = sorted.size()-1;
	
		root = buildTree(sorted, first, last);
		
	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if (n != null) {
			toArray(n.left, sorted);
			sorted.add(n.element);
			toArray(n.right, sorted);
		}
	}

	/*
	 * Builds a complete tree from the elements from position first to last in the
	 * list sorted. Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if (last > first) {
		int indexMid = (first + last)/2;
		BinaryNode<E> node = new BinaryNode<E>(sorted.get(indexMid));
		node.left = buildTree(sorted, first, indexMid-1);		//vänster träd
		node.right = buildTree(sorted, indexMid+1, last);//höger träd
		return node;
		} else {
		BinaryNode<E> node = new BinaryNode<E>(sorted.get(first));			//METOD ÄR FEL FIXA TILL DEN????????
		node = new BinaryNode<E>(sorted.get(last));
		return node;
		}
		
	}

	@SuppressWarnings("unchecked")
	public int compare(E o1, E o2) {
		if (comparator != null) {
			return comparator.compare(o1, o2);
		}
		return ((Comparable<E>) o1).compareTo(o2);
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
