package org.adligo.i.util_tests.shared.mocks;

public class BadHashCode {
	private int count;
	
	public BadHashCode(int p_count) {
		count = p_count;
	}

	@Override
	public int hashCode() {
		int toRet = count / 100;
		return toRet;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BadHashCode other = (BadHashCode) obj;
		if (count != other.count)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BadHashCode [count=" + count + ",hash=" + hashCode() + "]";
	}

	
}
