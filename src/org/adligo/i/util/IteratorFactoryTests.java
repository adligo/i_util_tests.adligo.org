package org.adligo.i.util;

import org.adligo.i.util.client.CollectionFactory;
import org.adligo.i.util.client.I_Collection;
import org.adligo.i.util.client.I_Iterator;
import org.adligo.i.util.client.IteratorFactory;
import org.adligo.i.util.client.MockCollection;
import org.adligo.i.util.client.MockIterator;
import org.adligo.i.util.mocks.MockCollectionFactory;
import org.adligo.i.util.mocks.MockIteratorFactory;
import org.adligo.jse.util.JSEPlatform;

import junit.framework.TestCase;

public class IteratorFactoryTests extends TestCase {

	public IteratorFactoryTests() {
		super("org.adligo.i.util.IteratorFactoryTests");
	}
	public void testIteratorFactoryTests() throws Exception {
		
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
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		MockIteratorFactory.uninit();
		
	}
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		MockIteratorFactory.uninit();
		JSEPlatform.init();
	}
}
