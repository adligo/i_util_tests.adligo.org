package org.adligo.i.util_tests;

import org.adligo.i.log.shared.Log;
import org.adligo.i.log.shared.LogFactory;
import org.adligo.i.util.shared.ArrayIterator;
import org.adligo.tests.ATest;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ArrayIteratorTests extends ATest {
	private static final Log log = LogFactory.getLog(ArrayIteratorTests.class);
	
	public void testEmptyArrayIterator() {
		ArrayIterator ar = new ArrayIterator(null);
		assertFalse(ar.hasNext());
		
		ar = new ArrayIterator(new Object[] {});
		assertFalse(ar.hasNext());
	}

	
}
