package org.adligo.i.util.client.models;

import org.adligo.i.util.client.I_Serializable;

public class SimplePrivateFailure implements I_Serializable {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
private String dam;

public String getDam() {
	return dam;
}

public void setDam(String dam) {
	this.dam = dam;
}
 
}
