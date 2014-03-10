package org.adligo.i.util_tests;

import org.adligo.i.util.shared.MapFactory;
import org.adligo.i.util_tests.shared.MapFactoryAssertions;
import org.adligo.i.util_tests.shared.mocks.MockMapFactory;
import org.adligo.jse.util.JSEPlatform;
import org.adligo.tests.ATest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MapFactoryTests extends ATest {
	private MapFactoryAssertions asserts = new MapFactoryAssertions();
	
	public MapFactoryTests() {
		asserts.setTest(this);
	}
	
	@Override
	public String getScope() {
		return MapFactory.class.getName();
	}
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		MockMapFactory.unInit();
		
		
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		MockMapFactory.unInit();
		JSEPlatform.init();
	}

	@Test
	public void testMapFactory() throws Exception {
		asserts.mapFactoryAsserts();
		
	}
	
}
