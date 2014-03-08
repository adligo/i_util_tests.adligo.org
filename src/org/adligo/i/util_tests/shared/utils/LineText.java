package org.adligo.i.util_tests.shared.utils;

import java.util.ArrayList;
import java.util.List;

import org.adligo.i.util.shared.AppenderFactory;

public class LineText {
	private List<String> lines = new ArrayList<String>();
	
	public LineText(String text) {
		char [] chars = text.toCharArray();
		StringBuilder line = new StringBuilder();
		boolean previousLineFeed = false;
		
		String lineSep = AppenderFactory.lineSeperator();
		
		LineSplitter splitter = new LineSplitter();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (splitter.isLineFeedChar(c)) {
				if (!previousLineFeed) {
					String lineText = line.toString();
					line = new StringBuilder();
					lines.add(lineText);
				}
				previousLineFeed = true;
			} else {
				line.append(c);
				previousLineFeed = false;
			}
		}
		String lineText = line.toString();
		if (lineText.length() != 0) {
			lines.add(lineText);
		}
	}
	
	public int getLines() {
		return lines.size();
	}
	
	public String getLine(int i) {
		return lines.get(i);
	}
}
