package org.adligo.i.util_tests;

import org.adligo.i.util.shared.Event;
import org.adligo.i.util.shared.I_Event;
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
				"Event [source=this,value=that,exception=null]", 
				e.toString());
		
		e.setException(new Exception("throwable"));
		assertEquals("To String should match", 
				"Event [source=this,value=that,exception=java.lang.Exception: throwable]", 
				e.toString());
		
		e = new Event();
		e.setSource("<some huge ass string>could be html set as the souce of a event</some huge ass string>" +
				"<note> I have found this to be somewhat poor form and I am mostly using enums for source now </note>" +
				"<ps> although enums arn't on j2me so I guess I will go back to int's there (for switch stmts)</ps>");
		
		assertEquals("Event [source= instanceofString-HASH--745139899,value=null,exception=null]", 
				e.toString());
	}

	private void assertSource(I_Event e) {
		assertEquals("To String should match", 
				"Event [source=this,value=null,exception=null]", 
				e.toString());
	}
}
