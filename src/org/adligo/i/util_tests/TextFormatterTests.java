package org.adligo.i.util_tests;

import junit.framework.TestCase;

import org.adligo.i.util.shared.I_Event;
import org.adligo.i.util.shared.I_Iterator;
import org.adligo.i.util.shared.I_Listener;
import org.adligo.i.util.shared.I_Map;
import org.adligo.i.util.shared.I_TextFormatter;
import org.adligo.i.util.shared.I_ThreadPopulator;
import org.adligo.i.util.shared.IteratorFactory;
import org.adligo.i.util.shared.PropertyFactory;
import org.adligo.i.util.shared.TextFormatter;
import org.adligo.i.util.shared.ThreadPopulatorFactory;
import org.adligo.i.util_tests.mocks.MockIteratorFactory;
import org.adligo.i.util_tests.mocks.MockPropertyFactory;
import org.adligo.i.util_tests.mocks.MockTextFormatterFactory;
import org.adligo.i.util_tests.mocks.MockThreadPopulatorFactory;
import org.adligo.i.util_tests.shared.MockCollection;
import org.adligo.i.util_tests.shared.MockIterator;
import org.adligo.i.util_tests.shared.MockMap;
import org.adligo.i.util_tests.shared.MockTextFormatter;
import org.adligo.i.util_tests.shared.MockThreadPopulator;
import org.adligo.jse.util.JSEPlatform;

public class TextFormatterTests extends TestCase {
	
	public TextFormatterTests() {
		super("org.adligo.i.util.TextFormatterTests");
	}
	public void testPropertyFactory() throws Exception {
		
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
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		MockTextFormatterFactory.uninit();
		
	}
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		MockTextFormatterFactory.uninit();
		JSEPlatform.init();
	}
}
