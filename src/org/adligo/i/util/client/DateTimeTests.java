package org.adligo.i.util.client;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.adligo.tests.ATest;


public class DateTimeTests extends ATest {

	public void testYear() {
		DateTime dt = new DateTime(0);
		assertEquals(1969,dt.getYear());
		dt = new DateTime(CommonTime.ONE_DAY);
		assertEquals(1970,dt.getYear());
	}
	
	public void testMonth() {
		DateTime dt = new DateTime(0);
		assertEquals(12,dt.getMonth());
		dt = new DateTime(CommonTime.ONE_DAY);
		assertEquals(1,dt.getMonth());
	}
	
	public void testDayOfMonth() {
		DateTime dt = new DateTime(0);
		assertEquals(31,dt.getDayOfMonth());
		dt = new DateTime(CommonTime.ONE_DAY);
		assertEquals(1,dt.getDayOfMonth());
	}
	
	public void testHourOfMonth() {
		DateTime dt = new DateTime(0);
		assertEquals(6,dt.getHourOfDay());
		dt = new DateTime(CommonTime.ONE_HOUR);
		assertEquals(7,dt.getHourOfDay());
		
		dt = new DateTime(-1 * CommonTime.ONE_HOUR);
		assertEquals(5,dt.getHourOfDay());
	}
	
	public void testMinuteOfHour() {
		DateTime dt = new DateTime(0);
		assertEquals(0,dt.getMinuteOfHour());
		dt = new DateTime(CommonTime.ONE_MINUTE);
		assertEquals(1,dt.getMinuteOfHour());
		
		dt = new DateTime(-1 * CommonTime.ONE_MINUTE);
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
		
		DateTime dt = new DateTime(CommonTime.ONE_DAY * -2);
		assertEquals(CommonTime.MONDAY,dt.getDayOfWeek());
		
		dt = new DateTime(CommonTime.ONE_DAY * -1);
		assertEquals(CommonTime.TUESDAY,dt.getDayOfWeek());
		
		dt = new DateTime(1);
		assertEquals(CommonTime.WENSDAY,dt.getDayOfWeek());
		
		dt = new DateTime(CommonTime.ONE_DAY);
		assertEquals(CommonTime.THURSDAY,dt.getDayOfWeek());
		
		dt = new DateTime(CommonTime.ONE_DAY * 2);
		assertEquals(CommonTime.FRIDAY,dt.getDayOfWeek());
		
		dt = new DateTime(CommonTime.ONE_DAY * 3);
		assertEquals(CommonTime.SATURDAY,dt.getDayOfWeek());
		
		dt = new DateTime(CommonTime.ONE_DAY * 4);
		assertEquals(CommonTime.SUNDAY,dt.getDayOfWeek());
	}
	
	public void testToString() throws Exception {
		assertEquals("12/31/1969 06:00 PM 000", new DateTime(0).toString());
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = sdf.parse("01/01/2000");
		assertEquals("01/01/2000 12:00 AM 000", new DateTime(date.getTime()).toString());
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
	}
}
