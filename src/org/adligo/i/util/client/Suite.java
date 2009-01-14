package org.adligo.i.util.client;

import junit.framework.Test;
import junit.framework.TestSuite;

public class Suite {

	public static Test suite() {
		TestSuite suite= new TestSuite();


		suite.addTestSuite(ArrayCollectionTests.class);
		suite.addTestSuite(StringUtilsTests.class);
		
		return suite;
		
	}
}
