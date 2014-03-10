package org.adligo.i.util_tests.shared;

import org.adligo.i.log.shared.Log;
import org.adligo.i.log.shared.LogFactory;
import org.adligo.i.util.shared.ArrayIterator;
import org.adligo.tests.shared.AAssertions;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class ArrayIteratorAssertions extends AAssertions {
	private static final Log log = LogFactory.getLog(ArrayIteratorAssertions.class);
	
	public void emptyArrayIteratorAssertions() {
		ArrayIterator ar = new ArrayIterator(null);
		assertFalse(ar.hasNext());
		
		ar = new ArrayIterator(new Object[] {});
		assertFalse(ar.hasNext());
	}

	
}
