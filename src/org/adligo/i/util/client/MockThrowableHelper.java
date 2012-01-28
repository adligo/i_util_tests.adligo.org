package org.adligo.i.util.client;

import org.adligo.i.util.client.I_ThrowableHelper;

public class MockThrowableHelper implements I_ThrowableHelper {
	private Throwable lastFillInStackTraceThrowable;
	
	private Throwable lastGetStackTraceAsStringThrowable;
	private String lastGetStackTraceAsStringPreText;
	private String lastGetStackTraceAsStringLineFeed;
	private String nextStackTraceAsStringReturn = null;
	
	@Override
	public void fillInStackTrace(Throwable p) {
		lastFillInStackTraceThrowable =p;
	}

	@Override
	public String getStackTraceAsString(Throwable p) {
		lastGetStackTraceAsStringThrowable = p;
		return nextStackTraceAsStringReturn;
	}

	@Override
	public String getStackTraceAsString(String preText, Throwable p,
			String lineFeed) {
		lastGetStackTraceAsStringThrowable = p;
		lastGetStackTraceAsStringPreText = preText;
		lastGetStackTraceAsStringLineFeed = lineFeed;
		return nextStackTraceAsStringReturn;
	}

	public String getNextStackTraceAsStringReturn() {
		return nextStackTraceAsStringReturn;
	}

	public void setNextStackTraceAsStringReturn(String nextStackTraceAsStringReturn) {
		this.nextStackTraceAsStringReturn = nextStackTraceAsStringReturn;
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

}
