package org.adligo.i.util_tests;

import org.adligo.i.util.shared.PropertyFactory;
import org.adligo.i.util_tests.shared.PropertyFactoryAssertions;
import org.adligo.i.util_tests.shared.mocks.MockPropertyFactory;
import org.adligo.jse.util.JSEPlatform;
import org.adligo.tests.ATest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PropertyFactoryTests extends ATest {
	private PropertyFactoryAssertions asserts = new PropertyFactoryAssertions();
	
	public PropertyFactoryTests() {
		asserts.setTest(this);
	}
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		MockPropertyFactory.uninit();
		JSEPlatform.init();
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		MockPropertyFactory.uninit();
	}
	
	@Test
	public void testPropertyFactory() throws Exception {
		asserts.propertyFactoryAssertions();
	}
	

}
