package org.adligo.i.util.mocks;

import org.adligo.i.util.client.I_Factory;
import org.adligo.i.util.client.IteratorFactory;
import org.adligo.i.util.client.MockIterator;

public class MockIteratorFactory extends IteratorFactory implements I_Factory {

	
	public MockIteratorFactory(boolean initNull) throws Exception {
		IteratorFactory.init(null);
	}
	
	public MockIteratorFactory() throws Exception {
		IteratorFactory.init(this);
	}
	
	public static void uninit() {
		IteratorFactory.me = null;
	}
	
	@Override
	public Object createNew(Object p) {
		return new MockIterator();
	}

	
}
