package org.adligo.i.util_tests.shared;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.adligo.i.util.shared.I_Map;
import org.adligo.i.util.shared.MapFactory;
import org.adligo.i.util.shared.StringUtils;
import org.adligo.jse.util.MapWrapper;
import org.adligo.tests.shared.AAssertions;

public class StringUtilsAssertions extends AAssertions {

	public void equalsIgnoreCaseAsserts() {
		String a = "a";
		String b = "b";
		String aA = "A";
		
		assertTrue(StringUtils.equalsIgnoreCase(null, null));
		assertFalse(StringUtils.equalsIgnoreCase(null, a));
		assertFalse(StringUtils.equalsIgnoreCase(a, b));
		assertFalse(StringUtils.equalsIgnoreCase(b, a));
		
		assertFalse(StringUtils.equalsIgnoreCase(a, null));
		assertFalse(StringUtils.equalsIgnoreCase(null, a));
		assertTrue(StringUtils.equalsIgnoreCase(aA, a));
		assertTrue(StringUtils.equalsIgnoreCase(a, aA));
	}
	
	public void emptyAsserts() {
		String s = null;
		assertTrue("null should be empty ", StringUtils.isEmpty(s));
		s = "";
		assertTrue("empty string should be empty ", StringUtils.isEmpty(s));
		s = "1";
		assertFalse("1 should NOT be empty ", StringUtils.isEmpty(s));
		s = null;
	}
	
	@SuppressWarnings("unchecked")
	public void noLineFeedParsingAsserts() {
		String fileContent = "foo=bar";
		MapWrapper mw = new MapWrapper(new HashMap<String,String>());
		StringUtils.parse(fileContent, mw);
		Map<String,String> map = (Map<String,String>) mw.getWrapped();
		Set<String> keys = map.keySet();
		
		assertTrue("Should contain foo", keys.contains("foo"));
		assertEquals("Should contain value bar for key foo", 
				"bar",map.get("foo"));
	}
	
	public void unixParsingAsserts() {
		String fileContent = "hey=hey_val\nhe=she\nyou=hime\n\n";
		assertParsing(fileContent);
	}
	
	public void dosParsingAsserts() {
		String fileContent = "hey=hey_val\rhe=she\ryou=hime\r\r";
		assertParsing(fileContent);
	}

	public void mixedParsingAsserts() {
		String fileContent = "hey=hey_val\r\nhe=she\r\nyou=hime\r\n\r\n";
		assertParsing(fileContent);
		//switch
		fileContent = "hey=hey_val\n\rhe=she\n\ryou=hime\n\r\n\r";
		assertParsing(fileContent);
	}
	
	public void emptyParsingAsserts() {
		I_Map map = MapFactory.create();
		String fileContent = "\n\r\n=him\n\r=\n\r";
		StringUtils.parse(fileContent, map);
		
		assertEquals("" + map, 0, map.size());
		
	}
	
	@SuppressWarnings("unchecked")
	private void assertParsing(String fileContent) {
		MapWrapper mw = new MapWrapper(new HashMap<String,String>());
		StringUtils.parse(fileContent, mw);
		Map<String,String> map = (Map<String,String>) mw.getWrapped();
		Set<String> keys = map.keySet();
		
		assertTrue("Should contain hey", keys.contains("hey"));
		assertTrue("Should contain he", keys.contains("he"));
		assertTrue("Should contain you", keys.contains("you"));
		assertEquals("The size should be 3", 3, map.size());
		
		assertEquals("The value should match", "hey_val", map.get("hey"));
		assertEquals("The value should match", "she", map.get("he"));
		assertEquals("The value should match", "hime", map.get("you"));
		
		fileContent = null;
		mw = null;
		map = null;
		keys = null;
	}
	
	public void lastIndexOfAsserts() {
		String domain = "zeuhl.adligo.com";
		int index = StringUtils.lastIndexOf(domain, '.');
		assertEquals(12, index);
	}
}
