package org.adligo.i.util_tests;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.adligo.i.log.shared.Log;
import org.adligo.i.log.shared.LogFactory;
import org.adligo.i.util.shared.DateTime;
import org.adligo.i.util_tests.shared.DateTimeAssertions;
import org.adligo.i.util_tests.shared.mocks.MockDateTime;
import org.adligo.tests.ATest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DateTimeTests extends ATest {
	private DateTimeAssertions asserts = new DateTimeAssertions();
	
	public DateTimeTests() {
		asserts.setTest(this);
	}

	@Test
	public void testStringConstructor() {
		asserts.stringConstructorAssertions();
	}
	
	@Test
	public void testDayStartEnd() {
		asserts.dayStartEndAssertions();
	}
	
	@Test
	public void testYear() {
		asserts.yearAssertions();
	}
	
	@Test
	public void testMonth() {
		asserts.monthAssertions();
	}
	
	@Test
	public void testDayOfMonth() {
		asserts.dayOfMonthAssertions();
	}
	
	@Test
	public void testHourOfMonth() {
		asserts.hourOfMonthAssertions();
	}
	
	@Test
	public void testMinuteOfHour() {
		asserts.minuteOfHourAssertions();
	}
	
	@Test
	public void testSecondOfMinute() {
		asserts.secondOfMinuteAssertions();
	}
	
	@Test
	public void testMillisecondOfSecond() {
		asserts.millisecondOfSecondAssertions();
	}
	
	@Test
	public void testDow() {
		asserts.dowAssertions();
	}
	
	@Test
	public void testToString() throws Exception {
		asserts.toStringAssertions();
	}
	
	@Test
	public void testFormatDateMethods() throws Exception {
		asserts.formatDateMethodsAssertions();
	}
	
	@Test
	public void testDayOfYearAllParmss() throws Exception {
		asserts.dayOfYearAllParmsAssertions();
	}
	
	@Test
	public void testIsLeapYear() {
		asserts.isLeapYearAssertions();
	}
	
	@Test
	public void testGetDaysInMonth() {
		asserts.getDaysInMonthAssertions();
	}
	
	@Test
	public void testDaysInYear() {
		asserts.daysInYearAsserts();
	}
	
	@Test
	public void testDayRoll() {
		asserts.dayRollAssertions();
	}
}
