package org.adligo.i.util_tests.shared;

import junit.framework.TestCase;

import org.adligo.i.util.shared.I_ThreadPopulator;
import org.adligo.i.util.shared.ThreadPopulatorFactory;
import org.adligo.i.util_tests.shared.mocks.MockThreadPopulatorFactory;
import org.adligo.tests.shared.AAssertions;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class ThreadPopulatorFactoryAssertions extends AAssertions {
	
	public void threadFactoryAssertions() throws Exception {
		
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
}
