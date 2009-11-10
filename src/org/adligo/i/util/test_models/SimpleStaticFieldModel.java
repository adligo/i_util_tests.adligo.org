package org.adligo.i.util.test_models;

import java.io.Serializable;

public class SimpleStaticFieldModel implements Serializable {
	private static final SimpleFailureModel STATIC = new SimpleFailureModel();
	
	private String field;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}
