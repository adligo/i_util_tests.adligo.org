package org.adligo.i.util.client.models;

import java.io.Serializable;

public class GenericEnum<T extends Enum<?>> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	T t;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	
}
