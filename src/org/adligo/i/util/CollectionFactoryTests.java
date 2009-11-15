package org.adligo.i.util;

import junit.framework.TestCase;

import org.adligo.i.util.client.CollectionFactory;
import org.adligo.i.util.client.I_Collection;
import org.adligo.i.util.client.MockCollection;
import org.adligo.i.util.mocks.MockCollectionFactory;
import org.adligo.j2se.util.J2SECollectionFactory;
import org.adligo.j2se.util.J2SEPlatform;

public class CollectionFactoryTests extends TestCase {

	public CollectionFactoryTests() {
		super("org.adligo.i.util.CollectionFactoryTests");
	}
	public void testCollectionFactory() throws Exception {
		
		assertFalse(CollectionFactory.isInit());
		Exception ex = null;
		try {
			new MockCollectionFactory(true);
		} catch (Exception x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("org.adligo.i.util.client.CollectionFactory the me variable is null?", 
				ex.getMessage());
		
		assertFalse(CollectionFactory.isInit());
		
		
		new MockCollectionFactory();
		
		assertTrue(CollectionFactory.isInit());
		I_Collection collection = CollectionFactory.create();
		assertTrue(collection instanceof MockCollection);
		
		ex = null;
		try {
			new MockCollectionFactory();
		} catch (Exception x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("org.adligo.i.util.client.CollectionFactory has already been initalized.", 
				ex.getMessage());
		
		
		
	}
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		MockCollectionFactory.uninit();
		
	}
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		MockCollectionFactory.uninit();
		J2SEPlatform.init();
	}
	
}