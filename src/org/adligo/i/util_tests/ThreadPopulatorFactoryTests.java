package org.adligo.i.util_tests;

import junit.framework.TestCase;

import org.adligo.i.util.shared.I_Event;
import org.adligo.i.util.shared.I_Iterator;
import org.adligo.i.util.shared.I_Listener;
import org.adligo.i.util.shared.I_Map;
import org.adligo.i.util.shared.I_ThreadPopulator;
import org.adligo.i.util.shared.IteratorFactory;
import org.adligo.i.util.shared.PropertyFactory;
import org.adligo.i.util.shared.ThreadPopulatorFactory;
import org.adligo.i.util_tests.mocks.MockIteratorFactory;
import org.adligo.i.util_tests.mocks.MockPropertyFactory;
import org.adligo.i.util_tests.mocks.MockThreadPopulatorFactory;
import org.adligo.i.util_tests.shared.MockCollection;
import org.adligo.i.util_tests.shared.MockIterator;
import org.adligo.i.util_tests.shared.MockMap;
import org.adligo.i.util_tests.shared.MockThreadPopulator;
import org.adligo.jse.util.JSEPlatform;

public class ThreadPopulatorFactoryTests extends TestCase {
	
	public ThreadPopulatorFactoryTests() {
		super("org.adligo.i.util.ThreadPopulatorFactoryTests");
	}
	public void testPropertyFactory() throws Exception {
		
		assertFalse(ThreadPopulatorFactory.isInit());
		Exception ex = null;
		try {
			new MockThreadPopulatorFactory(true);
		} catch (Exception x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("ThreadPopulatorFactory needs a non null I_ThreadPopulator argument.", 
				ex.getMessage());
		
		assertFalse(ThreadPopulatorFactory.isInit());
		
		new MockThreadPopulatorFactory();
		
		
		assertTrue(ThreadPopulatorFactory.isInit());
		I_ThreadPopulator pop = ThreadPopulatorFactory.getThreadPopulator();
		
		
		assertTrue(pop instanceof MockThreadPopulator);
		
		ex = null;
		try {
			new MockThreadPopulatorFactory();
		} catch (Exception x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("ThreadPopulatorFactory was already initialized.", 
				ex.getMessage());
		
		
		
	}
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		MockThreadPopulatorFactory.uninit();
		
	}
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		MockThreadPopulatorFactory.uninit();
		JSEPlatform.init();
	}
}
