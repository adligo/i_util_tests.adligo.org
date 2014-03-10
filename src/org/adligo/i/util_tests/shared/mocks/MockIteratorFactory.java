package org.adligo.i.util_tests.shared.mocks;

import org.adligo.i.util.shared.I_Factory;
import org.adligo.i.util.shared.IteratorFactory;
import org.adligo.i.util_tests.shared.MockIterator;

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
