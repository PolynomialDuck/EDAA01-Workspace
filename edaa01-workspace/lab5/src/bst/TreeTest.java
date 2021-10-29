package bst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TreeTest {
	BinarySearchTree<Integer> myTree1;
	BinarySearchTree<Integer> myTree2;
	@BeforeEach
	void setUp() throws Exception {
		myTree1 = new BinarySearchTree<Integer>();
		myTree2 = new BinarySearchTree<Integer>((x,y) -> x.compareTo(y));
	}

	@AfterEach
	void tearDown() throws Exception {
		myTree1 = null;
	}

	@Test
	void testAdd() {
		assertEquals(0, myTree1.size(), "Wrong Size 1");
		myTree1.add(5);
		myTree1.add(2);
		myTree1.printTree();
		assertEquals(2, myTree1.size(), "Wrong Size 2");
	}

	@Test
	void testHeight() {
		myTree1.add(5);
		myTree1.add(2);
		myTree1.add(6);
		assertEquals(2, myTree1.height(), "Wrong Height");
	}

	@Test
	void testClear() {
		myTree1.add(5);
		myTree1.add(2);
		myTree1.add(6);
		myTree1.clear();
		assertEquals(0, myTree1.size(), "Wrong Height");
	}

}
