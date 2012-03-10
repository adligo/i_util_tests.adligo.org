package org.adligo.i.util.client;

import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.tests.ATest;

public class CommonTimeTest extends ATest {
	private static final Log log = LogFactory.getLog(CommonTimeTest.class);
	
	public void testIsLeapYear() {
		assertTrue(CommonTime.isLeapYear(2000));
		boolean [] leapB = new boolean [] {true, false, false, false};
		short leapBid = 0;
		for (int i = 2000; i < 3000; i++) {
			boolean shouldBeLeap = leapB[leapBid];
			leapBid++;
			if (leapBid >= 4) {
				leapBid = 0;
			}
			
			boolean isALeap = CommonTime.isLeapYear(i);
			log.debug("year " +i + " is a leap year? " + isALeap + 
					" and should be? " + shouldBeLeap);
			if (shouldBeLeap) {
				assertTrue("the year " + i + " should be a leap year", 
						isALeap);
				assertTrue("the year " + i + " should be a leap year", 
						isALeap == shouldBeLeap);
			} else {
				assertFalse("the year " + i + " should NOT be a leap year", 
						isALeap);
				assertTrue("the year " + i + " should NOT be a leap year", 
						isALeap == shouldBeLeap);
			}
		}
		
		leapBid = 0;
		for (int i = 2000; i > -3000; i--) {
			boolean shouldBeLeap = leapB[leapBid];
			if (i == 4) {
				System.out.println("hey");
			}
			leapBid++;
			if (leapBid >= 4) {
				leapBid = 0;
			}
			
			boolean isALeap = CommonTime.isLeapYear(i);
			log.debug("year " +i + " is a leap year? " + isALeap + 
					" and should be? " + shouldBeLeap);
			if (shouldBeLeap) {
				assertTrue("the year " + i + " should be a leap year", 
						isALeap);
				assertTrue("the year " + i + " should be a leap year", 
						isALeap == shouldBeLeap);
			} else {
				assertFalse("the year " + i + " should NOT be a leap year", 
						isALeap);
				assertTrue("the year " + i + " should NOT be a leap year", 
						isALeap == shouldBeLeap);
			}
		}
	}
	

	public void testGetDaysInMonth() {
		assertEquals(29, CommonTime.getDaysInMonth(CommonTime.FEBUARY, 2000));
		assertEquals(28, CommonTime.getDaysInMonth(CommonTime.FEBUARY, 2001));
	}
	
	public void testDaysYearMonth() {
		assertEquals(366, CommonTime.getDaysInYear(2000));
		assertEquals(365, CommonTime.getDaysInYear(2001));
	}
	
	public void testDayOfMonth() {
		assertEquals(1, CommonTime.getDayOfYear(2000, 1, 1));
		assertEquals(365, CommonTime.getDayOfYear(2001, 12, 31));
		assertEquals(366, CommonTime.getDayOfYear(2000, 12, 31));
	}
}
