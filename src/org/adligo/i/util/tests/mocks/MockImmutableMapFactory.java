package org.adligo.i.util.tests.mocks;

import org.adligo.i.util.client.CollectionFactory;
import org.adligo.i.util.client.I_Factory;
import org.adligo.i.util.tests.client.MockImmutableMap;

public class MockImmutableMapFactory implements I_Factory {

	@Override
	public Object createNew(Object p) {
		return new MockImmutableMap();
	}

}
