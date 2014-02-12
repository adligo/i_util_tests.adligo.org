package org.adligo.i.util_tests;

import junit.framework.TestCase;

import org.adligo.i.util.shared.I_Event;
import org.adligo.i.util.shared.I_Iterator;
import org.adligo.i.util.shared.I_Listener;
import org.adligo.i.util.shared.I_Map;
import org.adligo.i.util.shared.IteratorFactory;
import org.adligo.i.util.shared.PropertyFactory;
import org.adligo.i.util_tests.mocks.MockIteratorFactory;
import org.adligo.i.util_tests.mocks.MockPropertyFactory;
import org.adligo.i.util_tests.shared.MockCollection;
import org.adligo.i.util_tests.shared.MockIterator;
import org.adligo.i.util_tests.shared.MockMap;
import org.adligo.jse.util.JSEPlatform;

public class PropertyFactoryTests extends TestCase {
	I_Map props = null;
	
	public PropertyFactoryTests() {
		super("org.adligo.i.util.PropertyFactoryTests");
	}
	public void testPropertyFactory() throws Exception {
		
		assertFalse(PropertyFactory.isInit());
		Exception ex = null;
		try {
			new MockPropertyFactory(true);
		} catch (Exception x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("PropertyFactory needs a non null factory argument.", 
				ex.getMessage());
		
		assertFalse(PropertyFactory.isInit());
		
		new MockPropertyFactory();
		
		
		assertTrue(PropertyFactory.isInit());
		PropertyFactory.get(null, new I_Listener() {
			
			@Override
			public void onEvent(I_Event p) {
				props = (I_Map) p.getValue();
			}
		});
		assertTrue(props instanceof MockMap);
		
		ex = null;
		try {
			new MockPropertyFactory();
		} catch (Exception x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("PropertyFactory was already initialized.", 
				ex.getMessage());
		
		
		
	}
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		MockPropertyFactory.uninit();
		
	}
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		MockPropertyFactory.uninit();
		JSEPlatform.init();
	}
}
