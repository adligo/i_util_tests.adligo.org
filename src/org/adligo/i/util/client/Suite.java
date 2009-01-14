package org.adligo.i.util.client;

import junit.framework.TestSuite;

public class Suite extends TestSuite {

	public Suite() {
		super.addTest(new ArrayCollectionTests());
		super.addTest(new StringUtilsTests());
	}
}
