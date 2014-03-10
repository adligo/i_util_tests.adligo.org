package org.adligo.i.util_tests;

import org.adligo.i.util.shared.TextFormatter;
import org.adligo.i.util_tests.shared.TextFormatterAssertions;
import org.adligo.i.util_tests.shared.mocks.MockTextFormatterFactory;
import org.adligo.jse.util.JSEPlatform;
import org.adligo.tests.ATest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TextFormatterTests extends ATest {
	private TextFormatterAssertions assertions = new TextFormatterAssertions();
	
	public TextFormatterTests() {
		assertions.setTest(this);
	}
	
	@Override
	public String getScope() {
		return TextFormatter.class.getName();
	}
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		MockTextFormatterFactory.uninit();
		
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		MockTextFormatterFactory.uninit();
		JSEPlatform.init();
	}
	
	@Test
	public void testTextFormatter() throws Exception {
		assertions.textFormatterAsserts();
	}
	
	
}
