package org.adligo.i.util_tests.mocks;

import org.adligo.i.util.shared.I_Factory;
import org.adligo.i.util.shared.TextFormatter;
import org.adligo.i.util_tests.shared.MockTextFormatter;

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
