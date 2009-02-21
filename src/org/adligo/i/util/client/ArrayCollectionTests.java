package org.adligo.i.util.client;

import java.util.ArrayList;

import org.adligo.i.util.client.ArrayCollection;
import org.adligo.i.util.client.I_Collection;
import org.adligo.i.util.client.I_Iterator;

import junit.framework.TestCase;

public class ArrayCollectionTests extends TestCase {

	public void testAddRemoveAndClear() {
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
		for (int i = 0; i < 10000; i++) {
			System.out.println("GCTracker iteration " + i + "\n\n\n\n");
			testMemory();
		}
	}
	
	public void testMemory() {
		GCTracker memTracker = new GCTracker(ArrayCollectionTests.class,
			"testMemory");
		ArrayCollection collection = new ArrayCollection();
		collection = null;
		//size of memTracker object...
		memTracker.assertUse(200);
		
	}
	
	public void testGet() {
		ArrayCollection col = new ArrayCollection();
		TestString one = new TestString("One");
		TestString oneA = new TestString("One");
		TestString two = new TestString("Two");
		TestString twoA = new TestString("Two");
		TestString three = new TestString("Three");
		
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

	private void assertOnes(ArrayCollection col, TestString one, TestString oneA, String p) {
		assertEquals("The instances should match",
				one, col.get(new TestString(p)));
		assertTrue("The instances should match",
				one == col.get(new TestString(p)));
		assertTrue("The instances should Not match " + oneA + " ... " + col.get(oneA),
				oneA != col.get(oneA));
	}


}
