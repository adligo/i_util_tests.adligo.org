package org.adligo.i.util.client;

import org.adligo.tests.ATest;

public class EventTest extends ATest {

	public void testEvent() {
		Event e = new Event();
		
		assertEquals("To String should match", 
				"Event [source=null,value=null,exception=null]", 
				e.toString());
		e.setSource("this");
		assertSource(e);
		e = new Event("this");
		assertSource(e);
		
		e.setValue("that");
		assertEquals("To String should match", 
				"Event [source= instanceofString-HASH-3559070,value=that,exception=null]", 
				e.toString());
		
		e.setException(new Exception("throwable"));
		assertEquals("To String should match", 
				"Event [source= instanceofString-HASH-3559070,value=that,exception=java.lang.Exception: throwable]", 
				e.toString());
	}

	private void assertSource(I_Event e) {
		assertEquals("To String should match", 
				"Event [source= instanceofString-HASH-3559070,value=null,exception=null]", 
				e.toString());
	}
}
