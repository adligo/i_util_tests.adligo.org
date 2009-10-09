package org.adligo.i.util.client;

import java.io.Serializable;

import org.adligo.tests.ATest;

public class ClassUtilsTest extends ATest {

	public void testTypeOf() {
		String s = new String();
		
		String className = ClassUtils.getClassName(
				s.getClass());
		assertEquals("The class names should match",
				"java.lang.String", className);
		
		assertTrue("object s should be a type of String",
				ClassUtils.typeOf(s, String.class));
		
		
		MockClass o  = new MockClass();
		assertTrue("object s should be a type of MockClass",
				ClassUtils.typeOf(o, MockClass.class));
		assertFalse("object s should NOT be a type of Serializable",
				ClassUtils.typeOf(o, Serializable.class));
		assertFalse("object s should NOT be a type of MockClass2",
				ClassUtils.typeOf(o, MockClass2.class));
	
	
	}
		
	
	public void testClassShortName() {
		String shortName = ClassUtils.getClassShortName(this.getClass());
		assertEquals("ClassUtilsTest", shortName);
		
	}
}

class MockClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}

class MockClass2 extends MockClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
