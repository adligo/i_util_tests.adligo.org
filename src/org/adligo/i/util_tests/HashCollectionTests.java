package org.adligo.i.util_tests;

import org.adligo.i.util.shared.HashCollection;
import org.adligo.i.util_tests.shared.HashCollectionAssertions;
import org.adligo.tests.ATest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HashCollectionTests extends ATest {
	private HashCollectionAssertions asserts = new HashCollectionAssertions();
	
	public HashCollectionTests() {
		asserts.setTest(this);
	}
	
	@Override
	public String getScope() {
		return HashCollection.class.getName();
	}
	
	@Test
	public void testLessThanOneHundred() {
		asserts.lessThanOneHundredAsserts();
	}
	
	@Test
	public void testGetBucket() {
		asserts.getBucketAsserts();
	}
	
	@Test
	public void testAdd10000Numbers() { 
		asserts.add10000NumbersAsserts();
	}
	
	@Test
	public void testPut10000Numbers() { 
		asserts.put10000NumbersAsserts();
	}
	
	@Test
	public void testPut10000HashCodeDuplicates() { 
		asserts.put10000HashCodeDuplicatesAsserts();
	}
	
	@Test
	public void testEvery1000000Numbers() { 
		asserts.every1000000NumbersAsserts();
	}
}

