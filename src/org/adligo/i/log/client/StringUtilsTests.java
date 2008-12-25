package org.adligo.i.log.client;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.adligo.i.util.client.StringUtils;
import org.adligo.j2se.util.MapWrapper;

import junit.framework.TestCase;

public class StringUtilsTests extends TestCase {

	public void testEmpty() {
		GCTracker mem = new GCTracker(StringUtils.class, "testEmpty");
		
		String s = null;
		assertTrue("null should be empty ", StringUtils.isEmpty(s));
		s = "";
		assertTrue("empty string should be empty ", StringUtils.isEmpty(s));
		s = "1";
		assertFalse("1 should NOT be empty ", StringUtils.isEmpty(s));
		s = null;
		
		//class loading of StringUtils?
		mem.assertUse(2000);
	}
	
	public void testUnixParsing() {
		GCTracker mem = new GCTracker(this.getClass(), "testUnixParsing");
		
		String fileContent = "hey=hey_val\nhe=she\nyou=hime\n\n";
		assertParsing(fileContent);
		mem.assertUse(2000);
	}
	
	public void testDosParsing() {
		GCTracker mem = new GCTracker(this.getClass(), "testUnixParsing");
		
		String fileContent = "hey=hey_val\rhe=she\ryou=hime\r\r";
		assertParsing(fileContent);
		mem.assertUse(2000);
	}

	public void testMixedParsing() {
		GCTracker mem = new GCTracker(this.getClass(), "testUnixParsing");
		
		String fileContent = "hey=hey_val\r\nhe=she\r\nyou=hime\r\n\r\n";
		assertParsing(fileContent);
		//switch
		fileContent = "hey=hey_val\n\rhe=she\n\ryou=hime\n\r\n\r";
		assertParsing(fileContent);
		mem.assertUse(2000);
	}
	
	private void assertParsing(String fileContent) {
		MapWrapper mw = new MapWrapper(new HashMap());
		StringUtils.parse(fileContent, mw);
		Map map = (Map) mw.getWrapped();
		Set keys = map.keySet();
		
		assertTrue("Should contain hey", keys.contains("hey"));
		assertTrue("Should contain he", keys.contains("he"));
		assertTrue("Should contain you", keys.contains("you"));
		assertTrue("Should contain ''", keys.contains(""));
		assertEquals("The size should be 4", 4, map.size());
		
		assertEquals("The value should match", "hey_val", map.get("hey"));
		assertEquals("The value should match", "she", map.get("he"));
		assertEquals("The value should match", "hime", map.get("you"));
		
		fileContent = null;
		mw = null;
		map = null;
		keys = null;
	}
}
