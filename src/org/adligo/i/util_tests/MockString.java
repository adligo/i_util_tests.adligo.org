package org.adligo.i.util_tests;

import junit.framework.TestCase;

/**
 * this is a test class so that 
 * .equals is overridden but toString isn't
 * @author scott
 *
 */
public class MockString {
	private String string;
	
	public MockString() {	
	}
	
	public MockString(String p) {
		string = p;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((string == null) ? 0 : string.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		//System.out.println("Compairing " + string + " and " + obj);
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MockString other = (MockString) obj;
		if (string == null) {
			if (other.string != null)
				return false;
		} else if (!string.equals(other.string))
			return false;
		return true;
	}
}
