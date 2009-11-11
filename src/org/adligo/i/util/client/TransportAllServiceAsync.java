package org.adligo.i.util.client;

import org.adligo.i.util.client.models.ComplexCollections;
import org.adligo.i.util.client.models.SimpleCollections;
import org.adligo.i.util.client.models.SimpleFailureModel;
import org.adligo.i.util.client.models.SimpleSerializable;
import org.adligo.i.util.client.models.SimpleStaticFieldModel;
import org.adligo.i.util.client.models.other_pkg.ComplexMaps;
import org.adligo.i.util.client.models.other_pkg.SimpleIsSerializable;
import org.adligo.i.util.client.models.other_pkg.SimpleMaps;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TransportAllServiceAsync {
	public void transport(ComplexCollections item, AsyncCallback<ComplexCollections> callback);
	public void transport(SimpleCollections item, AsyncCallback<SimpleCollections> callback);
	public void transport(SimpleSerializable item, AsyncCallback<SimpleSerializable> callback);
	public void transport(SimpleStaticFieldModel item, AsyncCallback<SimpleStaticFieldModel> callback);
	public void transport(ComplexMaps item, AsyncCallback<ComplexMaps> callback);
	public void transport(SimpleIsSerializable item, AsyncCallback<SimpleIsSerializable> callback);
	public void transport(SimpleMaps item, AsyncCallback<SimpleMaps> callback);
}
