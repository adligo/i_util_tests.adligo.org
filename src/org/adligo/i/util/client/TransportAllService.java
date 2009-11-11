package org.adligo.i.util.client;

import org.adligo.i.util.client.models.ComplexCollections;
import org.adligo.i.util.client.models.SimpleCollections;
import org.adligo.i.util.client.models.SimpleFailureModel;
import org.adligo.i.util.client.models.SimpleSerializable;
import org.adligo.i.util.client.models.SimpleStaticFieldModel;
import org.adligo.i.util.client.models.other_pkg.ComplexMaps;
import org.adligo.i.util.client.models.other_pkg.SimpleIsSerializable;
import org.adligo.i.util.client.models.other_pkg.SimpleMaps;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("TransportAllService")
public interface TransportAllService extends RemoteService {
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static TransportAllServiceAsync instance;
		public static TransportAllServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(TransportAllService.class);
			}
			return instance;
		}
	}
	
	public ComplexCollections transport(ComplexCollections item);
	public SimpleCollections transport(SimpleCollections item);
	public SimpleSerializable transport(SimpleSerializable item);
	public SimpleStaticFieldModel transport(SimpleStaticFieldModel item);
	public ComplexMaps transport(ComplexMaps item);
	public SimpleIsSerializable transport(SimpleIsSerializable item);
	public SimpleMaps transport(SimpleMaps item);
}
