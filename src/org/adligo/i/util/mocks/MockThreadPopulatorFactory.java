package org.adligo.i.util.mocks;

import org.adligo.i.util.client.I_Factory;
import org.adligo.i.util.client.MockThreadPopulator;
import org.adligo.i.util.client.ThreadPopulatorFactory;

public class MockThreadPopulatorFactory extends ThreadPopulatorFactory implements I_Factory {

	public MockThreadPopulatorFactory(boolean initNull) throws Exception {
		ThreadPopulatorFactory.init(null);
	}
	
	public MockThreadPopulatorFactory() throws Exception {
		ThreadPopulatorFactory.init(new MockThreadPopulator());
	}
	
	public static void uninit() {
		ThreadPopulatorFactory.pop = null;
	}
	
	@Override
	public Object createNew(Object p) {
		return new MockThreadPopulator();
	}

}
