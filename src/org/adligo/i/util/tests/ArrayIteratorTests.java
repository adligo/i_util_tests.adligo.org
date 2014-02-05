package org.adligo.i.util.tests;

import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.i.util.client.ArrayIterator;
import org.adligo.tests.ATest;

public class ArrayIteratorTests extends ATest {
	private static final Log log = LogFactory.getLog(ArrayIteratorTests.class);
	
	public void testEmptyArrayIterator() {
		ArrayIterator ar = new ArrayIterator(null);
		assertFalse(ar.hasNext());
		
		ar = new ArrayIterator(new Object[] {});
		assertFalse(ar.hasNext());
	}

	
}
