package org.adligo.i.util_tests.shared.utils;

import org.adligo.i.util.shared.AppenderFactory;

public class LineSplitter {
	private String lineSeperator = AppenderFactory.lineSeperator();
	private boolean lineSeperatorOneChar;
	private Character lastChar;
	
	public LineSplitter() {
		if (lineSeperator.length() == 1) {
			lineSeperatorOneChar = true;
		} else {
			lineSeperatorOneChar = false;
		}
	}
	
	public boolean isLineFeedChar(char c) {
		if (lineSeperatorOneChar) {
			if (c == lineSeperator.charAt(0)) {
				return true;
			}
		} else {
			if (c == lineSeperator.charAt(0)) {
				lastChar = c;
				return true;
			} else if (lastChar == lineSeperator.charAt(0)){
				if (lineSeperator.length() == 2) {
					if (c == lineSeperator.charAt(1)) {
						lastChar = null;
						return true;
					}
				}
				lastChar = null;
				return false;
			} 
		}
		return false;
	}
	
}
