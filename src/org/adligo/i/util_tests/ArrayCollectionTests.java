package org.adligo.i.util_tests;


import org.adligo.i.util.shared.ArrayCollection;
import org.adligo.i.util_tests.shared.ArrayCollectionAssertions;
import org.adligo.tests.ATest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * this tests the ArrayCollection and
 * ArrayIterator classes
 * 
 * @author scott
 *
 */
@RunWith(JUnit4.class)
public class ArrayCollectionTests extends ATest {
	private ArrayCollectionAssertions asserts = new ArrayCollectionAssertions();
	
	public ArrayCollectionTests() {
		asserts.setTest(this);
	}
	
	@Override
	public String getScope() {
		return ArrayCollection.class.getName();
	}
	
	@Test
	public void testAddRemoveAndClear() {
		asserts.addRemoveAndClearAsserts();
	}
	
	@Test
	public void testMemoryRepeater() {
		asserts.memoryRepeaterAsserts();
	}
	
	@Test
	public void testMemory() {
		asserts.memoryAsserts();
	}
	
	@Test
	public void testEquals() {
		asserts.equalsAsserts();
	}
	
	@Test
	public void testGet() {
		asserts.getAsserts();
	}

	
	@Test
	public void testGetInt() {
		asserts.getIntAsserts();
	}

	@Test
	public void testChunkSize5() {
		asserts.chunkSize5Asserts();
	}
	
	@Test
	public void testToArray() {
		asserts.toArrayAsserts();
	}
	
	@Test
	public void testChunkSize10() {
		asserts.chunkSize10Asserts();
	}
}
