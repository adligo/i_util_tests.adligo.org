package org.adligo.i.util_tests.mocks;

import org.adligo.i.util.shared.CollectionFactory;
import org.adligo.i.util.shared.I_Factory;
import org.adligo.i.util_tests.shared.MockImmutableMap;

public class MockImmutableMapFactory implements I_Factory {

	@Override
	public Object createNew(Object p) {
		return new MockImmutableMap();
	}

}
