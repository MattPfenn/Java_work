package ch.hearc.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import ch.hearc.list.List;

public class TestList {

	@Test
	public void test1() {
		List<Integer> l = new List<Integer>();

		l.addTail(5);
		l.addTail(4);
		l.addTail(3);
		l.addTail(2);
		l.addTail(1);
		for (int i=1; i<6; i++)
			assertEquals(Integer.valueOf(i), l.getTail(true));
	}

	@Test
	public void test2(){
		List<Integer> l = new List<Integer>();
		
		l.addHead(5);
		l.addHead(4);
		l.addHead(3);
		l.addHead(2);
		l.addHead(1);
		for (int i=1; i<6; i++)
			assertEquals(Integer.valueOf(i), l.getHead(true));
	}
	
	@Test
	public void test3(){
		//TODO
	}
	
	@Test
	public void testGetTail(){
		
	}

}