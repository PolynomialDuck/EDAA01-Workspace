package bst;

public class Main {
	static BinarySearchTree<Integer> myTree = new BinarySearchTree<Integer>();
	static int a = 12;
	static int b = 10;
	static int temp = a;
	public static void main(String[] args) {
		a = b;
		b = temp;
		System.out.println(a+" "+b);
		for(int i = 1; i < 25; i++) {
			myTree.add(i*i);
		}
		myTree.rebuild();
		BSTVisualizer bstVisualizer = new BSTVisualizer("Binary Search Tree Visulizer", 800, 800);
		bstVisualizer.drawTree(myTree);
	}

}
