package org.adligo.i.util.client.models;

import java.io.Serializable;

public class SimpleEnumContainer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Enum<?> se;

	public Enum<?> getSe() {
		return se;
	}

	public void setSe(Enum<?> se) {
		this.se = se;
	}
	
}
