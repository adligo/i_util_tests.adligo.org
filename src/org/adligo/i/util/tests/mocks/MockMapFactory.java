package org.adligo.i.util.tests.mocks;

import org.adligo.i.util.client.I_Factory;
import org.adligo.i.util.client.MapFactory;
import org.adligo.i.util.tests.client.MockMap;

public class MockMapFactory extends MapFactory implements I_Factory {

	public enum INIT_TYPES  {
		BOTH_NULL, NULL_IMUTE, NULL_ME, NULL_SYNC
	}
	
	public static void unInit() {
		MapFactory.me = null;
		MapFactory.imute_me = null;
	}
	
	public MockMapFactory(INIT_TYPES p) throws Exception {
		switch (p) {
			case BOTH_NULL:
				MapFactory.init(null, null,null);
				break;
			case NULL_IMUTE:
				MapFactory.init(this, null,null);
				break;
			case NULL_SYNC:
				MapFactory.init(this, new MockImmutableMapFactory(),null);
				break;
			case NULL_ME:
				I_Factory fact = null;
				MapFactory.init(fact, new MockImmutableMapFactory(), fact);	
		}
	}
	
	public MockMapFactory() throws Exception {
		MapFactory.init(this, new MockImmutableMapFactory(),this);	
	}

	@Override
	public Object createNew(Object p) {
		return new MockMap();
	}
	
	
}
