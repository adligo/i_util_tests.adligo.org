package org.adligo.i.util_tests.shared;

import org.adligo.i.util.shared.I_Appender;
import org.adligo.i.util.shared.I_ThrowableHelper;

public class MockThrowableHelper implements I_ThrowableHelper {
	private Throwable lastFillInStackTraceThrowable;
	
	private Throwable lastGetStackTraceAsStringThrowable;
	private String lastGetStackTraceAsStringPreText;
	private String lastGetStackTraceAsStringLineFeed;
	private I_Appender lastGetStackTraceAsStringAppender;
	
	@Override
	public void fillInStackTrace(Throwable p) {
		lastFillInStackTraceThrowable =p;
	}

	@Override
	public void appendStackTracesString(String preText, Throwable p,
			String lineFeed, I_Appender appender) {
		lastGetStackTraceAsStringThrowable = p;
		lastGetStackTraceAsStringPreText = preText;
		lastGetStackTraceAsStringLineFeed = lineFeed;
		lastGetStackTraceAsStringAppender = appender;
	}

	public Throwable getLastFillInStackTraceThrowable() {
		return lastFillInStackTraceThrowable;
	}

	public Throwable getLastGetStackTraceAsStringThrowable() {
		return lastGetStackTraceAsStringThrowable;
	}

	public String getLastGetStackTraceAsStringPreText() {
		return lastGetStackTraceAsStringPreText;
	}

	public String getLastGetStackTraceAsStringLineFeed() {
		return lastGetStackTraceAsStringLineFeed;
	}

	public I_Appender getLastGetStackTraceAsStringAppender() {
		return lastGetStackTraceAsStringAppender;
	}

}
