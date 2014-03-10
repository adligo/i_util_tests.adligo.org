package org.adligo.i.util_tests.shared;

import java.util.HashSet;
import java.util.Set;

import org.adligo.i.log.shared.Log;
import org.adligo.i.log.shared.LogFactory;
import org.adligo.i.util.shared.HashCollection;
import org.adligo.i.util_tests.shared.mocks.BadHashCode;
import org.adligo.tests.ATest;
import org.adligo.tests.shared.AAssertions;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class HashCollectionAssertions extends AAssertions {
	private static final Log log = LogFactory.getLog(HashCollectionAssertions.class);
	
	
	public void lessThanOneHundredAsserts() {
		HashCollection container = new HashCollection();
		
		Object one = "One";
		container.add(one);
		Object gotOne = container.get(one.hashCode());
		assertTrue(one == gotOne);
		container.remove(one.hashCode());
		assertNull(container.get(one.hashCode()));
		
		for (int i = 0; i < 100; i++) {
			container.add("s" + i);
		}
		
		for (int i = 0; i < 100; i++) {
			String s = "s" + i;
			assertEquals(s, container.get(s.hashCode()));
		}
		
	}
	
	public void getBucketAsserts() {
		
		long totalSpan = HashCollection.INT_SPAN;
		// -2147483648
		// 2147483647
		// + 1 for zero
		assertEquals(new Double(42949672.96 * 100).longValue(), totalSpan);
		
		if (log.isDebugEnabled()) {
			log.debug("totalSpanStart " + totalSpan);
		}
		int span = new Long(totalSpan/ 100).intValue();
		
		if (log.isDebugEnabled()) {
			log.debug("1st split span is " + span + 
					" total span is " + totalSpan);
		}
		assertEquals(42949672, HashCollection.getSpan(totalSpan, 100));
		
		int bucket = HashCollection.getBucket(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, span);
		assertEquals(0, bucket);
		
		bucket = HashCollection.getBucket(Integer.MIN_VALUE + 1, 
				Integer.MIN_VALUE, Integer.MAX_VALUE, span);
		assertEquals(0, bucket);
		
		bucket = HashCollection.getBucket(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, span);
		assertEquals(99, bucket);
		
		bucket = HashCollection.getBucket(Integer.MAX_VALUE - 1, 
				Integer.MIN_VALUE, Integer.MAX_VALUE, span);
		assertEquals(99, bucket);
		
		//bucket = HashCollection.getBucket(27296544, 8, 16777224, 1048576);
		//assertNotNull(bucket);
	}
	
	public void add10000NumbersAsserts() { 
		HashCollection container = new HashCollection();
		int count = 0;
		long lastTs = System.currentTimeMillis();
		
		Set<IntHash> allAdded = new HashSet<IntHash>();
		
		for (int i = 0; i < 1000; i++) {
			if (log.isDebugEnabled()) {
				log.debug("add i " + i);
			}
			IntHash ih = new IntHash(i);
			assertTrue(container.add(ih));
			assertEquals(i + 1, container.size());
			if (log.isDebugEnabled()) {
				//log.debug("container is now " + container);
			}
			
			assertTrue(allAdded.add(ih));
			if (count == 1000) {
				long now = System.currentTimeMillis();
				long diff = now - lastTs;
				lastTs = now;
				log.warn("calcing next 1 hundred hashs i is " + i + " took " + diff + " milliseconds");
				count = 0;
			}
			count++;
			for (int j = 0; j <= i; j++) {
				IntHash jh = new IntHash(j);
				IntHash other = (IntHash) container.get(jh.hashCode());
				assertEquals(jh, other);
			}
		}
		assertEquals(1000, allAdded.size());
		
		for (int i = 0; i < 1000; i++) {
			IntHash ih = new IntHash(i);
			assertTrue(container.remove(ih.hashCode()));
			assertEquals(999 - i, container.size());
			if (count == 100) {
				long now = System.currentTimeMillis();
				long diff = now - lastTs;
				lastTs = now;
				log.warn("asserting next 1 hundred hashs i is " + i + " took " + diff + " milliseconds");
				count = 0;
			}
			count++;
		}
		assertEquals(0, container.size());
	}
	
	public void put10000NumbersAsserts() { 
		HashCollection container = new HashCollection();
		int count = 0;
		
		Set<IntHash> allAdded = new HashSet<IntHash>();
		
		for (int i = 0; i < 10000; i++) {
			IntHash ih = new IntHash(i);
			assertTrue(container.add(ih));
			assertTrue(allAdded.add(ih));
			if (count == 1000) {
				log.warn("calcing next 1 thousand hashs i is " + i);
				count = 0;
			}
			count++;
		}
		assertEquals(10000, container.size());
		
		for (int i = 0; i < 10000; i++) {
			IntHash ih = new IntHash(i);
			IntHash other = (IntHash) container.get(ih.hashCode());
			assertEquals(ih, other);
			assertTrue(container.remove(ih.hashCode()));
			if (count == 1000) {
				log.warn("asserting next 1 thousand hashs i is " + i);
				count = 0;
			}
			count++;
		}
		
		//replace with integers
		for (int i = 0; i < 10000; i++) {
			assertTrue(container.put(i));
			Integer other = (Integer) container.get(i);
			assertEquals(new Integer(i), other);
			if (count == 1000) {
				log.warn("asserting replacing next 1 thousand hashs i is " + i);
				count = 0;
			}
			count++;
		}
		
		for (int i = 0; i < 10000; i++) {
			assertTrue(container.remove(i));
			assertEquals(9999 - i,container.size());
			if (count == 1000) {
				log.warn("asserting remove next 1 thousand hashs i is " + i);
				count = 0;
			}
			count++;
		}
		assertEquals(0, container.size());
	}
	
	public void put10000HashCodeDuplicatesAsserts() { 
		HashCollection container = new HashCollection();
		int count = 0;
		
		Set<BadHashCode> allAdded = new HashSet<BadHashCode>();
		
		for (int i = 0; i < 10000; i++) {
			BadHashCode ih = new BadHashCode(i);
			assertTrue(container.add(ih));
			assertTrue(allAdded.add(ih));
			if (count == 1000) {
				log.warn("calcing next 1 thousand hash code dupes i is " + i);
				count = 0;
			}
			count++;
		}
		assertEquals(10000, container.size());
		
		for (int i = 0; i < 10000; i++) {
			BadHashCode ih = new BadHashCode(i);
			BadHashCode other = (BadHashCode) container.get(ih);
			assertEquals(ih, other);
			
			assertEquals(10000 - i, container.size());
			assertTrue(container.remove(ih));
			if (count == 1000) {
				log.warn("asserting next 1 thousand hash code dupes " + i);
				count = 0;
			}
			count++;
			
		}
		assertEquals(0, container.size());
	}
	
	public void every1000000NumbersAsserts() { 
		HashCollection container = new HashCollection();
		int count = 0;
		
		Set<IntHash> allAdded = new HashSet<IntHash>();
		
		for (int i = Integer.MIN_VALUE; i <= Integer.MAX_VALUE;) {
			IntHash ih = new IntHash(i);
			container.add(ih);
			assertTrue(allAdded.add(ih));
			if (count == 1000) {
				log.warn("calcing next 1 thousand hashs i is " + i);
				count = 0;
			}
			long next = new Long(i).longValue() + 1000000;
			if (next > Integer.MAX_VALUE) {
				break;
			} else {
				i = (int) next;
			}
			count++;
		}
		assertEquals(4295, allAdded.size());
		
		for (IntHash ih: allAdded) {
			IntHash other = (IntHash) container.get(ih.hashCode());
			assertTrue("IntHash should show up " + ih, ih == other);
			if (count == 1000) {
				log.warn("asserting next  1 thousand hashs");
				count = 0;
			}
			count++;
		}
		
		//and now top down
		container.clear();
		allAdded.clear();
		int debugNumber = 356483647;
		for (int i = Integer.MAX_VALUE; i >= Integer.MIN_VALUE;) {
			IntHash ih = new IntHash(i);
			if (i == debugNumber) {
				System.out.println("debug");
			}
			assertTrue(container.put(ih));
			assertTrue(allAdded.add(ih));
			if (count == 1000) {
				log.warn("calcing next 1 thousand hashs i is " + i);
				count = 0;
			}
			long next = new Long(i).longValue() - 1000000;
			if (next < Integer.MIN_VALUE) {
				break;
			} else {
				i = (int) next;
			}
			count++;
		}
		
		assertEquals(4295, allAdded.size());
		
		for (IntHash ih: allAdded) {
			if (ih.hashCode() == debugNumber) {
				System.out.println("debug");
			}
			IntHash other = (IntHash) container.get(ih.hashCode());
			assertTrue("IntHash should show up " + ih, ih == other);
			assertTrue(container.remove(ih.hashCode()));
			if (count == 1000) {
				log.warn("asserting next  1 thousand hashs");
				count = 0;
			}
			count++;
		}
		
		assertEquals(0, container.size());
	}

	
	
}

class IntHash {
	int hash;
	
	public IntHash(int i) {
		hash = i;
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntHash other = (IntHash) obj;
		if (hash != other.hash)
			return false;
		return true;
	}
	
	public String toString() {
		return "IntHash" + hash;
	}
	
}
