package org.adligo.i.util_tests.shared.utils;

import org.adligo.tests.shared.AAssertions;


public class LineTextAssertions extends AAssertions {
	private String packageName;
	
	public void compaireFileText(String example, String actual) {
		LineText exampleLT = new LineText(example);
		LineText actualLT = new LineText(actual);
		assertEquals("The number of lines should match expected ",
				 exampleLT.getLines(), actualLT.getLines());
		
		for (int i = 0; i < exampleLT.getLines(); i++) {
			char [] exampleChars = exampleLT.getLine(i).toCharArray();
			char [] actualChars = actualLT.getLine(i).toCharArray();
			for (int j = 0; j < exampleChars.length; j++) {
				char c = exampleChars[j];
				char a = actualChars[j];
				if (c != a) {
					assertEquals("the character on line " + i + " at character " + j +
							" should match is '" + a + "' should be '" + c + "'",new String(exampleChars), new String(actualChars));
				}
			}
			
			assertEquals("The characters in line " + i + " should match ", 
					exampleChars.length, actualChars.length);
		}
	}

	@Override
	public String getPackage() {
		return packageName;
	}

	public void setPackage(String p) {
		packageName = p;
	}
}
