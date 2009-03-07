package org.adligo.i.util.client;

import junit.framework.TestCase;

public class EventTest extends TestCase {

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
				"Event [source=this,value=that,exception=null]", 
				e.toString());
		
		e.setException(new Exception("throwable"));
		assertEquals("To String should match", 
				"Event [source=this,value=that,exception=java.lang.Exception: throwable]", 
				e.toString());
	}

	private void assertSource(Event e) {
		assertEquals("To String should match", 
				"Event [source=this,value=null,exception=null]", 
				e.toString());
	}
}
