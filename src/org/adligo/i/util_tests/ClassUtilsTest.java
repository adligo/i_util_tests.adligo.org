package org.adligo.i.util_tests;

import org.adligo.i.util.shared.ClassUtils;
import org.adligo.i.util_tests.shared.ClassUtilsAssertions;
import org.adligo.tests.ATest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ClassUtilsTest extends ATest {
	private ClassUtilsAssertions asserts = new ClassUtilsAssertions();
	
	public ClassUtilsTest() {
		asserts.setTest(this);
	}
	
	@Override
	public String getScope() {
		return ClassUtils.class.getName();
	}
	
	@Test
	public void testTypeOf() {
		asserts.typeOfAsserts();
	}
		
	@Test
	public void testClassShortName() {
		asserts.classShortNameAsserts();
	}
	
	@Test
	public void testGetClassName() {
		asserts.getClassNameAsserts();
	}
}

