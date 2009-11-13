package org.adligo.i.util.mocks;

import org.adligo.i.util.client.Event;
import org.adligo.i.util.client.I_Factory;
import org.adligo.i.util.client.I_Listener;
import org.adligo.i.util.client.ListenerValueObject;
import org.adligo.i.util.client.MockMap;
import org.adligo.i.util.client.MockThreadPopulator;
import org.adligo.i.util.client.PropertyFactory;

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
