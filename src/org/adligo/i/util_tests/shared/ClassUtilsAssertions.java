package org.adligo.i.util_tests.shared;

import java.io.Serializable;

import org.adligo.i.util.shared.ClassUtils;
import org.adligo.i.util.shared.I_Collection;
import org.adligo.tests.ATest;
import org.adligo.tests.shared.AAssertions;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


public class ClassUtilsAssertions extends AAssertions {

	public void typeOfAsserts() {
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
		
	
	public void classShortNameAsserts() {
		String shortName = ClassUtils.getClassShortName(this.getClass());
		assertEquals("ClassUtilsAssertions", shortName);
		
	}
	
	public void getClassNameAsserts() {
		assertEquals("org.adligo.i.util_tests.shared.ClassUtilsAssertions", ClassUtils.getClassName(ClassUtilsAssertions.class));
		assertEquals("org.adligo.i.util.shared.I_Collection", ClassUtils.getClassName(I_Collection.class));
		
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
