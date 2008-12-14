package org.adligo.i.log.client;

import java.util.ArrayList;

import org.adligo.i.util.client.ArrayCollection;
import org.adligo.i.util.client.I_Iterator;

import junit.framework.TestCase;

public class ArrayCollectionTests extends TestCase {

	public void testAddAndClear() {
		ArrayCollection collection = new ArrayCollection();
		for (int i = 0; i < 235; i++) {
			//System.out.println("adding " + i);
			collection.add(i);
			assertEquals("The collections size should be ", i + 1, collection.size());
		}
		
		I_Iterator it = collection.getIterator();
		int counter  = 0;
		while (it.hasNext()) {
			Integer item = (Integer) it.next();
			//System.out.println("fetched " + item);
			assertEquals("The object values should match", counter,item.intValue());
			counter++;
		}
		assertEquals("The iterator should have called next this many times", 235, counter);
		
		collection.clear();
		assertEquals("The size should match",0, collection.size());
		it = collection.getIterator();
		assertFalse("The iterator should be empty ", it.hasNext());
		
	}
	
	public void testMemory() {
		long inital = getMemoryUse();
		//System.out.print("inital memory use is " + inital);
		//ArrayList list = new ArrayList();
		ArrayCollection collection = new ArrayCollection();
		
		long used = getMemoryUse() - inital;
		//System.out.println(" a single array collection took " + used);
		assertTrue("The ArrayCollection should take up almost no memeory and it took " + 1, 1> used);
		
		
	}
	
	 private static long getMemoryUse(){
		    long totalMemory = Runtime.getRuntime().totalMemory();
		    long freeMemory = Runtime.getRuntime().freeMemory();

		    return (totalMemory - freeMemory);
	 }

}
