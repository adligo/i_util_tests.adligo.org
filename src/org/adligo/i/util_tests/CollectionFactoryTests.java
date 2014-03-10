package org.adligo.i.util_tests;

import org.adligo.i.util.shared.CollectionFactory;
import org.adligo.i.util_tests.shared.CollectionFactoryAssertions;
import org.adligo.i.util_tests.shared.mocks.MockCollectionFactory;
import org.adligo.jse.util.JSEPlatform;
import org.adligo.tests.ATest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CollectionFactoryTests extends ATest {
	private CollectionFactoryAssertions asserts = new CollectionFactoryAssertions();
	
	public CollectionFactoryTests() {
		asserts.setTest(this);
	}
	
	@Override
	public String getScope() {
		return CollectionFactory.class.getName();
	}
	@Before
	public void setUp() throws Exception {
		super.setUp();
		MockCollectionFactory.uninit();
		
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		MockCollectionFactory.uninit();
		JSEPlatform.init();
	}
	
	@Test
	public void collectionFactoryAssertions() throws Exception {
		asserts.collectionFactoryAsserts();
	}

}
