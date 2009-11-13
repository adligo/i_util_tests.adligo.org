package org.adligo.i.util.mocks;

import org.adligo.i.util.client.I_Factory;
import org.adligo.i.util.client.MockTextFormatter;
import org.adligo.i.util.client.TextFormatter;

public class MockTextFormatterFactory extends TextFormatter implements I_Factory {

	public MockTextFormatterFactory(boolean initNull) throws Exception {
		TextFormatter.setDelegate(null);
	}
	
	public MockTextFormatterFactory() throws Exception {
		TextFormatter.setDelegate(new MockTextFormatter());
	}
	
	public static void uninit() {
		TextFormatter.delegate = null;
	}
	
	@Override
	public Object createNew(Object p) {
		// TODO Auto-generated method stub
		return null;
	}

}
