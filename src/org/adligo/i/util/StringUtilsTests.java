package org.adligo.i.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.adligo.i.util.client.StringUtils;
import org.adligo.j2se.util.MapWrapper;
import org.adligo.tests.ATest;

public class StringUtilsTests extends ATest {

	public void testEqualsIgnoreCase() {
		String a = "a";
		String b = "b";
		String aA = "A";
		
		assertTrue(StringUtils.equalsIgnoreCase(null, null));
		assertFalse(StringUtils.equalsIgnoreCase(null, a));
		assertFalse(StringUtils.equalsIgnoreCase(a, null));
		assertFalse(StringUtils.equalsIgnoreCase(null, a));
		assertTrue(StringUtils.equalsIgnoreCase(aA, a));
		assertTrue(StringUtils.equalsIgnoreCase(a, aA));
	}
	
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
		mem.assertUse(3000);
	}
	
	@SuppressWarnings("unchecked")
	public void testNoLineFeedParsing() {
		GCTracker mem = new GCTracker(this.getClass(), "testUnixParsing");
		
		String fileContent = "foo=bar";
		MapWrapper mw = new MapWrapper(new HashMap());
		StringUtils.parse(fileContent, mw);
		Map map = (Map) mw.getWrapped();
		Set keys = map.keySet();
		
		assertTrue("Should contain foo", keys.contains("foo"));
		assertEquals("Should contain value bar for key foo", 
				"bar",map.get("foo"));
		
		mem.assertUse(3000);
	}
	
	public void testUnixParsing() {
		GCTracker mem = new GCTracker(this.getClass(), "testUnixParsing");
		
		String fileContent = "hey=hey_val\nhe=she\nyou=hime\n\n";
		assertParsing(fileContent);
		mem.assertUse(3000);
	}
	
	public void testDosParsing() {
		GCTracker mem = new GCTracker(this.getClass(), "testUnixParsing");
		
		String fileContent = "hey=hey_val\rhe=she\ryou=hime\r\r";
		assertParsing(fileContent);
		mem.assertUse(3000);
	}

	public void testMixedParsing() {
		GCTracker mem = new GCTracker(this.getClass(), "testUnixParsing");
		
		String fileContent = "hey=hey_val\r\nhe=she\r\nyou=hime\r\n\r\n";
		assertParsing(fileContent);
		//switch
		fileContent = "hey=hey_val\n\rhe=she\n\ryou=hime\n\r\n\r";
		assertParsing(fileContent);
		mem.assertUse(3000);
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
	
	public void testLastIndexOf() {
		String domain = "zeuhl.adligo.com";
		int index = StringUtils.lastIndexOf(domain, '.');
		assertEquals(12, index);
	}
}
