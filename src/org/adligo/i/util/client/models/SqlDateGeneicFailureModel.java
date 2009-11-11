package org.adligo.i.util.client.models;

import java.sql.Date;
import java.util.Collection;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SqlDateGeneicFailureModel implements IsSerializable {

	private Collection<Date> sqlDateCollection;

	public Collection<Date> getSqlDateCollection() {
		return sqlDateCollection;
	}

	public void setSqlDateCollection(Collection<Date> sqlDateCollection) {
		this.sqlDateCollection = sqlDateCollection;
	}
	
}
