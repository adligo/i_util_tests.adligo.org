package org.adligo.i.util_tests.shared.mocks;

import org.adligo.i.util.shared.Event;
import org.adligo.i.util.shared.I_Factory;
import org.adligo.i.util.shared.I_Listener;
import org.adligo.i.util.shared.ListenerValueObject;
import org.adligo.i.util.shared.PropertyFactory;
import org.adligo.i.util_tests.shared.MockMap;
import org.adligo.i.util_tests.shared.MockThreadPopulator;

public class MockPropertyFactory extends PropertyFactory implements I_Factory {

	public MockPropertyFactory(boolean initNull) throws Exception {
		PropertyFactory.init(null);
	}
	
	public MockPropertyFactory() throws Exception {
		PropertyFactory.init(this);
	}
	
	public static void uninit() {
		PropertyFactory.me = null;
	}
	
	@Override
	public Object createNew(Object p) {
		ListenerValueObject params = (ListenerValueObject) p;
		Event e = new Event();
		e.setValue(new MockMap());
		I_Listener listener = params.getListener();
		listener.onEvent(e);
		return null;
	}

}
