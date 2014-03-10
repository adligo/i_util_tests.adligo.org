package org.adligo.i.util_tests.shared;

import org.adligo.i.util.shared.I_Iterator;
import org.adligo.i.util.shared.IteratorFactory;
import org.adligo.i.util_tests.shared.mocks.MockIteratorFactory;
import org.adligo.tests.shared.AAssertions;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class IteratorFactoryAssertions extends AAssertions {

	public void iteratorFactoryAsserts() throws Exception {
		
		assertFalse(IteratorFactory.isInit());
		Exception ex = null;
		try {
			new MockIteratorFactory(true);
		} catch (Exception x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("IteratorFactory needs a non null factory argument.", 
				ex.getMessage());
		
		assertFalse(IteratorFactory.isInit());
		
		
		new MockIteratorFactory();
		
		assertTrue(IteratorFactory.isInit());
		I_Iterator it = IteratorFactory.create(new MockCollection());
		assertTrue(it instanceof MockIterator);
		
		ex = null;
		try {
			new MockIteratorFactory();
		} catch (Exception x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("IteratorFactory was already initialized.", 
				ex.getMessage());
		
		
		
	}
}
