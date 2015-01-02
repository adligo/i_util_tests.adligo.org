package org.adligo.i.util_tests;

import org.adligo.i.util.shared.ArrayIterator;
import org.adligo.i.util_tests.shared.ArrayIteratorAssertions;
import org.adligo.tests.ATest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ArrayIteratorTests extends ATest {
	private ArrayIteratorAssertions asserts = new ArrayIteratorAssertions();
	
	public ArrayIteratorTests() {
		asserts.setTest(this);
	}

	@Test
	public void testEmptyArrayIterator() {
		asserts.emptyArrayIteratorAssertions();
	}

	

	
}
