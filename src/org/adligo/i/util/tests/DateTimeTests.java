package org.adligo.i.util.tests;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.i.util.client.DateTime;
import org.adligo.tests.ATest;


public class DateTimeTests extends ATest {
	private static final Log log = LogFactory.getLog(DateTimeTests.class);

	public void testStringConstructor() {
		DateTime dt = new DateTime("01/29/2001 12:01 AM 000");
		assertEquals(2001,dt.getYear());
		assertEquals(1, dt.getMonth());
		assertEquals(29, dt.getDayOfMonth());
		assertEquals(980748060000L, dt.getTime());
		assertEquals(new Long(980748060000L), dt.getTimeLong());
	}
	
	public void testDayStartEnd() {
		DateTime dt = new DateTime(0);
		assertEquals(-64800000L,dt.getDayStart());
		assertEquals("12/31/1969 12:00 AM 000",new DateTime(dt.getDayStart()).toString());
		assertEquals(21599999,dt.getDayEnd());
		assertEquals("12/31/1969 11:59 PM 999",new DateTime(dt.getDayEnd()).toString());
	}
	
	public void testYear() {
		DateTime dt = new DateTime(0);
		assertEquals(1969,dt.getYear());
		dt = new DateTime(DateTime.ONE_DAY);
		assertEquals(1970,dt.getYear());
	}
	
	public void testMonth() {
		DateTime dt = new DateTime(0);
		assertEquals(12,dt.getMonth());
		dt = new DateTime(DateTime.ONE_DAY);
		assertEquals(1,dt.getMonth());
	}
	
	public void testDayOfMonth() {
		DateTime dt = new DateTime(0);
		assertEquals(0L, dt.getTime());
		assertEquals(new Long(0L), dt.getTimeLong());
		
		assertEquals(31,dt.getDayOfMonth());
		dt = new DateTime(DateTime.ONE_DAY);
		assertEquals(1,dt.getDayOfMonth());
	}
	
	public void testHourOfMonth() {
		DateTime dt = new DateTime(0);
		assertEquals(6,dt.getHourOfDay());
		dt = new DateTime(DateTime.ONE_HOUR);
		assertEquals(7,dt.getHourOfDay());
		
		dt = new DateTime(-1 * DateTime.ONE_HOUR);
		assertEquals(5,dt.getHourOfDay());
	}
	
	public void testMinuteOfHour() {
		DateTime dt = new DateTime(0);
		assertEquals(0,dt.getMinuteOfHour());
		dt = new DateTime(DateTime.ONE_MINUTE);
		assertEquals(1,dt.getMinuteOfHour());
		
		dt = new DateTime(-1 * DateTime.ONE_MINUTE);
		assertEquals(59,dt.getMinuteOfHour());
	}
	
	public void testSecondOfMinute() {
		DateTime dt = new DateTime(0);
		assertEquals(0,dt.getSecondOfMinute());
		dt = new DateTime(1000);
		assertEquals(1,dt.getSecondOfMinute());
		
		dt = new DateTime(-1000);
		assertEquals(59,dt.getSecondOfMinute());
	}
	
	public void testMillisecondOfSecond() {
		DateTime dt = new DateTime(0);
		assertEquals(0,dt.getSecondOfMinute());
		dt = new DateTime(1);
		assertEquals(1,dt.getMillisecondOfSecond());
		
		dt = new DateTime(-10);
		assertEquals(990,dt.getMillisecondOfSecond());
	}
	
	public void testDow() {
		
		DateTime dt = new DateTime(DateTime.ONE_DAY * -2);
		assertEquals(DateTime.MONDAY,dt.getDayOfWeek());
		
		dt = new DateTime(DateTime.ONE_DAY * -1);
		assertEquals(DateTime.TUESDAY,dt.getDayOfWeek());
		
		dt = new DateTime(1);
		assertEquals(DateTime.WENSDAY,dt.getDayOfWeek());
		
		dt = new DateTime(DateTime.ONE_DAY);
		assertEquals(DateTime.THURSDAY,dt.getDayOfWeek());
		
		dt = new DateTime(DateTime.ONE_DAY * 2);
		assertEquals(DateTime.FRIDAY,dt.getDayOfWeek());
		
		dt = new DateTime(DateTime.ONE_DAY * 3);
		assertEquals(DateTime.SATURDAY,dt.getDayOfWeek());
		
		dt = new DateTime(DateTime.ONE_DAY * 4);
		assertEquals(DateTime.SUNDAY,dt.getDayOfWeek());
	}
	
	
	public void testToString() throws Exception {
		assertEquals("12/31/1969 06:00 PM 000", new DateTime(0).toString());
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = sdf.parse("01/01/2000");
		assertEquals("01/01/2000 12:00 AM 000", new DateTime(date.getTime()).toString());
		
		DateTimeExtension dt = new DateTimeExtension();
		assertEquals("01/01/2000 12:00 AM 000",  dt.toStringFix("01/01/2000 12:00 aa.m. 000"));
		assertEquals("01/01/2000 12:00 PM 000",  dt.toStringFix("01/01/2000 12:00 ap.m. 000"));
		
	}
	
