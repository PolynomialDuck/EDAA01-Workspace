package testqueue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import queue_singlelinkedlist.FifoQueue;

class TestAppendFifoQueue {
	private FifoQueue<Integer> myIntQueue1;
	private FifoQueue<Integer> myIntQueue2;
	@BeforeEach
	void setUp() throws Exception {
		myIntQueue1 = new FifoQueue<Integer>();
		myIntQueue2 = new FifoQueue<Integer>();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAppendEmpty() {
		assertEquals(0,myIntQueue1.size(), "Size not correct");
		assertEquals(0,myIntQueue2.size(), "Size not correct");
		myIntQueue1.append(myIntQueue2);
		assertEquals(0, myIntQueue1.size(), "Size not correct");
	}
	@Test
	void testAppendEmptyNonEmpty() {
		myIntQueue2.offer(1);
		assertEquals(1,myIntQueue2.size(), "Size not correct");
		myIntQueue1.append(myIntQueue2);
		assertEquals(1,myIntQueue1.poll(),"Poll 1 failed");
		assertNull(myIntQueue2.peek(), "Not Null");
	}
	@Test
	void testAppendNonEmptyEmpty() {
		myIntQueue1.offer(2);
		myIntQueue1.append(myIntQueue2);
		assertEquals(2,myIntQueue1.poll(), "Poll 2 failed");
	}
	@Test
	void testAppendNonEmpty() {
		myIntQueue1.offer(1);
		myIntQueue2.offer(2);
		myIntQueue1.append(myIntQueue2);
		assertEquals(1,myIntQueue1.poll(),"Poll 3 failed");
		assertEquals(2,myIntQueue1.poll(), "Poll 4 failed");
	}
	@Test
	void testSelfAppend() {
		myIntQueue1.append(myIntQueue1);
	}
}
