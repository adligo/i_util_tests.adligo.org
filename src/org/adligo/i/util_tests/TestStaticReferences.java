package org.adligo.i.util_tests;
 
import java.util.ArrayList;
import java.util.List;

import org.adligo.i.util.shared.Event;
import org.adligo.i.util.shared.I_Event;
import org.adligo.i.util.shared.I_Listener;
import org.adligo.i.util.shared.ListenerValueObject;
import org.adligo.tests.ATest;
 
/**
 * yep this class basically just tests java it self
 * which is fairly silly to some.  To others it just proves 
 * how java works.
 * 
 * not sure exactly why I thought keeping a reference to a CONSTANT
 * would make the referring object considered active for the life of the JVM
 * I WAS WRONG :)
 * 
 * @see
 *    http://forums.sun.com/thread.jspa?threadID=5357387
 *    
 * @author scott
 *
 */
public class TestStaticReferences extends ATest implements I_Listener {
	public static final String CONSTANT = "doesn't change";
	public static final Exception CONSTANT_EXCEPTION = new Exception("test exception");
	
	public void testStaticReferences() {
		/*
		 * test takes to long and
		 * leads to many false positives
		 * so its commented out
		GCTracker tracker = new GCTracker(TestStaticReferences.class, "test");
		
		tracker.setLog(false);
		
		for (int i = 0; i < 10000; i++) {
			tracker.forceGc();
			long used = tracker.getMemoryUse();
			create();
			System.out.println("Memory used is " + used + " on iteration " + i);
		}
		
		tracker.assertUse(0);
		*/
	}
 
	@SuppressWarnings("unused")
	private void create() {
		createObject();
		createList();
		createArray();
		createEvent();
		createListenerValueObject();
	}
	
	private void createObject() {
		@SuppressWarnings("unused")
		Object o = new Object() {
			@SuppressWarnings("unused")
			private String foo = CONSTANT;
			
		};
	}
	
	@SuppressWarnings("unchecked")
	private void createList() {
		List list = new ArrayList();
		list.add(CONSTANT);
	}
	
	private void createArray() {
		String [] array = new String[1];
		array[0] = CONSTANT;
	}
	
	private void createEvent() {
		Event e = new Event();
		e.setSource(this);
		e.setValue(CONSTANT);
		e.setException(CONSTANT_EXCEPTION);
	}

	private void createListenerValueObject() {
		ListenerValueObject e = new ListenerValueObject();
		e.setValue(CONSTANT);
		e.setListener(this);
	}
	
	public void onEvent(I_Event p) {
		//do nothing just for convience
	}
}