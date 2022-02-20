package testqueue;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import queue_singlelinkedlist.FifoQueue;
//import queue_singlelinkedlist.FifoQueue;

//import java.util.NoSuchElementException;
//import java.util.Queue;
//import java.util.Iterator;

public class TestAppendFifoQueue {
	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;
	
	@Before
	public void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		q1 = null;
		q2 = null;		
	}
	
	@Test
	public void testTwoEmpty() {
		q1.append(q2);
		assertTrue(q1.size() == 0);
		assertTrue(q2.size()==0);
	}

	@Test
	public void nonEmptyToEmpty() {
		q2.offer(1);
		q2.offer(2);
		q2.offer(3);
		
		q1.append(q2);
		assertTrue(q1.size()==3);
		int p = q1.peek();
		assertEquals(1,p);
		for (int i = 1; i <= 3; i++) {
			int k = q1.poll();
			assertEquals("poll returns incorrect element", i, k);
		}
		assertTrue(q2.size()==0);
		
	}
	
	@Test
	public void EmptyToNonEmpty() {
		q1.offer(1);
		q1.offer(2);
		q1.offer(3);
		
		q1.append(q2);
		assertTrue(q1.size()==3);
		assertTrue(q2.size()==0);
	}
	
	@Test
	public void nonEmptyToNonEmpty() {
		q1.offer(1);
		q1.offer(2);
		q2.offer(3);
		q2.offer(4);
		
		q1.append(q2);
		
		assertTrue(q1.size()==4);
		for (int i = 1; i <= 4; i++) {
			int k = q1.poll();
			assertEquals("poll returns incorrect element", i, k);
		}
		assertTrue("Ej rätt", q2.size()==0);
	}
	
	@Test
	public void addToSelf() {
		q1.offer(1);
		try {
			q1.append(q1);
			fail("Should raise exception");
		} catch (Exception IllegalArgumentException) {
			System.out.println("Wow");
		}
	}
	
}
