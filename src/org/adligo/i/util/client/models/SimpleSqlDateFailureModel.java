package org.adligo.i.util.client.models;

import java.io.Serializable;
import java.sql.Date;

public class SimpleSqlDateFailureModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	

}
