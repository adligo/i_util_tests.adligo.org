package org.adligo.i.util_tests;

import org.adligo.i.util.shared.IteratorFactory;
import org.adligo.i.util_tests.shared.IteratorFactoryAssertions;
import org.adligo.i.util_tests.shared.mocks.MockIteratorFactory;
import org.adligo.jse.util.JSEPlatform;
import org.adligo.tests.ATest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class IteratorFactoryTests extends ATest {
	private IteratorFactoryAssertions asserts = new IteratorFactoryAssertions();
	
	public IteratorFactoryTests() {
		asserts.setTest(this);
	}
	
	@Override
	public String getScope() {
		return IteratorFactory.class.getName();
	}
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		MockIteratorFactory.uninit();
		
	}
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		MockIteratorFactory.uninit();
		JSEPlatform.init();
	}
	
	@Test
	public void testIteratorFactoryTests() throws Exception {
		asserts.iteratorFactoryAsserts();
	}
}
