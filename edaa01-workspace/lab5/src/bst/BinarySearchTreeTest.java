package bst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {
	private BinarySearchTree<Integer> bstInt;
	private BinarySearchTree<String> bstStr;
	private BinarySearchTree<Integer> bstIntComp;
	
	@BeforeEach
	void setUp() throws Exception {
		bstInt = new BinarySearchTree<Integer>();
		bstStr = new BinarySearchTree<String>();
		bstIntComp = new BinarySearchTree<Integer>((e1, e2) -> e2 + e1);
	}

	@AfterEach
	void tearDown() throws Exception {
		bstInt.clear();
		bstStr.clear();
	}

	@Test
	void heightTest() {
		bstInt.add(1);
		bstInt.add(2);
		bstInt.add(3);
		//System.out.println(bstInt.height());
		assertTrue(bstInt.height() == 3);
		
		bstIntComp.add(3);
		bstIntComp.add(2);
		bstIntComp.add(4);
		//System.out.println(bstInt.height());
		assertTrue(bstInt.height() == 3);
		
		bstStr.add("a");
		bstStr.add("d");
		bstStr.add("e");
		bstStr.add("c");
		assertTrue(bstStr.height() == 3);
		
	}
	
	@Test
	void sizeTest() {
		bstInt.add(1);
		bstInt.add(2);
		bstInt.add(3);
		assertTrue(bstInt.size() == 3);
		
		bstStr.add("a");
		bstStr.add("b");
		bstStr.add("e");
		bstStr.add("c");
		assertTrue(bstStr.size() == 4);
		
	}
	

	@Test
	void clearTest() {
		bstInt.add(1);
		bstInt.add(2);
		bstInt.add(3);
		bstStr.add("a");
		bstStr.add("b");
		bstStr.add("e");
		bstStr.add("c");
		
		bstInt.clear();
		bstStr.clear();
		
		assertTrue(bstStr.size() == 0);
		assertTrue(bstInt.height() == 0);
		assertTrue(bstStr.size() == 0);
		assertTrue(bstInt.height() == 0);
		
	}
	
	@Test
	void printTreeTest() {
		bstInt.add(1);
		bstInt.add(2);
		bstInt.add(3);
		
		
	}
}