	public void testFormatDateMethods() throws Exception {
		assertEquals("12/31/1969", DateTime.formatDate(0L));
		assertEquals("12/31/1969 06:00 PM 000", DateTime.formatDateTime(0L));
		assertEquals("12/31/1969", DateTime.formatDate(DateTime.FULL_YEAR_DATE_FORMAT, 0L));
		
		
	}
	
	public void testDayOfYearAllParmas() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = sdf.parse("01/01/2000");
		
		DateTime dt = new DateTime(date.getTime());
		assertEquals(1, dt.getDayOfYear());
		date = sdf.parse("12/31/2001");
		dt = new DateTime(date.getTime());
		assertEquals(365, dt.getDayOfYear());
		date = sdf.parse("12/31/2000");
		dt = new DateTime(date.getTime());
		assertEquals(366, dt.getDayOfYear());
		
		dt = new DateTime(0);
		assertEquals(365, dt.getDayOfYear());
		
		dt = new DateTime(1331346065687L);
		assertEquals(69, dt.getDayOfYear());
		
		date = sdf.parse("03/04/2012");
		dt = new DateTime(date.getTime());
		System.out.println("" +  date.getTime());
		assertEquals(64, dt.getDayOfYear());
		//should be the same date with a different time
		dt = new DateTime(date.getTime() + 50000);
		String asSTring = dt.toString();
		assertEquals(asSTring, 64, dt.getDayOfYear());
	}
	
	
	public void testIsLeapYear() {
		assertTrue(DateTime.isLeapYear(2000));
		boolean [] leapB = new boolean [] {true, false, false, false};
		short leapBid = 0;
		for (int i = 2000; i < 3000; i++) {
			boolean shouldBeLeap = leapB[leapBid];
			leapBid++;
			if (leapBid >= 4) {
				leapBid = 0;
			}
			
			boolean isALeap = DateTime.isLeapYear(i);
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
			
			boolean isALeap = DateTime.isLeapYear(i);
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
		assertEquals(31, DateTime.getDaysInMonth(DateTime.DECEMBER, 2001));
		assertEquals(29, DateTime.getDaysInMonth(DateTime.FEBUARY, 2000));
		assertEquals(28, DateTime.getDaysInMonth(DateTime.FEBUARY, 2001));
		assertEquals(31, DateTime.getDaysInMonth(DateTime.MARCH, 2001));
		assertEquals(30, DateTime.getDaysInMonth(DateTime.APRIL, 2001));
		assertEquals(31, DateTime.getDaysInMonth(DateTime.MAY, 2001));
		assertEquals(30, DateTime.getDaysInMonth(DateTime.JUNE, 2001));
		assertEquals(31, DateTime.getDaysInMonth(DateTime.JULY, 2001));
		assertEquals(31, DateTime.getDaysInMonth(DateTime.AUGUST, 2001));
		assertEquals(30, DateTime.getDaysInMonth(DateTime.SEPTEMBER, 2001));
		assertEquals(31, DateTime.getDaysInMonth(DateTime.OCTOBER, 2001));
		assertEquals(31, DateTime.getDaysInMonth(DateTime.DECEMBER, 2001));
		IllegalArgumentException caught = null;
		try {
			DateTime.getDaysInMonth((short) 13, 2001);
		} catch (IllegalArgumentException e) {
			caught = e;
		}
		assertNotNull(caught);
		assertEquals(DateTime.THE_MONTH_PASSED_IN_TO_GET_DAYS_IN_MONT_IS_NOT_BETWEEN_1_AND_12, caught.getMessage());
	}
	
	public void testDaysYearMonth() {
		assertEquals(366, DateTime.getDaysInYear(2000));
		assertEquals(365, DateTime.getDaysInYear(2001));
	}
	
	/**
	 * this test discovered that using ints as constants caused a 
	 * multiplication constraint when using a month
	 * so the constants ONE_DAY exc were changed to longs
	 */
	public void testDayRoll() {
		long epoc = 0L;
		long dayAfterEpoc = epoc + DateTime.ONE_DAY;
		long dayBeforeEpoc = epoc - DateTime.ONE_DAY;
		assertEquals("Wed Dec 31 18:00:00 CST 1969", new Date(epoc).toString());
		assertEquals("Thu Jan 01 18:00:00 CST 1970", new Date(dayAfterEpoc).toString());
		assertEquals("Tue Dec 30 18:00:00 CST 1969", new Date(dayBeforeEpoc).toString());
		
		long monthAfterEpoc = epoc + (DateTime.ONE_DAY * 30);
		long monthBeforeEpoc = epoc - (DateTime.ONE_DAY * 30);
		assertEquals("Fri Jan 30 18:00:00 CST 1970", new Date(monthAfterEpoc).toString());
		assertEquals("Mon Dec 01 18:00:00 CST 1969", new Date(monthBeforeEpoc).toString());
		
	}
}
