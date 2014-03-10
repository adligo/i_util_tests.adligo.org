package org.adligo.i.util_tests.shared;

import org.adligo.i.util.shared.I_TextFormatter;
import org.adligo.i.util.shared.TextFormatter;
import org.adligo.i.util_tests.shared.mocks.MockTextFormatterFactory;
import org.adligo.tests.shared.AAssertions;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TextFormatterAssertions extends AAssertions {
	
	public void textFormatterAsserts() throws Exception {
		
		assertFalse(TextFormatter.isInit());
		Exception ex = null;
		try {
			new MockTextFormatterFactory(true);
		} catch (Exception x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("TextFormatter needs a non null I_TextFormater argument.", 
				ex.getMessage());
		
		assertFalse(TextFormatter.isInit());
		
		new MockTextFormatterFactory();
		
		
		assertTrue(TextFormatter.isInit());
		I_TextFormatter fmter = TextFormatter.getInstance();
		
		
		assertTrue(fmter instanceof MockTextFormatter);
		
		ex = null;
		try {
			new MockTextFormatterFactory();
		} catch (Exception x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("TextFormatter has already been initialized.", 
				ex.getMessage());
		
	}
}
