package org.adligo.i.util_tests.shared;

import org.adligo.i.util.shared.CollectionFactory;
import org.adligo.i.util.shared.I_Collection;
import org.adligo.i.util_tests.shared.mocks.MockCollectionFactory;
import org.adligo.tests.shared.AAssertions;

public class CollectionFactoryAssertions extends AAssertions {

	public void collectionFactoryAsserts() throws Exception {
		
		assertFalse(CollectionFactory.isInit());
		Exception ex = null;
		try {
			new MockCollectionFactory(true);
		} catch (Exception x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("org.adligo.i.util.shared.CollectionFactory the me variable is null?", 
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
		assertEquals("org.adligo.i.util.shared.CollectionFactory has already been initalized.", 
				ex.getMessage());
		
		
		
	}
}
