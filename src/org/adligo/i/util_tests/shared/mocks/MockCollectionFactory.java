package org.adligo.i.util_tests.shared.mocks;

import org.adligo.i.util.shared.CollectionFactory;
import org.adligo.i.util.shared.I_Factory;
import org.adligo.i.util_tests.shared.MockCollection;

public class MockCollectionFactory extends CollectionFactory implements I_Factory {

	public MockCollectionFactory(boolean initNull) throws Exception {
		CollectionFactory.init(null);
	}
	
	public MockCollectionFactory() throws Exception {
		CollectionFactory.init(this);
	}

	@Override
	public Object createNew(Object p) {
		return new MockCollection();
	}
	
	public static void uninit() {
		CollectionFactory.me = null;
	}
}
