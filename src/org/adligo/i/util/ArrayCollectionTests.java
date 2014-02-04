package org.adligo.i.util;


import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.i.util.client.ArrayCollection;
import org.adligo.i.util.client.I_Collection;
import org.adligo.i.util.client.I_Iterator;
import org.adligo.tests.ATest;

/**
 * this tests the ArrayCollection and
 * ArrayIterator classes
 * 
 * @author scott
 *
 */
public class ArrayCollectionTests extends ATest {
	private static final Log log = LogFactory.getLog(ArrayCollectionTests.class);
	public static final String CONSTANT = "dosen't change";
	
	public void testAddRemoveAndClear() {
		if (log.isDebugEnabled()) {
			log.debug("starting testAddRemoveAndClear");
		}
		GCTracker memTracker = new GCTracker(ArrayCollectionTests.class,
				"testAddRemoveAndClear");
		
		I_Collection collection = new ArrayCollection();
		for (int i = 0; i < 235; i++) {
			//System.out.println("adding " + i);
			assertTrue("The collection should be able to add " + i,collection.add(i));
			assertEquals("The collections size should be ", i + 1, collection.size());
		}
		
		memTracker.assertUse(4200);
		I_Iterator it = collection.getIterator();
		int counter  = 0;
		while (it.hasNext()) {
			Integer item = (Integer) it.next();
			//System.out.println("fetched " + item);
			assertEquals("The object values should match", counter,item.intValue());
			counter++;
		}
		assertEquals("The iterator should have called next this many times", 235, counter);
		
		
		counter  = 0;
		assertTrue("The collection should be able to remove object Integer 200", 
				collection.remove(200));
		it = collection.getIterator();
		while (it.hasNext()) {
			Integer item = (Integer) it.next();
			//System.out.println("fetched " + item);
			if (item == 200) {
				assertTrue("The collection should not contain the number 200 " + item, false);
			} else {
				if (item > 200) {
					assertEquals("The object values should match " + item, counter + 1, item.intValue());
				} else {
					assertEquals("The object values should match " + item, counter, item.intValue());
				}
			}
			counter++;
		}
		assertEquals("The iterator should have called next this many times", 234, counter);
		
		collection.clear();
		assertEquals("The size should match",0, collection.size());
		it = collection.getIterator();
		assertFalse("The iterator should be empty ", it.hasNext());
		
		memTracker.assertUse(38000);
	}
	
	public void testMemoryRepeater() {
		/*
		 * test takes to long and
		 * leads to many false positives
		 * so its commented out
		GCTracker memTracker = new GCTracker(ArrayCollectionTests.class,
			"testMemoryRepeater");
		for (int i = 0; i < 10000; i++) {
			System.out.println("GCTracker iteration " + i + "\n\n\n\n");
			testMemory();
		}
		memTracker.assertUse(200);
		*/
	}
	
	public void testMemory() {
		if (log.isDebugEnabled()) {
			log.debug("starting testMemory");
		}
		
		GCTracker memTracker = new GCTracker(ArrayCollectionTests.class,
			"testMemory");
		ArrayCollection collection = new ArrayCollection();
		collection.add(CONSTANT);
		//size of memTracker object...
		memTracker.assertUse(1000);
		
	}
	public void testEquals() {
		if (log.isDebugEnabled()) {
			log.debug("starting testEquals");
		}
		ArrayCollection a = new ArrayCollection();
		ArrayCollection b = new ArrayCollection();
		
		a.add(1);
		a.add(2);
		a.add(3);
		
		b.add(1);
		assertFalse(a.equals(b));
		
		b.add(2);
		b.add(3);
		assertEquals(a, b);
	}
	
	public void testGet() {
		if (log.isDebugEnabled()) {
			log.debug("starting testGet");
		}
		ArrayCollection col = new ArrayCollection();
		MockString one = new MockString("One");
		MockString oneA = new MockString("One");
		MockString two = new MockString("Two");
		MockString twoA = new MockString("Two");
		MockString three = new MockString("Three");
		
		System.out.println("One " + one + " Two " + two + " Three " + three + " OneA " + oneA);
		
		col.add(one);
		
		assertOnes(col, one, oneA, "One");
		assertNull("Should return null",
				col.get("Two"));
		assertNull("Should return null",
				col.get(two));
		
		col.add(two);
		assertOnes(col, one, oneA, "One");
		assertOnes(col, two, twoA, "Two");
	}

	private void assertOnes(ArrayCollection col, MockString one, MockString oneA, String p) {
		assertEquals("The instances should match",
				one, col.get(new MockString(p)));
		assertTrue("The instances should match",
				one == col.get(new MockString(p)));
		assertTrue("The instances should Not match " + oneA + " ... " + col.get(oneA),
				oneA != col.get(oneA));
	}
	
	public void testGetInt() {
		if (log.isDebugEnabled()) {
			log.debug("starting testGetInt");
		}
		ArrayCollection col = new ArrayCollection();
		assertContent(col);
	}

	public void testChunkSize5() {
		if (log.isDebugEnabled()) {
			log.debug("starting testChunkSize5");
		}
		ArrayCollection col = new ArrayCollection(5);
		assertContent(col);
	}
	
	public void testToArray() {
		if (log.isDebugEnabled()) {
			log.debug("starting testChunkSize2");
		}
		ArrayCollection col = new ArrayCollection(2);
		col.add("Hey");
		assertEquals(1, col.size());
		Object [] objs = col.toArray();
		assertEquals("Hey", objs[0]);
		
		
	}
	
	public void testChunkSize10() {
		if (log.isDebugEnabled()) {
			log.debug("starting testChunkSize10");
		}
		ArrayCollection col = new ArrayCollection(10);
		assertContent(col);
	}
	
	private void assertContent(ArrayCollection col) {
		for (int i = 0; i <= 1001; i++) {
			col.add(i);
		}
		assertTrue(1002 == col.size());
		assertNull("Should match ",col.get(-1));
		
		assertEquals("Should match ", 0, col.get(0));
		assertEquals("Should match ", 1, col.get(1));
		assertEquals("Should match ", 98, col.get(98));
		assertEquals("Should match ", 99, col.get(99));
		assertEquals("Should match ", 100, col.get(100));
		assertEquals("Should match ", 101, col.get(101));
		assertEquals("Should match ", 102, col.get(102));
		assertEquals("Should match ", 998, col.get(998));
		assertEquals("Should match ", 999, col.get(999));
		assertEquals("Should match ", 1000, col.get(1000));
		assertEquals("Should match ", 1001, col.get(1001));
	}

	

}
