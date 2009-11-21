package org.adligo.i.util.client.models;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SimpleDefaultFailure implements IsSerializable {
 String doh;

public String getDoh() {
	return doh;
}

public void setDoh(String doh) {
	this.doh = doh;
}
 
}
