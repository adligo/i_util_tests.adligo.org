package org.adligo.i.util_tests;

import org.adligo.i.util.shared.StringUtils;
import org.adligo.i.util_tests.shared.StringUtilsAssertions;
import org.adligo.tests.ATest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class StringUtilsTests extends ATest {
	private StringUtilsAssertions asserts = new StringUtilsAssertions();
	
	public StringUtilsTests() {
		asserts.setTest(this);
	}
	
	@Test
	public void testEqualsIgnoreCase() {
		asserts.equalsIgnoreCaseAsserts();
	}
	
	@Test
	public void testEmpty() {
		GCTracker mem = new GCTracker(StringUtils.class, "testEmpty");
		asserts.emptyAsserts();
		mem.assertUse(3000);
	}
	
	@Test
	public void testNoLineFeedParsing() {
		GCTracker mem = new GCTracker(this.getClass(), "testUnixParsing");
		asserts.noLineFeedParsingAsserts();
		mem.assertUse(3000);
	}
	
	@Test
	public void testUnixParsing() {
		GCTracker mem = new GCTracker(this.getClass(), "testUnixParsing");
		asserts.unixParsingAsserts();
		mem.assertUse(3000);
	}
	
	@Test
	public void testDosParsing() {
		GCTracker mem = new GCTracker(this.getClass(), "testUnixParsing");
		asserts.dosParsingAsserts();
		mem.assertUse(3000);
	}

	@Test
	public void testMixedParsing() {
		GCTracker mem = new GCTracker(this.getClass(), "testUnixParsing");
		asserts.mixedParsingAsserts();
		mem.assertUse(3000);
	}
	
	@Test
	public void testEmptyParsing() {
		asserts.emptyParsingAsserts();
	}
	
	@Test
	public void testLastIndexOf() {
		asserts.lastIndexOfAsserts();
	}

	
}
