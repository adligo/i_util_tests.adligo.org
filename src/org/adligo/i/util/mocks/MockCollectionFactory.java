package org.adligo.i.util.mocks;

import org.adligo.i.util.client.CollectionFactory;
import org.adligo.i.util.client.I_Factory;
import org.adligo.i.util.client.MockCollection;

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
