package org.adligo.i.util.tests;

import junit.framework.TestCase;

import org.adligo.i.util.client.I_Event;
import org.adligo.i.util.client.I_Iterator;
import org.adligo.i.util.client.I_Listener;
import org.adligo.i.util.client.I_Map;
import org.adligo.i.util.client.I_ThreadPopulator;
import org.adligo.i.util.client.IteratorFactory;
import org.adligo.i.util.client.PropertyFactory;
import org.adligo.i.util.client.ThreadPopulatorFactory;
import org.adligo.i.util.tests.client.MockCollection;
import org.adligo.i.util.tests.client.MockIterator;
import org.adligo.i.util.tests.client.MockMap;
import org.adligo.i.util.tests.client.MockThreadPopulator;
import org.adligo.i.util.tests.mocks.MockIteratorFactory;
import org.adligo.i.util.tests.mocks.MockPropertyFactory;
import org.adligo.i.util.tests.mocks.MockThreadPopulatorFactory;
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
