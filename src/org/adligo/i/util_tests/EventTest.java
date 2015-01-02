package org.adligo.i.util_tests;

import org.adligo.i.util.shared.Event;
import org.adligo.i.util_tests.shared.EventAssertions;
import org.adligo.tests.ATest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class EventTest extends ATest {
	private EventAssertions asserts = new EventAssertions();
	
	public EventTest() {
		asserts.setTest(this);
	}
	
	@Test
	public void eventAssertions() {
		asserts.eventAssertions();
	}

	
}
