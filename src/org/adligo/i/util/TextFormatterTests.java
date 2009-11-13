package org.adligo.i.util;

import junit.framework.TestCase;

import org.adligo.i.util.client.I_Event;
import org.adligo.i.util.client.I_Iterator;
import org.adligo.i.util.client.I_Listener;
import org.adligo.i.util.client.I_Map;
import org.adligo.i.util.client.I_TextFormatter;
import org.adligo.i.util.client.I_ThreadPopulator;
import org.adligo.i.util.client.IteratorFactory;
import org.adligo.i.util.client.MockCollection;
import org.adligo.i.util.client.MockIterator;
import org.adligo.i.util.client.MockMap;
import org.adligo.i.util.client.MockTextFormatter;
import org.adligo.i.util.client.MockThreadPopulator;
import org.adligo.i.util.client.PropertyFactory;
import org.adligo.i.util.client.TextFormatter;
import org.adligo.i.util.client.ThreadPopulatorFactory;
import org.adligo.i.util.mocks.MockIteratorFactory;
import org.adligo.i.util.mocks.MockPropertyFactory;
import org.adligo.i.util.mocks.MockTextFormatterFactory;
import org.adligo.i.util.mocks.MockThreadPopulatorFactory;
import org.adligo.j2se.util.J2SEPlatform;

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
		J2SEPlatform.init();
	}
}
