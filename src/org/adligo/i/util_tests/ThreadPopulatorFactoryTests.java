package org.adligo.i.util_tests;

import org.adligo.i.util.shared.ThreadPopulatorFactory;
import org.adligo.i.util_tests.shared.ThreadPopulatorFactoryAssertions;
import org.adligo.i.util_tests.shared.mocks.MockThreadPopulatorFactory;
import org.adligo.jse.util.JSEPlatform;
import org.adligo.tests.ATest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ThreadPopulatorFactoryTests extends ATest {
	public ThreadPopulatorFactoryAssertions assertions = new ThreadPopulatorFactoryAssertions();
	
	public ThreadPopulatorFactoryTests() {
		assertions.setTest(this);
	}
	
	@Override
	public String getScope() {
		return ThreadPopulatorFactory.class.getName();
	}
	
	@Before
	public void setUp() throws Exception {
		MockThreadPopulatorFactory.uninit();
	}
	
	
	@Test
	public void testThreadPopulatorFactor() throws Exception {
		assertions.threadFactoryAssertions();
	}

}
